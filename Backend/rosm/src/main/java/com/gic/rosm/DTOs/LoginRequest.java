package com.gic.rosm.DTOs;

public class LoginRequest {
    private String login_name;
    private String password;

    public String getLoginName() {
        return login_name;
    }

    public void setLoginName(String loginName) {
        this.login_name = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
