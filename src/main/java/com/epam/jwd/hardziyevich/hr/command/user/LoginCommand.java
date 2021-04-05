package com.epam.jwd.hardziyevich.hr.command.user;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.command.page.ShowMainPageCommand;
import com.epam.jwd.hardziyevich.hr.command.page.ShowUsersLoginPage;
import com.epam.jwd.hardziyevich.hr.model.UserDto;
import com.epam.jwd.hardziyevich.hr.service.UserService;

import java.util.Optional;

public class LoginCommand implements Command {
    private static LoginCommand instance;
    private final UserService userService;

    private LoginCommand(){
        this.userService = new UserService();
    }

    public static LoginCommand getInstance(){
        if(instance == null){
            instance = new LoginCommand();
        }
        return instance;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        final String userName = String.valueOf(requestContext.getAttribute("userName"));
        final String userPassword = String.valueOf(requestContext.getAttribute("userPassword"));
        final Optional<UserDto> user = userService.login(userName, userPassword);
        final ResponseContext result;
        if(user.isPresent()){
            requestContext.setSessionAttribute("userName", user);
            result = ShowMainPageCommand.getInstance().execute(requestContext);//todo: rewrite with redirect
        } else {
            requestContext.setAttribute("errorMessage", "invalid credentials");
            result = ShowUsersLoginPage.getInstance().execute(requestContext); //todo: rewrite with redirect
        }
        return result;
    }
}
