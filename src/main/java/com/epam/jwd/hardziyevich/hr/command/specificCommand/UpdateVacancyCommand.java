package com.epam.jwd.hardziyevich.hr.command.specificCommand;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.command.page.ShowAdminPageCommand;
import com.epam.jwd.hardziyevich.hr.command.page.ShowEditVacancyPageCommand;
import com.epam.jwd.hardziyevich.hr.model.entity.Status;
import com.epam.jwd.hardziyevich.hr.service.VacancyService;
import com.epam.jwd.hardziyevich.hr.service.impl.VacancyServiceImpl;


public class UpdateVacancyCommand implements Command {
    private static UpdateVacancyCommand instance = null;

    private UpdateVacancyCommand() {

    }

    public static UpdateVacancyCommand getInstance() {
        if (instance == null) {
            instance = new UpdateVacancyCommand();
        }
        return instance;
    }

    private final VacancyService service = VacancyServiceImpl.getInstance();

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext responseResult = null;
        requestContext.setCharacterEncoding("UTF-8");
        final int vacancyId = Integer.parseInt(requestContext.getParameter("id".trim()));
        final String vacancyName = String.valueOf(requestContext.getParameter(ShowEditVacancyPageCommand.VACANCY_NAME_PARAM));
        final String companyName = String.valueOf(requestContext.getParameter(ShowEditVacancyPageCommand.COMPANY_NAME_PARAM));
        final String description = String.valueOf(requestContext.getParameter(ShowEditVacancyPageCommand.VACANCY_DESCRIPTION_PARAM));
        final String skillsDescription = String.valueOf(requestContext.getParameter(ShowEditVacancyPageCommand.VACANCY_SKILLS_DESCRIPTION_PARAM));
        final boolean result = service.update(vacancyId, vacancyName, companyName, description, skillsDescription);
        if(result){
            requestContext.setAttribute("vacancyId", vacancyId);
            requestContext.setAttribute("vacancyName", vacancyName);
            requestContext.setAttribute("companyName", companyName);
            requestContext.setAttribute("description", description);
            requestContext.setAttribute("skillsDescription", skillsDescription);
            responseResult = ShowAdminPageCommand.getInstance().execute(requestContext);
        }
        return responseResult;
    }
}
