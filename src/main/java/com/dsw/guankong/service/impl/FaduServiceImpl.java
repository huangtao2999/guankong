package com.dsw.guankong.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dsw.guankong.constant.FaduConfig;
import com.dsw.guankong.dal.TpBaqryUserDoMapperExt;
import com.dsw.guankong.dal.TpBlrecordDoMapperExt;
import com.dsw.guankong.model.TpBaqryUserDo;
import com.dsw.guankong.model.TpBaqryUserDoExample;
import com.dsw.guankong.model.TpBlrecordDo;
import com.dsw.guankong.model.TpBlrecordDoExample;
import com.dsw.guankong.service.FaduService;
import com.dsw.guankong.util.*;
import com.dsw.guankong.vo.BlContentVo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;

@Service
public class FaduServiceImpl implements FaduService {
    protected static final Logger logger = Logger.getLogger(FaduServiceImpl.class);
    @Value("${bl.service.url}")
    private String FADU_URL;
    @Autowired
    private FileUpload fileUpload;
    @Autowired(required = false)
    private TpBaqryUserDoMapperExt tpBaqryUserDoMapperExt;
    @Autowired(required = false)
    private TpBlrecordDoMapperExt tpBlrecordDoMapperExt;

    @Override
    public ActionResult queryBiluListByRyIdcard() throws Exception {
        ActionResult actionResult = new ActionResult();
        //接口安全接入 秘钥
        Map<String, String> params = new HashMap<String, String>();
        params.put("apiKey", FaduConfig.appKey);
        //设置请求参数  查询条件
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("idCard", "513435199706032115");
        data.put("kssj", "20171229000000");
        data.put("start", "1");
        data.put("end", "10");
        //对查询条件aes加密
        AESTool aes = new AESTool();
        String d = aes.encrypt(JSON.toJSONString(data), FaduConfig.appSecret);
        params.put("data", d);
        String bgSig = MD5Util.MD5(FaduConfig.appKey + FaduConfig.appSecret + d);
        params.put("bdSig", bgSig);
        //通过http请求发送查询条件，返回查询结果
        String json = HttpClientUtil.post(FADU_URL + FaduConfig.method_queryBiluListByRyIdcard, params);
        System.out.println(json);
        actionResult.setContent(json);
        return actionResult;
    }

    @Override
    public BlContentVo queryBiluByBiluID(String blId, String isOuterId) throws Exception {
        BlContentVo blContentVo = new BlContentVo();
        //接口安全接入 秘钥
        Map<String, String> params = new HashMap<String, String>();
        params.put("apiKey", FaduConfig.appKey);
        //设置请求参数  查询条件
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("blid", blId);
        data.put("wtsj", "0");
        data.put("isOuterId", isOuterId);
        //对查询条件aes加密
        AESTool aes = new AESTool();
        String d = aes.encrypt(JSON.toJSONString(data), FaduConfig.appSecret);
        params.put("data", d);
        String bgSig = MD5Util.MD5(FaduConfig.appKey + FaduConfig.appSecret + d);
        params.put("bdSig", bgSig);
        //通过http请求发送查询条件，返回查询结果
        String html = HttpClientUtil.post(FADU_URL + FaduConfig.method_queryBiluByBiluID, params);
        JSONObject jsonObject = JSONObject.parseObject(html);
        if (null != jsonObject && jsonObject.getIntValue("resultCode") == 0) {
            JSONObject jsonData = jsonObject.getJSONObject("data");
            String kssj = jsonData.getString("KSSJ");
            String jssj = jsonData.getString("JSSJ");
            String wdnr = jsonData.getString("WDNR");
            String blnr = aes.decrypt(wdnr, FaduConfig.appSecret);
            blContentVo.setKssj(kssj);
            blContentVo.setJssj(jssj);
            blContentVo.setBlnr(blnr);
        }
        return blContentVo;
    }

