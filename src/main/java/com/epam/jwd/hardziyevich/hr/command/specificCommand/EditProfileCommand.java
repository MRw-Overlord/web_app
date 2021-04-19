package com.epam.jwd.hardziyevich.hr.command.specificCommand;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.command.page.ShowProfilePageCommand;
import com.epam.jwd.hardziyevich.hr.model.entityDto.UserDto;
import com.epam.jwd.hardziyevich.hr.service.UserService;
import com.epam.jwd.hardziyevich.hr.service.impl.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class EditProfileCommand implements Command {
    private static EditProfileCommand instance = null;

    private EditProfileCommand() {

    }

    public static EditProfileCommand getInstance() {
        if (instance == null) {
            instance = new EditProfileCommand();
        }
        return instance;
    }

    private final UserService service = UserServiceImpl.getInstance();

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext responseResult = null;
        final String login = String.valueOf(requestContext.getParameter("login"));
        final String name = String.valueOf(requestContext.getParameter("name"));
        final String lastName = String.valueOf(requestContext.getParameter("lastName"));
        final String email = String.valueOf(requestContext.getParameter("email"));
        final Integer age = Integer.valueOf(requestContext.getParameter("age"));
        final Optional<UserDto> user = service.editProfileUser(login, name, lastName, email, age);
        if(user.isPresent()){
            requestContext.setSessionAttribute("firstName", user.get().getFirstName());
            requestContext.setSessionAttribute("lastName", user.get().getLastName());
            requestContext.setSessionAttribute("email", user.get().getEmail());
            requestContext.setSessionAttribute("age", user.get().getAge());
            responseResult = ShowProfilePageCommand.getInstance().execute(requestContext);
        }
        return responseResult;
    }
}
