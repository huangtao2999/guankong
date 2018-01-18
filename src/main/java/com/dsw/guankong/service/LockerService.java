package com.dsw.guankong.service;

import com.dsw.guankong.dto.LockerResquest;
import com.dsw.guankong.model.TpLockerDo;
import com.dsw.guankong.util.PageResult;

import java.util.List;

/**
 * 储物柜服务
 */
public interface LockerService {
    /**
     * 分页查询储物柜
     */
    public PageResult<TpLockerDo> queryPage(LockerResquest request);
}
