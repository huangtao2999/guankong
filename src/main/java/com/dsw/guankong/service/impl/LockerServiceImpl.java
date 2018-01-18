package com.dsw.guankong.service.impl;

import com.dsw.guankong.dal.TpLockerDoMapperExt;
import com.dsw.guankong.model.TpLockerDo;
import com.dsw.guankong.model.TpLockerDoExample;
import com.dsw.guankong.model.TpUserDo;
import com.dsw.guankong.service.LockerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 储物柜服务
 *
 * @author huangt
 * @create 2018-01-17 22:24
 **/
@Service
public class LockerServiceImpl implements LockerService {
    @Autowired(required = false)
    private TpLockerDoMapperExt tpLockerDoMapperExt;

    @Override
    public List<TpLockerDo> queryLocker() {
        TpLockerDoExample example = new TpLockerDoExample();
        //添加条件
        List<TpLockerDo> list = tpLockerDoMapperExt.selectByExample(example);
        return list;
    }
}
