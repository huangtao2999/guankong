package com.dsw.guankong.controller.login.html;

import com.dsw.guankong.constant.CommConfig;
import com.dsw.guankong.util.CookieUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录页面控制
 *
 * @author huangt
 * @create 2018-01-16 17:41
 **/
@Controller
@RequestMapping("/Login")
public class LoginHtml {

    @RequestMapping("/login")
    public String login(Model model) {
        return "/login/login";
    }

    @RequestMapping("/quit")
    public String quit(Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            CookieUtil.removeCookie(CommConfig.GUANKONG_USER, request, response);
        } catch (Exception e) {
            return "/login/login";
        }
        return "/login/login";
    }
}
