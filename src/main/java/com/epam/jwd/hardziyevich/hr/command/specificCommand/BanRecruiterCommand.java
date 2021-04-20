package com.epam.jwd.hardziyevich.hr.command.specificCommand;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.command.page.ShowAdminPageCommand;
import com.epam.jwd.hardziyevich.hr.service.UserService;
import com.epam.jwd.hardziyevich.hr.service.impl.UserServiceImpl;

public class BanRecruiterCommand implements Command {
    private static BanRecruiterCommand instance = null;

    private BanRecruiterCommand(){

    }

    public static BanRecruiterCommand getInstance(){
        if(instance == null){
            instance = new BanRecruiterCommand();
        }
        return instance;
    }

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        final String recruiterLogin = requestContext.getParameter("login");
        userService.banRecruiter(recruiterLogin);
        requestContext.setAttribute("redirect", true);
        return ShowAdminPageCommand.getInstance().execute(requestContext);
    }
}
