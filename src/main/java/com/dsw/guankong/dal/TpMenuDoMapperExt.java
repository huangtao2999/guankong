package com.dsw.guankong.dal;

import com.dsw.guankong.dal.base.TpMenuDoMapper;
import com.dsw.guankong.model.TpMenuDo;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TpMenuDoMapperExt extends TpMenuDoMapper {

    public List<TpMenuDo> selectTpMenuDoByUserId(@Param("userId") long userId);
}