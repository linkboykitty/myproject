package com.lusifer.myshop.module.user.service.impl;

import com.lusifer.myshop.common.utils.IDUtils;
import com.lusifer.myshop.module.sys.dto.LoginDTO;
import com.lusifer.myshop.module.user.entity.TbUser;
import com.lusifer.myshop.module.user.mapper.TbUserMapper;
import com.lusifer.myshop.module.user.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * 用户管理
 * <p>Title: TbUserServiceImpl</p>
 * <p>Description: </p>
 *
 * @author Lusifer
 * @version 1.0.0
 * @date 2018/1/8 10:31
 */
@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserMapper tbUserMapper;

    /**
     * 使用用户名和密码登录
     * @param loginDTO
     * @return
     */
    @Override
    public TbUser login(LoginDTO loginDTO) {
        TbUser result = null;

        // 封装查询条件
        Example example = new Example(TbUser.class);
        example.createCriteria()
                .andEqualTo("email", loginDTO.getLoginId())
                .orEqualTo("username", loginDTO.getLoginId())
                .orEqualTo("phone", loginDTO.getLoginId());

        // 根据条件查询
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);

        // 判断用户是否存在
        if (tbUsers != null && tbUsers.size() == 1) {
            result = tbUsers.get(0);

            // 判断密码是否匹配
            String password = DigestUtils.md5DigestAsHex(loginDTO.getLoginPwd().getBytes());
            if (password.equals(result.getPassword())) {
                return result;
            }
        }

        // 登录失败
        else {
            // ...  记录登录失败的日志
        }

        return null;
    }

    @Override
    public TbUser getById(Long id) {
        return tbUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(TbUser tbUser) {
        // 有 ID 表示编辑
        if (tbUser.getId() != null) {
            // 密码字段不为并且长度为 0 时，则将密码字段设为 null
            if (StringUtils.isBlank(tbUser.getPassword())) {
                tbUser.setPassword(null);
            }

            // 密码加密
            else {
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
            }
            tbUserMapper.updateByPrimaryKeySelective(tbUser);
        }

        // 新增
        else {
            tbUser.setId(IDUtils.genId());
            // 新增用户时密码需要加密
            tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
            tbUser.setCreated(new Date());
            tbUser.setUpdated(new Date());
            tbUserMapper.insert(tbUser);
        }
    }

    @Override
    public List<TbUser> list() {
        return tbUserMapper.selectAll();
    }

    @Override
    public boolean check(TbUser tbUser) {
        // 检测用户名是否唯一
        if (!StringUtils.isBlank(tbUser.getUsername())) {
            return checkUsername(tbUser.getUsername());
        }

        // 检测邮箱是否唯一
        else if (!StringUtils.isBlank(tbUser.getEmail())) {
            return checkEmail(tbUser.getEmail());
        }

        // 检测手机号是否唯一
        else if (!StringUtils.isBlank(tbUser.getPhone())) {
            return checkPhone(tbUser.getPhone());
        }

        return true;
    }

    @Override
    public TbUser getByLoginId(String loginId) {
        TbUser tbUser = null;

        // 封装查询条件
        Example example = new Example(TbUser.class);
        example.createCriteria()
                .andEqualTo("email", loginId)
                .orEqualTo("username", loginId)
                .orEqualTo("phone", loginId);

        // 根据条件查询
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);

        // 直接获取用户
        if (tbUsers != null && tbUsers.size() == 1) {
            tbUser = tbUsers.get(0);
        }

        return tbUser;
    }

    /**
     * 检测用户名是否唯一
     * @param username
     * @return
     */
    private boolean checkUsername(String username) {
        if (selectCountByProperty("username", username) >= 1) {
            return false;
        }

        return true;
    }

    /**
     * 检测手机号是否唯一
     * @param phone
     * @return
     */
    private boolean checkPhone(String phone) {
        if (selectCountByProperty("phone", phone) >= 1) {
            return false;
        }

        return true;
    }

    /**
     * 检测邮箱是否唯一
     * @param email
     * @return
     */
    private boolean checkEmail(String email) {
        if (selectCountByProperty("email", email) >= 1) {
            return false;
        }

        return true;
    }

    /**
     * 根据属性和值查询笔数
     * @param property
     * @param value
     * @return
     */
    private int selectCountByProperty(String property, Object value) {
        Example example = new Example(TbUser.class);
        example.createCriteria().andEqualTo(property, value);
        return tbUserMapper.selectCountByExample(example);
    }
}
