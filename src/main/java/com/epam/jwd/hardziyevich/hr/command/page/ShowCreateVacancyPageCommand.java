package com.epam.jwd.hardziyevich.hr.command.page;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.service.VacancyService;
import com.epam.jwd.hardziyevich.hr.service.impl.VacancyServiceImpl;

public class ShowCreateVacancyPageCommand implements Command {

    public static final ResponseContext EDIT_VACANCY_PAGE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/createVacancyPage.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };
    public static ShowCreateVacancyPageCommand instance = null;
    public static final String COMPANY_NAME_PARAM = "companyName";
    public static final String VACANCY_NAME_PARAM = "vacancyName";
    public static final String VACANCY_DESCRIPTION_PARAM = "description";
    public static final String VACANCY_SKILLS_DESCRIPTION_PARAM = "skillsDescription";
    public static final String VACANCY_STATUS_PARAM = "status";
    public static final	 String FORM_ACTION_UPDATE = "/controller?command=update_vacancy&id=";
    private ShowCreateVacancyPageCommand(){

    }

    public static ShowCreateVacancyPageCommand getInstance(){
        if(instance == null){
            instance = new ShowCreateVacancyPageCommand();
        }
        return instance;
    }


    private final VacancyService vacancyService = VacancyServiceImpl.getInstance();

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.setAttribute("vacancyNameParam", VACANCY_NAME_PARAM);
        requestContext.setAttribute("vacancyDescriptionParam", VACANCY_DESCRIPTION_PARAM);
        requestContext.setAttribute("vacancySkillsDescriptionParam", VACANCY_SKILLS_DESCRIPTION_PARAM);
        requestContext.setAttribute("companyNameParam", COMPANY_NAME_PARAM);
        return EDIT_VACANCY_PAGE;
    }
}
