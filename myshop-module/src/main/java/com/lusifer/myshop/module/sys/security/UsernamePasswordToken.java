package com.lusifer.myshop.module.sys.security;

/**
 * 用户身份令牌
 * <p>Title: UsernamePasswordToken</p>
 * <p>Description: </p>
 *
 * @author Lusifer
 * @version 1.0.0
 * @date 2018/1/15 15:46
 */
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {
    // 扩展验证码属性
    private String validateCode;

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public UsernamePasswordToken() {
        super();
    }

    public UsernamePasswordToken(String username, String password, boolean rememberMe, String host, String validateCode) {
        super(username, password, rememberMe, host);
        this.validateCode = validateCode;
    }
}
