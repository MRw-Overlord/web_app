package com.epam.jwd.hardziyevich.hr.command.page;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.model.entityDto.VacancyDto;
import com.epam.jwd.hardziyevich.hr.service.VacancyService;
import com.epam.jwd.hardziyevich.hr.service.impl.VacancyServiceImpl;

import java.util.Optional;

public class ShowEditVacancyPageCommand implements Command {
    public static final ResponseContext EDIT_VACANCY_PAGE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/editVacancyPage.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };
    public static final String COMPANY_NAME_PARAM = "companyName";
    public static final String VACANCY_NAME_PARAM = "vacancyName"; //todo: check later
    public static final String VACANCY_DESCRIPTION_PARAM = "description";
    public static final String VACANCY_SKILLS_DESCRIPTION_PARAM = "skillsDescription";
    public static final String VACANCY_STATUS_PARAM = "status";
    public static final String FORM_ACTION_UPDATE = "/controller?command=update_vacancy&id=";
    public static volatile ShowEditVacancyPageCommand instance = null;
    private final VacancyService vacancyService = VacancyServiceImpl.getInstance();

    private ShowEditVacancyPageCommand() {

    }

    public static ShowEditVacancyPageCommand getInstance() {
        if (instance == null) {
            synchronized (ShowEditVacancyPageCommand.class) {
                if (instance == null) {
                    instance = new ShowEditVacancyPageCommand();
                }
            }
        }
        return instance;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        final int vacancyId = Integer.parseInt(requestContext.getParameter("id".trim()));
        final Optional<VacancyDto> vacancyDto = vacancyService.findById(vacancyId);
        ResponseContext response = EDIT_VACANCY_PAGE;
        if (vacancyDto.isPresent()) {
            requestContext.setAttribute("formAction", FORM_ACTION_UPDATE + vacancyId);
            requestContext.setAttribute("vacancyNameParam", VACANCY_NAME_PARAM);
            requestContext.setAttribute("vacancyDescriptionParam", VACANCY_DESCRIPTION_PARAM);
            requestContext.setAttribute("vacancySkillsDescriptionParam", VACANCY_SKILLS_DESCRIPTION_PARAM);
            requestContext.setAttribute("companyNameParam", COMPANY_NAME_PARAM);
            requestContext.setAttribute("vacancy", vacancyDto.get());
            requestContext.setAttribute("status", VACANCY_STATUS_PARAM);
        } else {
            response = ShowMainPageCommand.getInstance().execute(requestContext);
        }
        return response;
    }
}
