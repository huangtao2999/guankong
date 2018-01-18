package com.dsw.guankong.service;

import com.dsw.guankong.model.TpLockerDo;

import java.util.List;

/**
 * 储物柜服务
 */
public interface LockerService {
    /**
     * 分页查询储物柜
     */
    public List<TpLockerDo> queryLocker();
}
