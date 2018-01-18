package com.lusifer.myshop.module.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * <p>Title: MainController</p>
 * <p>Description: </p>
 *
 * @author Lusifer
 * @version 1.0.0
 * @date 2018/1/8 11:56
 */
@Controller
public class MainController {
    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String main() {
        return "modules/sys/index";
    }
}
