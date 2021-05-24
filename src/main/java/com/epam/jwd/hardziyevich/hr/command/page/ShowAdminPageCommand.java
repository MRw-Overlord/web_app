package com.epam.jwd.hardziyevich.hr.command.page;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.service.UserService;
import com.epam.jwd.hardziyevich.hr.service.impl.UserServiceImpl;
import com.epam.jwd.hardziyevich.hr.service.impl.VacancyServiceImpl;

import java.util.stream.Collectors;

public class ShowAdminPageCommand implements Command {
    public static final ResponseContext ADMIN_PAGE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/adminPage.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };
    public static final ResponseContext ADMIN_PANEL_REDIRECT = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/adminPage.jsp";
        }

        @Override
        public boolean isRedirect() {
            return true;
        }

        @Override
        public String getUrlToRedirect() {
            return "/controller?command=show_admin_page";
        }
    };
    private final static String VACANCIES_LIST_TYPE = "vacancies";
    private final static String HR_LIST_TYPE = "hr";
    private final static String USERS_LIST_TYPE = "users";
    private final static String LIST_TYPE_PARAM = "adminListType";
    private static volatile ShowAdminPageCommand instance = null;
    //todo: create in userService
    private final VacancyServiceImpl vacancyService = VacancyServiceImpl.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();

    private ShowAdminPageCommand() {

    }

    public static ShowAdminPageCommand getInstance() {
        if (instance == null) {
            synchronized (ShowAdminPageCommand.class) {
                if (instance == null) {
                    instance = new ShowAdminPageCommand();
                }
            }
        }
        return instance;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext context = ADMIN_PAGE;
        if (requestContext.getAttribute("redirect") != null) {
            context = ADMIN_PANEL_REDIRECT;
        }

        String adminListType = requestContext.getParameter(LIST_TYPE_PARAM);
        if (adminListType == null) {
            adminListType = USERS_LIST_TYPE;
        }
        requestContext.setAttribute(LIST_TYPE_PARAM, adminListType);


        vacancyService.findAll().ifPresent(vacancyDtos -> requestContext.setAttribute(VACANCIES_LIST_TYPE, vacancyDtos));
        userService.findAll().ifPresent(users -> requestContext
                .setAttribute(USERS_LIST_TYPE, users.stream()
                        .filter(userDto -> !userDto.getRoleName().equalsIgnoreCase("ADMIN"))
                        .collect(Collectors.toList())));
        return context;
    }
}
