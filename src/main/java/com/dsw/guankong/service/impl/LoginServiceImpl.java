package com.dsw.guankong.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dsw.guankong.constant.CommConfig;
import com.dsw.guankong.constant.FaduConfig;
import com.dsw.guankong.service.LoginService;
import com.dsw.guankong.util.*;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private FileUpload fileUpload;
    @Override
    public ActionResult syncFadu(String param) {
        ActionResult actionResult = new ActionResult();
        JSONObject jsonObject = JSONObject.parseObject(param);
        String idType = jsonObject.get("idType").toString();
        String idValue = jsonObject.get("idValue").toString();
        String anInfo = jsonObject.get("ajInfo").toString();

        JSONObject json = new JSONObject();
        json.put("IdType",idType);
        json.put("IdValue",idValue);

        json.put("AppKey", FaduConfig.appKey);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime localDateTime  = LocalDateTime.now();
        String time = dateTimeFormatter.format(localDateTime);
        json.put("Time",time);
        String sign = MD5Util.MD5(FaduConfig.appKey+idValue+FaduConfig.appSecret+time);
        json.put("Sign",sign);
        if(null!=anInfo){
            String fileName = sign+".txt";
            String gxfileDir = fileUpload.saveRemote(anInfo,fileName);
            json.put("Tag",gxfileDir);
        }
        actionResult.setContent(FaduConfig.prefix_order+FuncComm.getBase64(json.toString()));
        return actionResult;
    }
}
