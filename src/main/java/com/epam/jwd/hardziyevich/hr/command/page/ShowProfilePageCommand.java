package com.epam.jwd.hardziyevich.hr.command.page;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.model.entityDto.UserDto;
import com.epam.jwd.hardziyevich.hr.service.UserService;
import com.epam.jwd.hardziyevich.hr.service.impl.UserServiceImpl;

import java.util.Optional;


public class ShowProfilePageCommand implements Command {
    private static ShowProfilePageCommand instance = null;

    private ShowProfilePageCommand(){

    }

    public static ShowProfilePageCommand getInstance() {
        if(instance == null){
            instance = new ShowProfilePageCommand();
        }
        return instance;

    }

    private final UserService userService = UserServiceImpl.getInstance();

    public static final ResponseContext PROFILE_PAGE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/profile.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        String login = String.valueOf(requestContext.getSession().getAttribute("login"));
        final Optional<UserDto> userByLogin = userService.findUserByLogin(login);
        userByLogin.ifPresent(userDto -> requestContext.setAttribute("user", userDto));
        return PROFILE_PAGE;
    }
}
