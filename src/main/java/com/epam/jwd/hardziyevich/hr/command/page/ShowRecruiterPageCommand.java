package com.epam.jwd.hardziyevich.hr.command.page;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.service.UserService;
import com.epam.jwd.hardziyevich.hr.service.impl.UserServiceImpl;
import com.epam.jwd.hardziyevich.hr.service.impl.VacancyServiceImpl;

import java.util.stream.Collectors;

public class ShowRecruiterPageCommand implements Command {
    private static volatile ShowRecruiterPageCommand instance;

    private ShowRecruiterPageCommand(){

    }

    public static ShowRecruiterPageCommand getInstance(){
        if(instance == null) {
            synchronized (ShowRecruiterPageCommand.class) {
                if (instance == null) {
                    instance = new ShowRecruiterPageCommand();
                }
            }
        }
        return instance;
    }
    private final static String VACANCIES_LIST_TYPE = "vacancies";
    private final static String USERS_LIST_TYPE = "users";
    private final static String LIST_TYPE_PARAM = "recruiterListType";


    private final VacancyServiceImpl vacancyService = VacancyServiceImpl.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();

    public static final ResponseContext RECRUITER_PAGE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/recruiterPage.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    public static final ResponseContext RECRUITER_PANEL_REDIRECT = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/recruiterPage.jsp";
        }

        @Override
        public boolean isRedirect() {
            return true;
        }

        @Override
        public String getUrlToRedirect() {
            return "/controller?command=show_recruiter_page";
        }
    };

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext context = RECRUITER_PAGE;
        if (requestContext.getAttribute("redirect") != null) {
            context = RECRUITER_PANEL_REDIRECT;
        }

        String recruiterListType = requestContext.getParameter(LIST_TYPE_PARAM);
        if (recruiterListType == null) {
            recruiterListType = USERS_LIST_TYPE;
        }
        requestContext.setAttribute(LIST_TYPE_PARAM, recruiterListType);


        vacancyService.findAll().ifPresent(vacancyDtos -> requestContext.setAttribute(VACANCIES_LIST_TYPE, vacancyDtos));
        userService.findAll().ifPresent(users -> requestContext
                .setAttribute(USERS_LIST_TYPE, users.stream()
                        .filter(userDto -> !(userDto.getRoleName().equalsIgnoreCase("ADMIN")
                                || userDto.getRoleName().equalsIgnoreCase("HR")))
                        .collect(Collectors.toList())));
        return context;
    }
}
