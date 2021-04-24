package com.epam.jwd.hardziyevich.hr.command.specificCommand;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.command.page.ShowMainPageCommand;
import com.epam.jwd.hardziyevich.hr.model.entityDto.UserDto;
import com.epam.jwd.hardziyevich.hr.service.RespondService;
import com.epam.jwd.hardziyevich.hr.service.UserService;
import com.epam.jwd.hardziyevich.hr.service.impl.RespondServiceImpl;
import com.epam.jwd.hardziyevich.hr.service.impl.UserServiceImpl;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

public class ApplyVacancyCommand implements Command {
    private static ApplyVacancyCommand instance;

    private ApplyVacancyCommand(){

    }

    public static ApplyVacancyCommand getInstance(){
        if(instance == null){
            instance = new ApplyVacancyCommand();
        }
        return instance;
    }

    private final RespondService respondService = RespondServiceImpl.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();


    @Override
    public ResponseContext execute(RequestContext requestContext) {
        final String login = String.valueOf(requestContext.getParameter("userLogin"));
        final int vacancyId = Integer.parseInt(requestContext.getParameter("vacancyId"));
        final Timestamp date = Timestamp.valueOf(LocalDateTime.now());
        final Optional<UserDto> userByLogin = userService.findUserByLogin(login);
        if(userByLogin.isPresent()) {
            respondService.create(userService.findUserByLogin(login).get().getId(),vacancyId, date);
            requestContext.setAttribute("redirect", true);
        }
        return ShowMainPageCommand.getInstance().execute(requestContext);
    }
}
