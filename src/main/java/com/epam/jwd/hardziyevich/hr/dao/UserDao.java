package com.epam.jwd.hardziyevich.hr.dao;


import com.epam.jwd.hardziyevich.hr.exception.UploadAvatarPathException;
import com.epam.jwd.hardziyevich.hr.exception.WriteAvatarImgDbException;
import com.epam.jwd.hardziyevich.hr.model.entity.User;

import java.io.File;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Optional;

/**
 * Data access object that performs various operations with User model in database
 */
public interface UserDao extends CommonDao<User> {
    void updateProfile(User object, String name, String lastName, String email, Integer age);

    /**
     * Method is used to retrieve specified user from database by its login
     *
     * @param login is a login of specified user
     * @return Optional User model
     */
    Optional<User> findByLogin(String login);

    Optional<User> findById(int id);

    void uploadAvatarPath(int userId, String avatarPath) throws UploadAvatarPathException;

    void writeAvatarImgtoDb(InputStream inputStream, int userId, File image) throws WriteAvatarImgDbException;

    Blob getAvatarImg(int userId);
}