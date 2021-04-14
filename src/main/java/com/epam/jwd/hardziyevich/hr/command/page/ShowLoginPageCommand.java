package com.epam.jwd.hardziyevich.hr.command.page;


import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;

public class ShowLoginPageCommand implements Command {
    private static ShowLoginPageCommand instance = null;

    private ShowLoginPageCommand() {

    }

    public static ShowLoginPageCommand getInstance() {
        if (instance == null) {
            instance = new ShowLoginPageCommand();
        }
        return instance;
    }

    public static final ResponseContext LOGIN_PAGE_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/login.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final ResponseContext LOGIN_PAGE_REDIRECT = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/login.jsp";
        }

        @Override
        public boolean isRedirect() {
            return true;
        }

        @Override
        public String getUrlToRedirect() {
            return "/login";
        }
    };

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext response = LOGIN_PAGE_RESPONSE;
        if (requestContext.getParameter("redirect") != null) {
            response = LOGIN_PAGE_REDIRECT;
        }
        return response;
    }
}
