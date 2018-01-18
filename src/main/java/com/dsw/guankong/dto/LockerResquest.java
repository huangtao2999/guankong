package com.dsw.guankong.dto;

import com.dsw.guankong.util.BaseRequest;

import lombok.Data;

/**
 * locker查询请求头
 *
 * @author huangt
 * @create 2018-01-18 9:49
 **/
@Data
public class LockerResquest extends BaseRequest {

    private String lockerNo;
}
