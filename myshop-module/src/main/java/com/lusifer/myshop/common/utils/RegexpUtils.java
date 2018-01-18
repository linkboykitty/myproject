package com.lusifer.myshop.common.utils;

/**
 * 正则表达式工具类
 * <p>Title: RegexpUtils</p>
 * <p>Description: </p>
 *
 * @author Lusifer
 * @version 1.0.0
 * @date 2018/1/10 11:13
 */
public final class RegexpUtils {
    private RegexpUtils() {}

    public static final String PHONE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    public static final String EMAIL = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";
}
