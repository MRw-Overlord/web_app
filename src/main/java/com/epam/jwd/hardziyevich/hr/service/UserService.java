package com.epam.jwd.hardziyevich.hr.service;

import com.epam.jwd.hardziyevich.hr.model.entity.User;
import com.epam.jwd.hardziyevich.hr.model.entityDto.UserDto;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Optional;

public interface UserService extends CommonService<User, UserDto> {

    /**
     * Method is used to validate user's login data.
     *
     * @param login    is a provided user's login
     * @param password a provided user's password
     * @return Optional UserDto model
     */
    Optional<UserDto> loginUser(String login, String password);

    /**
     * Method is used to retrieve and convert to DTO specified user from database by its login.
     *
     * @param login is a login of specified user
     * @return Optional UserDto model
     */
    Optional<UserDto> findUserByLogin(String login);

    /**
     * Method is used to ban specified user
     *
     * @param login is a login of specified user
     */
    void banUser(String login);

    /**
     * Method is used to unban specified user
     *
     * @param login is a login of specified user
     */
    void unbanUser(String login);

    Optional<UserDto> findUserById(int id);

    /**
     * Method is used to validate and create a new User in database
     *
     * @param object describes model of new user
     * @return true if committed otherwise false
     */
    boolean create(User object);

    Optional<UserDto> editProfileUser(String login, String name, String lastName, String email, Integer age);

    boolean appointRecruiter(String recruiterLogin);

    boolean banRecruiter(String recruiterLogin);

    void setAvatarPath(String path, int id);

    public void writeAvatarImg(InputStream inputStream, int userId, File image);

}
