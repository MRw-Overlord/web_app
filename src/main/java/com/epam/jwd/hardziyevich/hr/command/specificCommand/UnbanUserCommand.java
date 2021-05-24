package com.epam.jwd.hardziyevich.hr.command.specificCommand;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.command.page.ShowAdminPageCommand;
import com.epam.jwd.hardziyevich.hr.service.UserService;
import com.epam.jwd.hardziyevich.hr.service.impl.UserServiceImpl;

public class UnbanUserCommand implements Command {
    private static volatile UnbanUserCommand instance = null;

    private UnbanUserCommand(){

    }

    public static UnbanUserCommand getInstance(){
        if(instance == null) {
            synchronized (UnbanUserCommand.class) {
                if (instance == null) {
                    instance = new UnbanUserCommand();
                }
            }
        }
        return instance;
    }

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        final String userLogin = requestContext.getParameter("login");
        userService.unbanUser(userLogin);
        requestContext.setAttribute("redirect", true);
        return ShowAdminPageCommand.getInstance().execute(requestContext);
    }
}