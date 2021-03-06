package com.epam.jwd.hardziyevich.hr.command.page;


import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;

public class ShowSignUpPageCommand implements Command {

    public static final ResponseContext SIGN_UP_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/signup.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };
    private static volatile ShowSignUpPageCommand instance = null;

    private ShowSignUpPageCommand() {

    }

    public static ShowSignUpPageCommand getInstance() {
        if (instance == null) {
            synchronized (ShowSignUpPageCommand.class) {
                if (instance == null) {
                    instance = new ShowSignUpPageCommand();
                }
            }
        }
        return instance;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        return SIGN_UP_RESPONSE;
    }
}
