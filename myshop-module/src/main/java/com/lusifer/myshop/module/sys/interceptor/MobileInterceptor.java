package com.lusifer.myshop.module.sys.interceptor;

import com.lusifer.myshop.common.utils.UserAgentUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 移动端拦截器
 * <p>Title: MobileInterceptor</p>
 * <p>Description: </p>
 *
 * @author Lusifer
 * @version 1.0.0
 * @date 2018/1/12 14:40
 */
public class MobileInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        // 主要用于控制跳转
        if (modelAndView != null) {
            // 是移动端
            if (UserAgentUtils.isMobileOrTablet(httpServletRequest) && !StringUtils.startsWithIgnoreCase(modelAndView.getViewName(), "redirect:")) {
                modelAndView.setViewName("mobile" + modelAndView.getViewName().replace("modules", ""));
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
