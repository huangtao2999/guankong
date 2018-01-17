package com.dsw.guankong.controller.login.rpc;

import com.dsw.guankong.model.TpUserDo;
import com.dsw.guankong.service.LoginService;
import com.dsw.guankong.util.ActionResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录相关请求
 *
 * @author huangt
 * @create 2018-01-16 17:58
 **/
@RestController
@RequestMapping("/loginRpc")
public class LoginRpc {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public TpUserDo login(String userName, String passWord, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return loginService.login(userName, passWord, request, response);
    }
}
