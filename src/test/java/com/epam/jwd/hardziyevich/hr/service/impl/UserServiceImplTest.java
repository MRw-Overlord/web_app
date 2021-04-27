package com.epam.jwd.hardziyevich.hr.service.impl;

import com.epam.jwd.hardziyevich.hr.dao.impl.UserDaoImpl;
import com.epam.jwd.hardziyevich.hr.model.entity.Role;
import com.epam.jwd.hardziyevich.hr.model.entity.User;
import com.epam.jwd.hardziyevich.hr.model.entityDto.UserDto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    private static final int USER_ID = 23;
    private static final String USER_LOGIN = "testLogin";
    private static final Role USER_ROLE = Role.HR;
    private static final String USER_FIRSTNAME = "Test";
    private static final String USER_LASTNAME = "Testio";
    private static final int USER_AGE = 18;
    private static final String USER_EMAIL = "test@gmail.com";
    private static final String USER_STATUS = "ACTIVE";
    private static final String USER_PASSWORD = "$2a$10$UIICnEbOYE21qL4mWes8GOzOBnpNDAEMIPTt0roKYWHPccAGBvV92";
    private static final String USER_AVATAR = "avatar";
    private static final String USER_FIRSTNAME1 = "Test1";
    public static final String USER_LASTNAME1 = "Testio1";
    public static final String USER_EMAIL1 = "test1@gmail.com";
    public static final int USER_AGE1 = 19;
    UserServiceImpl userService;
    @Mock
    UserDaoImpl userDaoMock;
    User user;
    List<User> userList;
    UserDto userDto;
    List<UserDto> userDtoList;


    @Before
    public void setUp() throws Exception {
        user = new User(USER_ID, USER_LOGIN, USER_ROLE, USER_FIRSTNAME, USER_LASTNAME,
                USER_AGE, USER_EMAIL, USER_PASSWORD, USER_STATUS, USER_AVATAR);
        userList = new ArrayList<>();
        userDtoList = new ArrayList<>();
        userList.add(user);
        userDto = new UserDto(USER_ID, USER_LOGIN, USER_FIRSTNAME, USER_LASTNAME,
                USER_AGE, USER_EMAIL, USER_ROLE, USER_STATUS, USER_AVATAR);

        userDtoList.add(userDto);
        Mockito.when(userDaoMock.findById(USER_ID)).thenReturn(Optional.of(user));
        Mockito.when(userDaoMock.findAll()).thenReturn(Optional.of(userList));
        Mockito.when(userDaoMock.findByLogin(USER_LOGIN)).thenReturn(Optional.of(user));
        Mockito.when(userDaoMock.update(user)).thenReturn(true);
        userService = UserServiceImpl.getInstance();
        userService.setUserDao(userDaoMock);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getInstance() {
        final UserServiceImpl actual = UserServiceImpl.getInstance();
        final UserServiceImpl expected = this.userService;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void editProfileUser() {
        final Optional<UserDto> actual = userService.editProfileUser(USER_LOGIN, USER_FIRSTNAME1, USER_LASTNAME1, USER_EMAIL1, USER_AGE1);
        final Optional<UserDto> expected = Optional.of(new UserDto(USER_ID, USER_LOGIN, USER_FIRSTNAME1, USER_LASTNAME1,
                USER_AGE1, USER_EMAIL1, USER_ROLE, USER_STATUS, USER_AVATAR));
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void appointRecruiter() {

    }

    @Test
    public void banRecruiterPositive() {
        final boolean actual = userService.banRecruiter(USER_LOGIN);
        final boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findUserByLoginPositive() {
        final Optional<UserDto> actual = userService.findUserByLogin(USER_LOGIN);
        final Optional<UserDto> expected = Optional.of(this.userDto);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findUserByIdPositive() {
        final Optional<UserDto> actual = userService.findUserById(USER_ID);
        final Optional<UserDto> expected = Optional.of(this.userDto);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findAllPositive() {
        final Optional<List<UserDto>> actual = userService.findAll();
        final Optional<List<UserDto>> expected = Optional.of(this.userDtoList);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void create() {

    }

    @Test
    public void testCreate() {
    }

    @Test
    public void loginUser() {
    }

    @Test
    public void banUser() {
    }

    @Test
    public void unbanUser() {
    }

    @Test
    public void setUserDao() {
    }
}