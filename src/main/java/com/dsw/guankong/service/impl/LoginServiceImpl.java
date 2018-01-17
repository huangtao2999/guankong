package com.dsw.guankong.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dsw.guankong.constant.FaduConfig;
import com.dsw.guankong.dal.TpBaqryUserDoMapperExt;
import com.dsw.guankong.dal.TpBlrecordDoMapperExt;
import com.dsw.guankong.model.TpBlrecordDo;
import com.dsw.guankong.service.LoginService;
import com.dsw.guankong.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    private FileUpload fileUpload;
    @Autowired(required = false)
    private TpBlrecordDoMapperExt tpBlrecordDoMapperExt;
    @Autowired(required = false)
    private TpBaqryUserDoMapperExt tpBaqryUserDoMapperExt;

    @Override
    public ActionResult syncFadu(String param) {
        ActionResult actionResult = new ActionResult();
        JSONObject jsonObject = JSONObject.parseObject(param);
        String idType = jsonObject.get("idType").toString();
        String idValue = jsonObject.get("idValue").toString();
        JSONObject anjjson = jsonObject.getJSONObject("ajInfo");
        //需要生成blreord记录到outerId
        TpBlrecordDo tpBlrecordDo = new TpBlrecordDo();
        Long userId = anjjson.getLong("OuterId");
        tpBlrecordDo.setRyId(userId);
        tpBlrecordDo.setGmtCreate(new Date());
        String roomName = tpBaqryUserDoMapperExt.selectRoomNameByUserId(userId);
        if (null == roomName) {
            actionResult.setSuccess(false);
            actionResult.setErrorMsg("请确认人员在询/讯问室内");
            return actionResult;
        }
        tpBlrecordDo.setWhdd(roomName);
        tpBlrecordDoMapperExt.insertSelective(tpBlrecordDo);
        //修改外部ID为笔录记录ID
        anjjson.put("outerId", tpBlrecordDo.getId().toString());
        //处理逻辑
        String anInfo = anjjson.toString();
        JSONObject json = new JSONObject();
        json.put("IdType", idType);
        json.put("IdValue", idValue);

        json.put("AppKey", FaduConfig.appKey);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String time = dateTimeFormatter.format(localDateTime);
        json.put("Time", time);
        String sign = MD5Util.MD5(FaduConfig.appKey + idValue + FaduConfig.appSecret + time);
        json.put("Sign", sign);
        if (null != anInfo) {
            String fileName = sign + ".txt";
            String gxfileDir = fileUpload.saveRemote(anInfo, fileName);
            json.put("Tag", gxfileDir);
        }
        actionResult.setContent(FaduConfig.prefix_order + FuncComm.getBase64(json.toString()));
        return actionResult;
    }
}
