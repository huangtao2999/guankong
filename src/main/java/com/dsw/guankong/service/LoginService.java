package com.dsw.guankong.service;

import com.dsw.guankong.util.ActionResult;

public interface LoginService {

    /**
     * 单点登录 同步笔录基础信息
     * @param param
     * @return
     */
    public ActionResult syncFadu(String param);
}
