package com.dsw.guankong.util;

/**
 * 时间格式枚举类
 */
public enum DateStyle {
    FORMAT_FULL("yyyy-MM-dd HH:mm:ss"),
    FORMAT_1("yyyyMMddHHmmss"),
    FORMAT_2("yyyy-MM-dd");

    private String pattern;
    private DateStyle(String pattern){
        this.pattern = pattern;
    }
    public String getPattern() {
        return pattern;
    }
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
