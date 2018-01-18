package com.lusifer.myshop.module.sys.security;

import com.lusifer.myshop.module.sys.dto.LoginDTO;
import com.lusifer.myshop.module.user.entity.TbUser;
import com.lusifer.myshop.module.user.service.TbUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统安全认证实现类
 * <p>Title: SystemAuthorizingRealm</p>
 * <p>Description: </p>
 *
 * @author Lusifer
 * @version 1.0.0
 * @date 2018/1/15 16:08
 */
@Service
public class SystemAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private TbUserService tbUserService;

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 认证回调函数, 登录时调用
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        // 验证验证码
        String validateCode = (String) getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if (!validateCode.equals(usernamePasswordToken.getValidateCode())) {
            throw new AuthenticationException("msg:验证码错误，请重试");
        }

        // 用户名/密码校验（认证）
        TbUser tbUser = tbUserService.getByLoginId(usernamePasswordToken.getUsername());
        if (tbUser != null) {
            String loginId = usernamePasswordToken.getUsername();
            String loginPwd = new String(usernamePasswordToken.getPassword());
            String isRemember = usernamePasswordToken.isRememberMe() ? "on" : "";
            return new SimpleAuthenticationInfo(new LoginDTO(loginId, loginPwd, isRemember, null), tbUser.getPassword(), getName());
        }

        // 认证失败
        return null;
    }

    /**
     * 获取 Shiro 管理的 Session
     *
     * @return
     */
    private Session getSession() {
        Subject subject = SecurityUtils.getSubject();
        // 是否创建新会话
        Session session = subject.getSession(false);
        if (session == null) {
            session = subject.getSession();
        }
        return session;
    }
}
