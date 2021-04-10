package com.epam.jwd.hardziyevich.hr.command;

import com.epam.jwd.hardziyevich.hr.command.page.ShowAllUserCommand;
import com.epam.jwd.hardziyevich.hr.command.page.ShowMainPageCommand;
import com.epam.jwd.hardziyevich.hr.command.specificCommand.LoginCommand;
import com.epam.jwd.hardziyevich.hr.command.specificCommand.LogoutCommand;

public enum CommandManager {
    LOGIN(LoginCommand.getInstance()),
    LOGOUT(LogoutCommand.getInstance()),
    DEFAULT(ShowMainPageCommand.getInstance()),
    SHOW_USERS(ShowAllUserCommand.getInstance());

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
