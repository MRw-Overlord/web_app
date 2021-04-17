package com.epam.jwd.hardziyevich.hr.command;

import com.epam.jwd.hardziyevich.hr.command.page.ShowLoginPageCommand;
import com.epam.jwd.hardziyevich.hr.command.page.ShowMainPageCommand;
import com.epam.jwd.hardziyevich.hr.command.page.ShowNotFoundPageCommand;
import com.epam.jwd.hardziyevich.hr.command.page.ShowSignUpPageCommand;
import com.epam.jwd.hardziyevich.hr.command.page.ShowVacancyPageCommand;
import com.epam.jwd.hardziyevich.hr.command.specificCommand.LoginCommand;
import com.epam.jwd.hardziyevich.hr.command.specificCommand.LogoutCommand;
import com.epam.jwd.hardziyevich.hr.command.specificCommand.SignUpCommand;

public enum CommandManager {
    LOGIN(LoginCommand.getInstance()),
    LOGOUT(LogoutCommand.getInstance()),
    SIGNUP(SignUpCommand.getInstance()),
    SHOW_LOGIN_PAGE(ShowLoginPageCommand.getInstance()),
    SHOW_SIGNUP_PAGE(ShowSignUpPageCommand.getInstance()),
    DEFAULT(ShowMainPageCommand.getInstance()),
    SHOW_VACANCY_PAGE(ShowVacancyPageCommand.getInstance()),
    SHOW_NOT_FOUND_PAGE(ShowNotFoundPageCommand.getInstance());



    private final Command command;

    CommandManager(Command command) {
        this.command = command;
    }

    static Command of(String name){
        for(CommandManager action : values()){
            if(action.name().equalsIgnoreCase(name)){
                return action.command;
            }
        }
        return DEFAULT.command;
    }

}
