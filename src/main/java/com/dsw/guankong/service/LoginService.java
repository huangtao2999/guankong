package com.dsw.guankong.service;

import com.dsw.guankong.model.TpUserDo;
import com.dsw.guankong.util.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {

    /**
     * 单点登录 同步笔录基础信息
     */
    public ActionResult syncFadu(String param);

    /**
     * 根据用户账号和密码登录系统
     */
    public TpUserDo login(String userName, String passWord, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
