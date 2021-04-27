package com.epam.jwd.hardziyevich.hr.command.page;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.model.entityDto.UserDto;
import com.epam.jwd.hardziyevich.hr.service.UserService;
import com.epam.jwd.hardziyevich.hr.service.impl.UserServiceImpl;

import java.util.Optional;

public class ShowUserProfilePageCommand implements Command {
    private static final ResponseContext SHOW_USER_PROFILE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/userProfile.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };
    private static ShowUserProfilePageCommand instance = null;

    private ShowUserProfilePageCommand(){

    }

    public static ShowUserProfilePageCommand getInstance(){
        if(instance == null){
            instance = new ShowUserProfilePageCommand();
        }
        return instance;
    }

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        String login = String.valueOf(requestContext.getParameter("login"));
        final Optional<UserDto> userByLogin = userService.findUserByLogin(login);
        userByLogin.ifPresent(userDto -> requestContext.setAttribute("user", userDto));
        return SHOW_USER_PROFILE;
    }
}
