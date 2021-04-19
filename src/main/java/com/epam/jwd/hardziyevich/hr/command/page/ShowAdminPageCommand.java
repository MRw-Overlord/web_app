package com.epam.jwd.hardziyevich.hr.command.page;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.service.UserService;
import com.epam.jwd.hardziyevich.hr.service.impl.UserServiceImpl;
import com.epam.jwd.hardziyevich.hr.service.impl.VacancyServiceImpl;

public class ShowAdminPageCommand implements Command {
    private static ShowAdminPageCommand instance = null;

    private ShowAdminPageCommand(){

    }

    public static ShowAdminPageCommand getInstance(){
        if(instance == null){
            instance = new ShowAdminPageCommand();
        }
        return instance;
    }

    private final static String VACANCIES_LIST_TYPE = "vacancies";
    private final static String HR_LIST_TYPE = "hr";
    private final static String USERS_LIST_TYPE = "users";
    private final static String LIST_TYPE_PARAM = "adminListType";

    //todo: create in userService
    private final VacancyServiceImpl vacancyService = VacancyServiceImpl.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();

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
        userService.findAll().ifPresent(users -> requestContext.setAttribute(USERS_LIST_TYPE, users));
        return context;
    }
}
