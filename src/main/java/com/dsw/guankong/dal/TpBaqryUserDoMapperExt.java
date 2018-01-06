package com.dsw.guankong.dal;

import com.dsw.guankong.dal.base.TpBaqryUserDoMapper;
import com.dsw.guankong.model.TpBaqryUserDo;
import org.apache.ibatis.annotations.Param;

public interface TpBaqryUserDoMapperExt extends TpBaqryUserDoMapper {

    /**
     * 根据询问室房间号查询当前在使用的用户
     * @param roomNo
     * @return
     */
    public TpBaqryUserDo selectNowUserByRoomNo(@Param("roomNo") String roomNo);
}
