package com.epam.jwd.hardziyevich.hr.command.page;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;

public class ShowMainPageCommand implements Command {
    private static ShowMainPageCommand instance;

    private ShowMainPageCommand(){

    }

    public static ShowMainPageCommand getInstance(){
        if(instance == null){
            instance = new ShowMainPageCommand();
        }
        return instance;
    }

    private static final ResponseContext MAIN_PAGE_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/view/jsp/main.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };



    @Override
    public ResponseContext execute(RequestContext requestContext) {
        return MAIN_PAGE_RESPONSE;
    }
}
