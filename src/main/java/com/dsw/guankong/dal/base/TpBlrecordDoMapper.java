package com.dsw.guankong.dal.base;

import com.dsw.guankong.model.TpBlrecordDo;
import com.dsw.guankong.model.TpBlrecordDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TpBlrecordDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_blrecord
     *
     * @mbggenerated Wed Jan 17 18:34:56 CST 2018
     */
    int countByExample(TpBlrecordDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_blrecord
     *
     * @mbggenerated Wed Jan 17 18:34:56 CST 2018
     */
    int deleteByExample(TpBlrecordDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_blrecord
     *
     * @mbggenerated Wed Jan 17 18:34:56 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_blrecord
     *
     * @mbggenerated Wed Jan 17 18:34:56 CST 2018
     */
    int insert(TpBlrecordDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_blrecord
     *
     * @mbggenerated Wed Jan 17 18:34:56 CST 2018
     */
    int insertSelective(TpBlrecordDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_blrecord
     *
     * @mbggenerated Wed Jan 17 18:34:56 CST 2018
     */
    List<TpBlrecordDo> selectByExample(TpBlrecordDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_blrecord
     *
     * @mbggenerated Wed Jan 17 18:34:56 CST 2018
     */
    TpBlrecordDo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_blrecord
     *
     * @mbggenerated Wed Jan 17 18:34:56 CST 2018
     */
    int updateByExampleSelective(@Param("record") TpBlrecordDo record, @Param("example") TpBlrecordDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_blrecord
     *
     * @mbggenerated Wed Jan 17 18:34:56 CST 2018
     */
    int updateByExample(@Param("record") TpBlrecordDo record, @Param("example") TpBlrecordDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_blrecord
     *
     * @mbggenerated Wed Jan 17 18:34:56 CST 2018
     */
    int updateByPrimaryKeySelective(TpBlrecordDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_blrecord
     *
     * @mbggenerated Wed Jan 17 18:34:56 CST 2018
     */
    int updateByPrimaryKey(TpBlrecordDo record);
}