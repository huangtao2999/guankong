package com.dsw.guankong.controller;

import com.dsw.guankong.service.FaduService;
import com.dsw.guankong.service.LoginService;
import com.dsw.guankong.util.ActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/LoginController")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @RequestMapping("/createOrder")
    public ActionResult createOrder(String param) {
        return loginService.syncFadu(param);
    }

}
