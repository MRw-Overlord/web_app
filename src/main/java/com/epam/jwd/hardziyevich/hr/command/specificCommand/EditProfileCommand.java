package com.epam.jwd.hardziyevich.hr.command.specificCommand;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.command.page.ShowProfilePageCommand;
import com.epam.jwd.hardziyevich.hr.model.entityDto.UserDto;
import com.epam.jwd.hardziyevich.hr.service.UserService;
import com.epam.jwd.hardziyevich.hr.service.impl.UserServiceImpl;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
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
        HttpServletRequest request;
        HttpSession session = requestContext.getSession();
        final String login = String.valueOf(session.getAttribute("login"));
        final String name = String.valueOf(requestContext.getParameter("name"));
        final String lastName = String.valueOf(requestContext.getParameter("lastName"));
        final String email = String.valueOf(requestContext.getParameter("email"));
        final Integer age = Integer.valueOf(requestContext.getParameter("age"));
        /*final File avatarImg = new File(requestContext.getParameter("avatar"));*/
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
