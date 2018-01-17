package com.dsw.guankong.dal.base;

import com.dsw.guankong.model.TpRoleMenuDo;
import com.dsw.guankong.model.TpRoleMenuDoExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TpRoleMenuDoMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table tp_role_menu
     *
     * @mbggenerated Wed Jan 17 13:48:27 CST 2018
     */
    int countByExample(TpRoleMenuDoExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table tp_role_menu
     *
     * @mbggenerated Wed Jan 17 13:48:27 CST 2018
     */
    int deleteByExample(TpRoleMenuDoExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table tp_role_menu
     *
     * @mbggenerated Wed Jan 17 13:48:27 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table tp_role_menu
     *
     * @mbggenerated Wed Jan 17 13:48:27 CST 2018
     */
    int insert(TpRoleMenuDo record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table tp_role_menu
     *
     * @mbggenerated Wed Jan 17 13:48:27 CST 2018
     */
    int insertSelective(TpRoleMenuDo record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table tp_role_menu
     *
     * @mbggenerated Wed Jan 17 13:48:27 CST 2018
     */
    List<TpRoleMenuDo> selectByExample(TpRoleMenuDoExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table tp_role_menu
     *
     * @mbggenerated Wed Jan 17 13:48:27 CST 2018
     */
    TpRoleMenuDo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table tp_role_menu
     *
     * @mbggenerated Wed Jan 17 13:48:27 CST 2018
     */
    int updateByExampleSelective(@Param("record") TpRoleMenuDo record, @Param("example") TpRoleMenuDoExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table tp_role_menu
     *
     * @mbggenerated Wed Jan 17 13:48:27 CST 2018
     */
    int updateByExample(@Param("record") TpRoleMenuDo record, @Param("example") TpRoleMenuDoExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table tp_role_menu
     *
     * @mbggenerated Wed Jan 17 13:48:27 CST 2018
     */
    int updateByPrimaryKeySelective(TpRoleMenuDo record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table tp_role_menu
     *
     * @mbggenerated Wed Jan 17 13:48:27 CST 2018
     */
    int updateByPrimaryKey(TpRoleMenuDo record);
}