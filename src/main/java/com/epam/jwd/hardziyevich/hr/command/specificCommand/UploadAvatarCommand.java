package com.epam.jwd.hardziyevich.hr.command.specificCommand;

import com.epam.jwd.hardziyevich.hr.command.Command;
import com.epam.jwd.hardziyevich.hr.command.RequestContext;
import com.epam.jwd.hardziyevich.hr.command.ResponseContext;
import com.epam.jwd.hardziyevich.hr.model.entityDto.UserDto;
import com.epam.jwd.hardziyevich.hr.service.UserService;
import com.epam.jwd.hardziyevich.hr.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Optional;

@MultipartConfig
public class UploadAvatarCommand implements Command {
    public static final ResponseContext SUCCESS_UPLOAD = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/includeJsp/successUploadPage.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };
    public static final String PATH_AVATAR_IMG = "F:/Work/EPAM/HR/src/main/webapp/static/userAvatarDump/";
    private static UploadAvatarCommand instance = null;

    private UploadAvatarCommand() {

    }

    public static UploadAvatarCommand getInstance() {
        if (instance == null) {
            instance = new UploadAvatarCommand();
        }
        return instance;
    }

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        final String userLogin = requestContext.getParameter("login");
        try {
            Part filePart = requestContext.getPart("photo");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            InputStream inputStream = filePart.getInputStream();
            writeFile(PATH_AVATAR_IMG + fileName, inputStream);
            final Optional<UserDto> user = userService.findUserByLogin(userLogin);
            if (user.isPresent()) {
                userService.setAvatarPath("/static/" + fileName, user.get().getId());
                userService.writeAvatarImg(inputStream, user.get().getId(), new File(PATH_AVATAR_IMG + fileName));
            }
            inputStream.close();
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
        return SUCCESS_UPLOAD;
    }

    private void writeFile(String _path, InputStream _input) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(_path)) {
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = _input.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String getFileName(Part _part) {
        for (String cd : _part.getHeader("F:/Work/EPAM/HR/src/main/webapp/static/userAvatarDump/").split(";")) {
            if (cd.trim().startsWith(Paths.get(_part.getSubmittedFileName()).getFileName().toString())) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }
}
