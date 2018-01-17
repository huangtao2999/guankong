package com.dsw.guankong.service;

import com.dsw.guankong.model.TpMenuDo;

import java.util.List;


/**
 * 菜单服务
 */
public interface MenuSerivce {

    public List<TpMenuDo> findMenuByUserId(Long userId);
}
