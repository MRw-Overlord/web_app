package com.epam.jwd.hardziyevich.hr.dao.impl;

import com.epam.jwd.hardziyevich.hr.model.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoImplTest {

    private UserDaoImpl userDao;

    @Test
    public void getInstance() {
    }

    @Test
    public void create() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void updateProfile() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Before
    public void setUp(){
        this.userDao = UserDaoImpl.getInstance();
    }
    @Test
    public void findByLogin_Should_Return_True()  {
        Optional<User> user = userDao.findByLogin("Nastena");
        assertThat(user).isNotEmpty();
        assertThat(user.get().getLogin()).isNotNull().isEqualTo("Nastena");
    }

    @Test
    public void findById() {
    }
}