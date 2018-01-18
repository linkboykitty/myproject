package com.lusifer.myshop.module.sys.web;

import com.lusifer.myshop.common.utils.IDUtils;
import com.lusifer.myshop.module.sys.dto.LoginDTO;
import com.lusifer.myshop.module.user.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    /**
     * 跳转登录页
     *
     * @return
     */
    @RequestMapping(value = {"login", ""}, method = RequestMethod.GET)
    public String login() {
        LoginDTO principal = UserUtils.getPrincipal();
        if (principal != null) {
            return "redirect:/main";
        }
        return "modules/sys/login";
    }

    /**
     * 登录失败的处理，真正 POST 请求在过滤器中完成
     *
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loginFail(HttpServletRequest request) {
        // 验证失败清空验证码
        request.getSession().setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, IDUtils.genId());
        return "modules/sys/login";
    }
}
