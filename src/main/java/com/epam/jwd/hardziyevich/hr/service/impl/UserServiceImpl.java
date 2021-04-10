package com.epam.jwd.hardziyevich.hr.service.impl;

import com.epam.jwd.hardziyevich.hr.dao.impl.UserDaoImpl;
import com.epam.jwd.hardziyevich.hr.model.entity.User;
import com.epam.jwd.hardziyevich.hr.model.entityDto.UserDto;
import com.epam.jwd.hardziyevich.hr.pool.ActiveUserPool;
import com.epam.jwd.hardziyevich.hr.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance = null;
    public static final String ANTIHACK_PASSWARD = "$2a$10$Z6P43xL3xPINRYG6pPwxxunfz53zO9jZ6gC.HDtzkQoQNXh52Prry";
    private UserDaoImpl userDao;

    private UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public static UserServiceImpl getInstance(){
        if(instance == null){
            instance = new UserServiceImpl(UserDaoImpl.getInstance());
        }
        return instance;
    }

    @Override
    public boolean create(UserDto object) {
        return false;
    }

    @Override
    public boolean create(User object) {
        return userDao.create(object);
    }

    @Override
    public Optional<List<UserDto>> findAll() {
        return userDao.findAll().map(
                users -> users.stream()
                        .map(this::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Optional<UserDto> loginUser(String login, String password) {
        UserDto userDto = null;
        UserDaoImpl modifiedUserDao = (UserDaoImpl) userDao;
        Optional<User> user = modifiedUserDao.findByLogin(login);
        if (user.isPresent()) {
            if (BCrypt.checkpw(password, user.get().getPassword())) {
                userDto = convertToDto(user.get());
            }
        } else {
            BCrypt.checkpw(password, ANTIHACK_PASSWARD);
        }
        return Optional.ofNullable(userDto);
    }

    @Override
    public Optional<UserDto> findUserByLogin(String login) {
        UserDto userDto = null;
        final Optional<User> byLogin = userDao.findByLogin(login);
        if (byLogin.isPresent()) {
            userDto = convertToDto(byLogin.get());
        }
        return Optional.ofNullable(userDto);
    }

    @Override
    public void banUser(String login) {
        final Optional<User> userOptional = userDao.findByLogin(login);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setStatus("BANNED");
            userDao.update(user);
            ActiveUserPool.getInstance().remove(user.getLogin());
        }
    }

    @Override
    public void unbanUser(String login) {
        final Optional<User> userOptional = userDao.findByLogin(login);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setStatus("ACTIVE");
            userDao.update(user);
        }
    }

    private UserDto convertToDto(User user) {
        return new UserDto(user.getLogin(),
                user.getFirstName(), user.getLastName(),
                user.getAge(), user.getEmail(), user.getRole(),
                user.getStatus());
    }

    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }
}
