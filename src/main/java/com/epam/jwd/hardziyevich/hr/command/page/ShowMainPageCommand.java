package com.epam.jwd.hardziyevich.hr.command.page;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;

public class ShowMainPageCommand implements Command {
    private static ShowMainPageCommand instance = null;

    private ShowMainPageCommand(){

    }

    public static ShowMainPageCommand getInstance(){
        if(instance == null){
            instance = new ShowMainPageCommand();
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


    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext context = MAIN_PAGE_RESPONSE;
        if (requestContext.getAttribute("redirect") != null) {
            context = MAIN_PAGE_RESPONSE_REDIRECT;
        }
        return context;
    }
}
