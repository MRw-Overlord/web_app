package com.epam.jwd.hardziyevich.hr.localization;

import java.util.Locale;
import java.util.ResourceBundle;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Welcome extends ActionSupport {
    private String greeting = null;

    @Override
    public String execute() {
        ResourceBundle bundle = ResourceBundle.getBundle("examples/localization/i18n", getLocale());
        setGreeting(bundle.getString("greeting"));
        return Action.SUCCESS;
    }

    public Locale getLocale() {
        return ActionContext.getContext().getLocale();
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
