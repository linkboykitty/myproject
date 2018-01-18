package com.lusifer.myshop.module.user.service;

import com.lusifer.myshop.module.sys.dto.LoginDTO;
import com.lusifer.myshop.module.user.entity.TbUser;

import java.util.List;

public interface TbUserService {
    /**
     * 登录
     * @param loginDTO
     * @return
     */
    @Deprecated
    public TbUser login(LoginDTO loginDTO);

    /**
     * 根据 ID 获取用户信息
     * @param id
     * @return
     */
    public TbUser getById(Long id);

    /**
     * 保存用户信息
     * @param tbUser
     */
    public void save(TbUser tbUser);

    /**
     * 获取用户信息列表
     */
    public List<TbUser> list();

    /**
     * 校验用户信息
     * @param tbUser
     * @return
     */
    public boolean check(TbUser tbUser);

    /**
     * 根据 LoginId 获取用户信息
     * @param loginId
     * @return
     */
    public TbUser getByLoginId(String loginId);
}
