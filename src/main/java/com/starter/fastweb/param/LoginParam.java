package com.starter.fastweb.param;

import java.io.Serializable;

/**
 * 登录入参
 */
public class LoginParam implements Serializable {

    private static final long serialVersionUID = -6424064771237377628L;

    /**
     * 登录账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String password;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
