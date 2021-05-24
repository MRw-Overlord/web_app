package com.epam.jwd.hardziyevich.hr.command.specificCommand;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.command.page.ShowAdminPageCommand;
import com.epam.jwd.hardziyevich.hr.service.UserService;
import com.epam.jwd.hardziyevich.hr.service.impl.UserServiceImpl;

public class BanUserCommand implements Command {
    private static volatile BanUserCommand instance = null;
    private final UserService userService = UserServiceImpl.getInstance();

    private BanUserCommand() {

    }

    public static BanUserCommand getInstance() {
        if (instance == null) {
            synchronized (BanUserCommand.class) {
                if (instance == null) {
                    instance = new BanUserCommand();
                }
            }
        }
        return instance;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        final String userLogin = requestContext.getParameter("login");
        userService.banUser(userLogin);
        requestContext.setAttribute("redirect", true);
        return ShowAdminPageCommand.getInstance().execute(requestContext);
    }
}

