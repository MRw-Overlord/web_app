package com.epam.jwd.hardziyevich.hr.command.page;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;

public class ShowNotFoundPageCommand implements Command {
    private static volatile ShowNotFoundPageCommand instance = null;

    private ShowNotFoundPageCommand(){

    }

    public static ShowNotFoundPageCommand getInstance(){
        if(instance == null) {
            synchronized (ShowNotFoundPageCommand.class) {
                if (instance == null) {
                    instance = new ShowNotFoundPageCommand();
                }
            }
        }
        return instance;
    }

    public static final ResponseContext SHOW_NOT_FOUND_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return "error/page/404.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        return SHOW_NOT_FOUND_PAGE_CONTEXT;
    }
}