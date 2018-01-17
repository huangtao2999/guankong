package com.dsw.guankong.dal;

import com.dsw.guankong.dal.base.TpBaqryUserDoMapper;
import com.dsw.guankong.model.TpBaqryUserDo;

import org.apache.ibatis.annotations.Param;

public interface TpBaqryUserDoMapperExt extends TpBaqryUserDoMapper {

    /**
     * 根据询问室名称查询当前在使用的用户
     */
    public TpBaqryUserDo selectNowUserByRoomNo(@Param("roomName") String roomName);

    /**
     * 根据用户获取当前关押的房间名称
     */
    public String selectRoomNameByUserId(@Param("userId") Long userId);
}
