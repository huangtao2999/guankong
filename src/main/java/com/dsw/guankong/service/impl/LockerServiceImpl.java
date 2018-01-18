package com.dsw.guankong.service.impl;

import com.dsw.guankong.dal.TpLockerDoMapperExt;
import com.dsw.guankong.dto.LockerResquest;
import com.dsw.guankong.model.TpLockerDo;
import com.dsw.guankong.model.TpLockerDoExample;
import com.dsw.guankong.model.TpUserDo;
import com.dsw.guankong.service.LockerService;
import com.dsw.guankong.util.FuncComm;
import com.dsw.guankong.util.Page;
import com.dsw.guankong.util.PageResult;

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
    public PageResult<TpLockerDo> queryPage(LockerResquest request) {
        PageResult<TpLockerDo> pageResult = new PageResult<>();
        TpLockerDoExample example = new TpLockerDoExample();
        TpLockerDoExample.Criteria criteria = example.createCriteria();
        //添加条件
        if (!FuncComm.isEmpty(request.getLockerNo())) {
            criteria.andLockerNoLike("%" + request.getLockerNo() + "%");
        }
        int count = tpLockerDoMapperExt.countByExample(example);
        Page page = new Page(request.getPage(), request.getLimit(), count);
        example.setPage(page);
        List<TpLockerDo> list = tpLockerDoMapperExt.selectByExample(example);
        pageResult.setData(list);
        pageResult.setCount(count);
        return pageResult;
    }
}
