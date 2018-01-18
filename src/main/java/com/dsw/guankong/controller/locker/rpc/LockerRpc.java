package com.dsw.guankong.controller.locker.rpc;

import com.dsw.guankong.dto.LockerResquest;
import com.dsw.guankong.model.TpLockerDo;
import com.dsw.guankong.service.LockerService;
import com.dsw.guankong.util.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 储物柜rpc
 *
 * @author huangt
 * @create 2018-01-18 8:40
 **/
@RestController
@RequestMapping("/Locker")
public class LockerRpc {
    @Autowired
    private LockerService lockerService;

    @RequestMapping("/queryPage")
    public PageResult<TpLockerDo> queryPage(LockerResquest resquest) {
        PageResult<TpLockerDo> pageResult = lockerService.queryPage(resquest);
        return pageResult;
    }
}