    @Override
    public List<String> getSigntureFileContent(String blid) throws Exception {
        List<String> list = new ArrayList<>();
        //接口安全接入 秘钥
        Map<String, String> params = new HashMap<String, String>();
        params.put("apiKey", FaduConfig.appKey);
        //设置请求参数  查询条件
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("blid", blid);
        //对查询条件aes加密
        AESTool aes = new AESTool();
        String d = aes.encrypt(JSON.toJSONString(data), FaduConfig.appSecret);
        params.put("data", d);
        String bgSig = MD5Util.MD5(FaduConfig.appKey + FaduConfig.appSecret + d);
        params.put("bdSig", bgSig);
        //通过http请求发送查询条件，返回查询结果
        String json = HttpClientUtil.post(FADU_URL + FaduConfig.method_getLatestSigntureFileContent, params);
        JSONObject jsonObject = JSONObject.parseObject(json);
        if (null != jsonObject && 1 == jsonObject.getIntValue("resultCode")) {
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            String content = null;
            String name = null;
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                content = jsonObject2.getString("content");
                name = jsonObject2.getString("fileName");
                if (null != content) {
                    byte[] bytes = FuncComm.base64ToByte(content);
                    String fileName = MD5Util.MD5(content) + "." + "pdf";
                    String fileAddress = fileUpload.saveRemote(bytes, fileName);
                    list.add(fileAddress);
                }
            }
        }
        return list;
    }

    /**
     * 通过人员登记ID 获取笔内容
     */
    private List<BlContentVo> getBlPdf(String baqryId) throws Exception {
        List<BlContentVo> blContentVos = new ArrayList<>();
        //通过身份证号和入办案区时间获取当前的一条笔录
        Map<String, String> params = new HashMap<String, String>();
        params.put("apiKey", FaduConfig.appKey);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("blid", baqryId);
        data.put("wtsj", "0");
        data.put("isOuterId", "1");
        //对查询条件aes加密
        AESTool aes = new AESTool();
        String dataSecret = aes.encrypt(JSON.toJSONString(data), FaduConfig.appSecret);
        params.put("data", dataSecret);
        String bgSig = MD5Util.MD5(FaduConfig.appKey + FaduConfig.appSecret + dataSecret);
        params.put("bdSig", bgSig);
        //通过http请求发送查询条件，返回查询结果
        String blHtml = HttpClientUtil.post(FADU_URL + FaduConfig.method_queryBiluByBiluID, params);
        if (null != blHtml) {
            JSONObject jsonObject = JSONObject.parseObject(blHtml);
            if (0 == jsonObject.getIntValue("resultCode")) {
                JSONObject blObj = jsonObject.getJSONObject("data");
                String kssj = blObj.getString("KSSJ");
                String jssj = blObj.getString("JSSJ");
                String blid = blObj.getString("id");
                String bldxxm = blObj.getString("bldxxm");
                BlContentVo blContentVo = new BlContentVo();
                blContentVo.setKssj(DateUtil.formatFull(kssj, DateStyle.FORMAT_1));
                blContentVo.setJssj(DateUtil.formatFull(jssj, DateStyle.FORMAT_1));
                blContentVo.setBldxxm(bldxxm);
                String pdfFile = blObj.getString("pdf_file");
                if ("1".equals(pdfFile)) {
                    List<String> filePaths = getSigntureFileContent(blid);
                    blContentVo.setFilePath(filePaths);
                } else {
                    logger.warn(blid + ":该笔录没有PDF文书!");
                }
                blContentVos.add(blContentVo);
            } else {
                throw new BizException("法度接口访问失败!");
            }
        }
        return blContentVos;
    }

    @Override
    public List<BlContentVo> getBlContentToPdfPath(String roomName) throws Exception {
        TpBlrecordDo tpBlrecordDo = findBlrecord(null, roomName);
        if (null == tpBlrecordDo) {
            throw new BizException(MessageFormat.format("请确认{0}中有在所人员!", roomName));
        }
        List<BlContentVo> blContentVos = getBlPdf(String.valueOf(tpBlrecordDo.getId()));
        Collections.sort(blContentVos, new Comparator<BlContentVo>() {
            @Override
            public int compare(BlContentVo o1, BlContentVo o2) {
                if (DateUtil.formatTime(o1.getKssj(), DateStyle.FORMAT_FULL) < DateUtil.formatTime(o2.getKssj(), DateStyle.FORMAT_FULL)) {
                    return 1;
                } else if (DateUtil.formatTime(o1.getKssj(), DateStyle.FORMAT_FULL) > DateUtil.formatTime(o2.getKssj(), DateStyle.FORMAT_FULL)) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        List<BlContentVo> resultList = new ArrayList();
        if (null != blContentVos && blContentVos.size() > 0) {
            resultList.add(blContentVos.get(0));
        }
        return resultList;
    }

    @Override
    public List<BlContentVo> getBlContentToJpgPath(String alertNumber) throws Exception {
        List<BlContentVo> blContentVosAll = new ArrayList<>();
        TpBaqryUserDoExample example = new TpBaqryUserDoExample();
        example.createCriteria().andAlertNumberEqualTo(alertNumber);
        List<TpBaqryUserDo> list = tpBaqryUserDoMapperExt.selectByExample(example);
        //同一个警情编号，对应多个嫌疑人
        for (TpBaqryUserDo tpBaqryUserDo : list) {
            TpBlrecordDo tpBlrecordDo = findBlrecord(tpBaqryUserDo.getId(), null);
            if (null == tpBlrecordDo) {
                throw new BizException(MessageFormat.format("请确认{0}有笔录记录!", tpBaqryUserDo.getId()));
            }
            List<BlContentVo> blContentVos = getBlPdf(String.valueOf(tpBlrecordDo.getId()));
            List<String> imagePaths = null;
            for (BlContentVo blContentVo : blContentVos) {
                List<String> filePaths = blContentVo.getFilePath();
                imagePaths = new ArrayList<>();
                for (String filePath : filePaths) {
                    imagePaths.addAll(fileUpload.pdfToImage(filePath));
                }
                blContentVo.setFilePath(imagePaths);
            }
            blContentVosAll.addAll(blContentVos);
        }
        return blContentVosAll;
    }

    private TpBlrecordDo findBlrecord(Long userId, String roomName) throws BizException {
        TpBlrecordDoExample example = new TpBlrecordDoExample();
        if (null == userId && null == roomName) {
            throw new BizException("缺少查询条件");
        }
        TpBlrecordDoExample.Criteria criteria = example.createCriteria();
        if (null != userId) {
            criteria.andRyIdEqualTo(userId);
        }
        if (null != roomName) {
            criteria.andWhddEqualTo(roomName);
        }
        example.setOrderByClause(" gmt_create desc");
        List<TpBlrecordDo> list = tpBlrecordDoMapperExt.selectByExample(example);
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
