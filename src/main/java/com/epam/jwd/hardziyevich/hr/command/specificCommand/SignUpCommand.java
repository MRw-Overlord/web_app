package com.epam.jwd.hardziyevich.hr.command.specificCommand;


import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.command.page.ShowMainPageCommand;
import com.epam.jwd.hardziyevich.hr.command.page.ShowSignUpPageCommand;
import com.epam.jwd.hardziyevich.hr.model.entity.User;
import com.epam.jwd.hardziyevich.hr.service.UserService;
import com.epam.jwd.hardziyevich.hr.service.impl.UserServiceImpl;

import javax.servlet.http.HttpSession;

public class SignUpCommand implements Command {

    private static SignUpCommand instance = null;

    private SignUpCommand(){

    }

    public static SignUpCommand getInstance(){
        if(instance == null){
            instance = new SignUpCommand();
        }
        return instance;
    }

    private final UserService service = UserServiceImpl.getInstance();

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        final HttpSession session = requestContext.getSession();
        if (session.getAttribute("login") != null) {
            return ShowMainPageCommand.getInstance().execute(requestContext);
        }
        final String login = String.valueOf(requestContext.getParameter("login"));
        final String password = String.valueOf(requestContext.getParameter("password"));
        final User user = new User(login, password);
        ResponseContext response;
        if (password.length() >= 6 && password.length() <= 30 && login.length() <= 15 && login.matches("[A-Za-z0-9_]{1,15}")) {
            if (service.create(user)) {
                response = LoginCommand.getInstance().execute(requestContext);
            } else {
                requestContext.setAttribute("signupError", "login");
                response = ShowSignUpPageCommand.getInstance().execute(requestContext);
            }
        } else {
            requestContext.setAttribute("signupError", "password");
            response = ShowSignUpPageCommand.getInstance().execute(requestContext);
        }
        return response;
    }
}
