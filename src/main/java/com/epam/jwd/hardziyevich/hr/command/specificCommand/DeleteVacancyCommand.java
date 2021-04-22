package com.epam.jwd.hardziyevich.hr.command.specificCommand;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.command.page.ShowAdminPageCommand;
import com.epam.jwd.hardziyevich.hr.command.page.ShowMainPageCommand;
import com.epam.jwd.hardziyevich.hr.command.page.ShowRecruiterPageCommand;
import com.epam.jwd.hardziyevich.hr.service.VacancyService;
import com.epam.jwd.hardziyevich.hr.service.impl.VacancyServiceImpl;

public class DeleteVacancyCommand implements Command {
    private static DeleteVacancyCommand instance = null;

    private DeleteVacancyCommand(){

    }

    public static DeleteVacancyCommand getInstance(){
        if(instance == null){
            instance = new DeleteVacancyCommand();
        }
        return instance;
    }

    private final VacancyService vacancyService = VacancyServiceImpl.getInstance();

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        final int vacancyId = Integer.parseInt(requestContext.getParameter("id"));
        vacancyService.delete(vacancyId);
        return ShowRecruiterPageCommand.getInstance().execute(requestContext);
    }
}