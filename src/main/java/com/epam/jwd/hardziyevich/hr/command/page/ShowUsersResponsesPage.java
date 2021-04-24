package com.epam.jwd.hardziyevich.hr.command.page;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.model.entityDto.RespondDto;
import com.epam.jwd.hardziyevich.hr.model.entityDto.UserDto;
import com.epam.jwd.hardziyevich.hr.service.RespondService;
import com.epam.jwd.hardziyevich.hr.service.UserService;
import com.epam.jwd.hardziyevich.hr.service.impl.RespondServiceImpl;
import com.epam.jwd.hardziyevich.hr.service.impl.UserServiceImpl;

import java.util.List;
import java.util.Optional;

public class ShowUsersResponsesPage implements Command {
    public static final ResponseContext RESPONSE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/includeJsp/recruiter/usersResponsesPage.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };
    private static ShowUsersResponsesPage instance = null;

    private ShowUsersResponsesPage() {

    }

    public static ShowUsersResponsesPage getInstance() {
        if (instance == null) {
            instance = new ShowUsersResponsesPage();
        }
        return instance;
    }

    private final UserService userService = UserServiceImpl.getInstance();
    private final RespondService respondService = RespondServiceImpl.getInstance();

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext responseContext = RESPONSE_CONTEXT;
        final int vacancyId = Integer.parseInt(requestContext.getParameter("id"));
        final List<UserDto> userDtoList = respondService.findAllUsersByIDWhichRespondVacancy(vacancyId).get();
        requestContext.setAttribute("users", userDtoList);
        return responseContext;
    }
}
