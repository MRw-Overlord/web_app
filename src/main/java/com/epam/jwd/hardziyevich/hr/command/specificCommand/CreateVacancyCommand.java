package com.epam.jwd.hardziyevich.hr.command.specificCommand;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.command.page.ShowMainPageCommand;
import com.epam.jwd.hardziyevich.hr.model.entity.Status;
import com.epam.jwd.hardziyevich.hr.service.VacancyService;
import com.epam.jwd.hardziyevich.hr.service.impl.VacancyServiceImpl;



public class CreateVacancyCommand implements Command {
    private static CreateVacancyCommand instance = null;

    private CreateVacancyCommand(){

    }

    public static CreateVacancyCommand getInstance(){
        if(instance == null){
            instance = new CreateVacancyCommand();
        }
        return instance;
    }

    private final VacancyService vacancyService = VacancyServiceImpl.getInstance();

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext responseResult = null;
        final String vacancyName = String.valueOf(requestContext.getParameter("vacancyName"));
        final String companyName = String.valueOf(requestContext.getParameter("companyName"));
        final String description = String.valueOf(requestContext.getParameter("description"));
        final String skillsDescription = String.valueOf(requestContext.getParameter("skillsDescription"));
        final boolean result = vacancyService.create(vacancyName,companyName, description, skillsDescription, Status.ACTIVE);
        if(result){
            responseResult = ShowMainPageCommand.getInstance().execute(requestContext);
        }
        return responseResult;
    }
}
