package com.epam.jwd.hardziyevich.hr.command.specificCommand;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.command.page.ShowLoginPageCommand;
import com.epam.jwd.hardziyevich.hr.command.page.ShowMainPageCommand;
import com.epam.jwd.hardziyevich.hr.model.entityDto.UserDto;
import com.epam.jwd.hardziyevich.hr.pool.ActiveUserPool;
import com.epam.jwd.hardziyevich.hr.service.UserService;
import com.epam.jwd.hardziyevich.hr.service.impl.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {

    private static LoginCommand instance = null;

    private LoginCommand() {

    }

    public static LoginCommand getInstance() {
        if (instance == null) {
            instance = new LoginCommand();
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
        final Optional<UserDto> userDto = service.loginUser(login, password);
        ResponseContext response;
        if (userDto.isPresent()) {
            UserDto user = userDto.get();
            requestContext.setSessionAttribute("login", user.getLogin());
            requestContext.setSessionAttribute("firstName", user.getFirstName());
            requestContext.setSessionAttribute("lastName", user.getLastName());
            requestContext.setSessionAttribute("age", user.getAge());
            requestContext.setSessionAttribute("email", user.getEmail());
            requestContext.setSessionAttribute("role", user.getRole().getRoleName());
            ActiveUserPool.getInstance().add(user.getLogin());
            requestContext.setAttribute("redirect", true);
            response = ShowMainPageCommand.getInstance().execute(requestContext);
        } else {
            requestContext.setAttribute("loginError", "Not found and wrong password");
            response = ShowLoginPageCommand.getInstance().execute(requestContext);
        }
        return response;
    }
}
