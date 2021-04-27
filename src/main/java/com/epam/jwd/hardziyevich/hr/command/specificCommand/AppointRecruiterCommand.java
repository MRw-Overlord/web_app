package com.epam.jwd.hardziyevich.hr.command.specificCommand;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.command.page.ShowAdminPageCommand;
import com.epam.jwd.hardziyevich.hr.command.page.ShowForbiddenPageCommand;
import com.epam.jwd.hardziyevich.hr.service.UserService;
import com.epam.jwd.hardziyevich.hr.service.impl.UserServiceImpl;

public class AppointRecruiterCommand implements Command {
    private static AppointRecruiterCommand instance = null;

    private AppointRecruiterCommand(){

    }

    public static AppointRecruiterCommand getInstance(){
        if(instance == null){
            instance = new AppointRecruiterCommand();
        }
        return instance;
    }

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        final String recruiterLogin = requestContext.getParameter("login");
        if(!userService.appointRecruiter(recruiterLogin)){
            return ShowForbiddenPageCommand.getInstance().execute(requestContext);
        }
        requestContext.setAttribute("redirect", true);
        return ShowAdminPageCommand.getInstance().execute(requestContext);
    }
}
