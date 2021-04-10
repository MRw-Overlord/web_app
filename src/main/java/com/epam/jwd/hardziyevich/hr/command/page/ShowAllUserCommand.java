package com.epam.jwd.hardziyevich.hr.command.page;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.model.entityDto.UserDto;
import com.epam.jwd.hardziyevich.hr.service.impl.UserServiceImpl;

import java.util.Collections;
import java.util.List;

public class ShowAllUserCommand implements Command {

    private static ShowAllUserCommand instance = null;

    private static final ResponseContext USER_PAGE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/view/jsp/users.jsp" ;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    private ShowAllUserCommand() {

    }

    public static ShowAllUserCommand getInstance(){
        if(instance == null){
            instance = new ShowAllUserCommand();
        }
        return instance;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        final List<UserDto> users = userService.findAll().orElse(Collections.emptyList());
        requestContext.setAttribute("users", users);
        return USER_PAGE;
    }
}
