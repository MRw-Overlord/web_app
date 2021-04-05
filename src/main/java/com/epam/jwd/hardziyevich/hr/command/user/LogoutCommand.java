package com.epam.jwd.hardziyevich.hr.command.user;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.command.page.ShowMainPageCommand;

public class LogoutCommand implements Command {
    private static LogoutCommand instance;

    private LogoutCommand(){

    }

    public static LogoutCommand getInstance(){
        if(instance == null){
            instance = new LogoutCommand();
        }
        return instance;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.invalidateSession();
        return ShowMainPageCommand.getInstance().execute(requestContext);
    }
}
