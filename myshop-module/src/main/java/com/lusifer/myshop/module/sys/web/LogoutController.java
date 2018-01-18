package com.lusifer.myshop.module.sys.web;

import com.lusifer.myshop.module.sys.dto.LoginDTO;
import com.lusifer.myshop.module.user.utils.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogoutController {

    /**
     * 注销用户
     * @return
     */
    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request) {
        LoginDTO principal = UserUtils.getPrincipal();
        if (principal != null) {
            // 交给 Shiro 操作
            SecurityUtils.getSubject().logout();
        }
        return "redirect:/login";
    }
}
