package com.epam.jwd.hardziyevich.hr.command.specificCommand;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.command.page.ShowAdminPageCommand;
import com.epam.jwd.hardziyevich.hr.model.entityDto.UserDto;
import com.epam.jwd.hardziyevich.hr.service.UserService;
import com.epam.jwd.hardziyevich.hr.service.impl.UserServiceImpl;

import java.util.Optional;

public class DeleteUserCommand implements Command {
    private static volatile DeleteUserCommand instance = null;

    private DeleteUserCommand(){

    }

    public static DeleteUserCommand getInstance(){
        if(instance == null) {
            synchronized (DeleteUserCommand.class) {
                if (instance == null) {
                    instance = new DeleteUserCommand();
                }
            }
        }
        return instance;
    }

    private final UserService userService = UserServiceImpl.getInstance();
    @Override

    public ResponseContext execute(RequestContext requestContext) {
        final String login = requestContext.getParameter("login");
        final Optional<UserDto> userByLogin = userService.findUserByLogin(login);
        userByLogin.ifPresent(userDto -> userService.delete(userDto.getId()));
        return ShowAdminPageCommand.getInstance().execute(requestContext);
    }
}
