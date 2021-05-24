package com.epam.jwd.hardziyevich.hr.command.page;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;

public class ShowForbiddenPageCommand implements Command {
    private static volatile ShowForbiddenPageCommand instance = null;

    private ShowForbiddenPageCommand(){

    }

    public static ShowForbiddenPageCommand getInstance(){
        if(instance == null) {
            synchronized (ShowForbiddenPageCommand.class) {
                if (instance == null) {
                    instance = new ShowForbiddenPageCommand();
                }
            }
        }
        return instance;
    }

    public static final ResponseContext SHOW_FORBIDDEN_PAGE_COMMAND = new ResponseContext() {
        @Override
        public String getPage() {
            return "error/page/403.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        return SHOW_FORBIDDEN_PAGE_COMMAND;
    }
}
