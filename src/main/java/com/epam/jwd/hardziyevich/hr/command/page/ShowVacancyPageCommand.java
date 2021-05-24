package com.epam.jwd.hardziyevich.hr.command.page;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.model.entity.Vacancy;
import com.epam.jwd.hardziyevich.hr.model.entityDto.VacancyDto;
import com.epam.jwd.hardziyevich.hr.service.VacancyService;
import com.epam.jwd.hardziyevich.hr.service.impl.VacancyServiceImpl;

import java.util.Optional;

public class ShowVacancyPageCommand implements Command {
    private static volatile ShowVacancyPageCommand instance = null;

    private ShowVacancyPageCommand() {

    }

    public static ShowVacancyPageCommand getInstance(){
        if(instance == null) {
            synchronized (ShowVacancyPageCommand.class) {
                if (instance == null) {
                    instance = new ShowVacancyPageCommand();
                }
            }
        }
        return instance;
    }

    public static final String HIDDEN = "HIDDEN";
    VacancyService vacancyService = VacancyServiceImpl.getInstance();

    public static final ResponseContext SHOW_VACANCY_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/vacancyPage.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        int id = Integer.parseInt(requestContext.getParameter("id"));
        Optional<VacancyDto> vacancyDtoOptional = vacancyService.findById(id);
        vacancyDtoOptional.ifPresent(vacancyDto -> requestContext.setAttribute("vacancy", vacancyDto));
        ResponseContext result = SHOW_VACANCY_PAGE_CONTEXT;
        Vacancy vacancy = null;
        if (vacancyDtoOptional.isPresent()) {
            vacancy = vacancyService.convertToModel(vacancyDtoOptional.get());
            if (HIDDEN.equalsIgnoreCase(vacancy.getStatus().geStatusName())) {
                result = ShowNotFoundPageCommand.getInstance().execute(requestContext);
            }
        } else {
            result = ShowNotFoundPageCommand.getInstance().execute(requestContext);
        }
        return result;
    }
}
