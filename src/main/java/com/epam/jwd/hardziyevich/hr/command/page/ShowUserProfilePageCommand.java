package com.epam.jwd.hardziyevich.hr.command.page;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;

public class ShowUserProfilePageCommand implements Command {
    private static final ResponseContext SHOW_USER_PROFILE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/userProfile.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };
    private static ShowUserProfilePageCommand instance = null;

    private ShowUserProfilePageCommand(){

    }

    public static ShowUserProfilePageCommand getInstance(){
        if(instance == null){
            instance = new ShowUserProfilePageCommand();
        }
        return instance;
    }


    @Override
    public ResponseContext execute(RequestContext requestContext) {
        return SHOW_USER_PROFILE;
    }
}
