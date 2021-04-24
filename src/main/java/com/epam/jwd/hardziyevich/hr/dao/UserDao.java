package com.epam.jwd.hardziyevich.hr.dao;


import com.epam.jwd.hardziyevich.hr.model.entity.User;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Data access object that performs various operations with User model in database
 *
 */
public interface UserDao extends  CommonDao<User>{
    void updateProfile(User object, String name, String lastName, String email, Integer age);

    /**
     * Method is used to retrieve specified user from database by its login
     *
     * @param login is a login of specified user
     * @return Optional User model
     */
    Optional<User> findByLogin(String login);

    Optional<User> findById(int id);

}