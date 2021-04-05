package com.epam.jwd.hardziyevich.hr.command.page;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;

public class ShowUsersLoginPage implements Command {
    private static ShowUsersLoginPage instance;
    private static final ResponseContext SHOW_LOGIN_PAGE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/view/jsp/login.jsp";
        }

        @Override
        public boolean isRedirect() {
            return true;
        }
    };

    private ShowUsersLoginPage(){

    }

    public static ShowUsersLoginPage getInstance(){
        if(instance == null){
            instance = new ShowUsersLoginPage();
        }
        return instance;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        return SHOW_LOGIN_PAGE;
    }
}
