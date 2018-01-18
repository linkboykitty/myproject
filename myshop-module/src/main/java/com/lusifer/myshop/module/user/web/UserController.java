package com.lusifer.myshop.module.user.web;

import com.lusifer.myshop.common.web.BaseController;
import com.lusifer.myshop.module.user.entity.TbUser;
import com.lusifer.myshop.module.user.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "user")
public class UserController extends BaseController {

    @Autowired
    private TbUserService tbUserService;

    @ModelAttribute
    public TbUser getById(@RequestParam(required = false) Long id) {
        TbUser entity = null;
        if (id != null) {
            entity = tbUserService.getById(id);
        } else {
            entity = new TbUser();
        }

        return entity;
    }

    /**
     * 跳转用户表单页面
     *
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "modules/user/userForm";
    }

    /**
     * 跳转用户列表页面
     * <p>Title: UserController</p>
     * <p>Description: </p>
     *
     * @author Lusifer
     * @version 1.0.0
     * @date 2018/1/10 17:05
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        List<TbUser> list = tbUserService.list();
        model.addAttribute("list", list);
        return "modules/user/userList";
    }

    /* ------------------------------------------------------------- 业务处理 ------------------------------------------------------------- */

    /**
     * 保存用户信息
     *
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbUser tbUser, Model model, RedirectAttributes redirectAttributes) {
        // 服务器端数据验证
        if (!beanValidator(model, tbUser)) {
            return form();
        }

        tbUserService.save(tbUser);
        addMessage(redirectAttributes, "保存用户成功");
        return "redirect:/user/list";
    }

    /**
     * 检测用户有效性
     * @param tbUser
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "check")
    public boolean check(TbUser tbUser) {
        return tbUserService.check(tbUser);
    }

    /* ------------------------------------------------------------- 私有业务 ------------------------------------------------------------- */
}
