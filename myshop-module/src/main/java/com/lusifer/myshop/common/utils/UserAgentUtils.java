package com.lusifer.myshop.common.utils;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户代理工具类
 * <p>Title: UserAgentUtils</p>
 * <p>Description: </p>
 *
 * @author Lusifer
 * @version 1.0.0
 * @date 2018/1/12 14:58
 */
public final class UserAgentUtils {
    private UserAgentUtils() {

    }

    /**
     * 获取用户代理对象
     *
     * @param request
     * @return
     */
    public static UserAgent getUserAgent(HttpServletRequest request) {
        return UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
    }

    /**
     * 获取设备类型
     *
     * @param request
     * @return
     */
    public static DeviceType getDeviceType(HttpServletRequest request) {
        return getUserAgent(request).getOperatingSystem().getDeviceType();
    }

    /**
     * 是否是 PC
     *
     * @param request
     * @return
     */
    public static boolean isComputer(HttpServletRequest request) {
        return DeviceType.COMPUTER.equals(getDeviceType(request));
    }

    /**
     * 是否是手机
     *
     * @param request
     * @return
     */
    public static boolean isMobile(HttpServletRequest request) {
        return DeviceType.MOBILE.equals(getDeviceType(request));
    }

    /**
     * 是否是平板
     *
     * @param request
     * @return
     */
    public static boolean isTablet(HttpServletRequest request) {
        return DeviceType.TABLET.equals(getDeviceType(request));
    }

    /**
     * 是否是手机和平板
     *
     * @param request
     * @return
     */
    public static boolean isMobileOrTablet(HttpServletRequest request) {
        DeviceType deviceType = getDeviceType(request);
        return DeviceType.MOBILE.equals(deviceType) || DeviceType.TABLET.equals(deviceType);
    }

    /**
     * 获取浏览类型
     *
     * @param request
     * @return
     */
    public static Browser getBrowser(HttpServletRequest request) {
        return getUserAgent(request).getBrowser();
    }

    /**
     * 是否 IE 版本是否小于等于 IE8
     *
     * @param request
     * @return
     */
    public static boolean isLteIE8(HttpServletRequest request) {
        Browser browser = getBrowser(request);
        return Browser.IE5.equals(browser) || Browser.IE6.equals(browser) || Browser.IE7.equals(browser) || Browser.IE8.equals(browser);
    }
}
