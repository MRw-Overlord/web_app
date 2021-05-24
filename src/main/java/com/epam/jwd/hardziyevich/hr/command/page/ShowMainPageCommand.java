package com.epam.jwd.hardziyevich.hr.command.page;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.model.entityDto.VacancyDto;
import com.epam.jwd.hardziyevich.hr.service.VacancyService;
import com.epam.jwd.hardziyevich.hr.service.impl.VacancyServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class ShowMainPageCommand implements Command {
    private static volatile ShowMainPageCommand instance = null;

    private ShowMainPageCommand() {

    }

    public static ShowMainPageCommand getInstance() {
        if (instance == null) {
            synchronized (ShowMainPageCommand.class) {
                if (instance == null) {
                    instance = new ShowMainPageCommand();
                }
            }
        }
        return instance;
    }

    public static final ResponseContext MAIN_PAGE_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/main.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    public static final ResponseContext MAIN_PAGE_RESPONSE_REDIRECT = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/main.jsp";
        }

        @Override
        public boolean isRedirect() {
            return true;
        }

        @Override
        public String getUrlToRedirect() {
            return "/";
        }
    };

    private final VacancyService vacancyService = VacancyServiceImpl.getInstance();

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext context = MAIN_PAGE_RESPONSE;
        if (requestContext.getAttribute("redirect") != null) {
            context = MAIN_PAGE_RESPONSE_REDIRECT;
        }

        List<VacancyDto> vacancies = vacancyService.findAllRelevantVacancies().orElse(new ArrayList<>());
        requestContext.setAttribute("relevantVacancies", vacancies);
        return context;
    }
}
