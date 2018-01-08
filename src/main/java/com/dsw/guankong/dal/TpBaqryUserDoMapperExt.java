package com.dsw.guankong.dal;

import com.dsw.guankong.dal.base.TpBaqryUserDoMapper;
import com.dsw.guankong.model.TpBaqryUserDo;
import org.apache.ibatis.annotations.Param;

public interface TpBaqryUserDoMapperExt extends TpBaqryUserDoMapper {

    /**
     * 根据询问室名称查询当前在使用的用户
     * @param roomName
     * @return
     */
    public TpBaqryUserDo selectNowUserByRoomNo(@Param("roomName") String roomName);
}
