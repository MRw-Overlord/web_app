package com.epam.jwd.hardziyevich.hr.localization;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
    private String login;
    private String password;

    @Override
    public String execute() {
        return SUCCESS;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
