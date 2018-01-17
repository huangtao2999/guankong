package com.dsw.guankong.dal.base;

import com.dsw.guankong.model.TpRoleDo;
import com.dsw.guankong.model.TpRoleDoExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TpRoleDoMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table tp_role
     *
     * @mbggenerated Wed Jan 17 13:48:27 CST 2018
     */
    int countByExample(TpRoleDoExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table tp_role
     *
     * @mbggenerated Wed Jan 17 13:48:27 CST 2018
     */
    int deleteByExample(TpRoleDoExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table tp_role
     *
     * @mbggenerated Wed Jan 17 13:48:27 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table tp_role
     *
     * @mbggenerated Wed Jan 17 13:48:27 CST 2018
     */
    int insert(TpRoleDo record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table tp_role
     *
     * @mbggenerated Wed Jan 17 13:48:27 CST 2018
     */
    int insertSelective(TpRoleDo record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table tp_role
     *
     * @mbggenerated Wed Jan 17 13:48:27 CST 2018
     */
    List<TpRoleDo> selectByExample(TpRoleDoExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table tp_role
     *
     * @mbggenerated Wed Jan 17 13:48:27 CST 2018
     */
    TpRoleDo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table tp_role
     *
     * @mbggenerated Wed Jan 17 13:48:27 CST 2018
     */
    int updateByExampleSelective(@Param("record") TpRoleDo record, @Param("example") TpRoleDoExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table tp_role
     *
     * @mbggenerated Wed Jan 17 13:48:27 CST 2018
     */
    int updateByExample(@Param("record") TpRoleDo record, @Param("example") TpRoleDoExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table tp_role
     *
     * @mbggenerated Wed Jan 17 13:48:27 CST 2018
     */
    int updateByPrimaryKeySelective(TpRoleDo record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table tp_role
     *
     * @mbggenerated Wed Jan 17 13:48:27 CST 2018
     */
    int updateByPrimaryKey(TpRoleDo record);
}