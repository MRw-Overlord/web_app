package com.epam.jwd.hardziyevich.hr.service.impl;

import com.epam.jwd.hardziyevich.hr.dao.impl.UserDaoImpl;
import com.epam.jwd.hardziyevich.hr.exception.DeleteUserException;
import com.epam.jwd.hardziyevich.hr.exception.UploadAvatarPathException;
import com.epam.jwd.hardziyevich.hr.exception.WriteAvatarImgDbException;
import com.epam.jwd.hardziyevich.hr.model.entity.Role;
import com.epam.jwd.hardziyevich.hr.model.entity.User;
import com.epam.jwd.hardziyevich.hr.model.entityDto.UserDto;
import com.epam.jwd.hardziyevich.hr.pool.ActiveUserPool;
import com.epam.jwd.hardziyevich.hr.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static volatile UserServiceImpl instance = null;

    public static final String ANTIHACK_PASSWARD = "$2a$10$Z6P43xL3xPINRYG6pPwxxunfz53zO9jZ6gC.HDtzkQoQNXh52Prry";
    private UserDaoImpl userDao;

    private UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            synchronized (UserServiceImpl.class) {
                if (instance == null) {
                    instance = new UserServiceImpl(UserDaoImpl.getInstance());
                }
            }
        }
        return instance;
    }

    @Override
    public Optional<UserDto> editProfileUser(String login, String name, String lastName, String email, Integer age) {
        final Optional<User> byLogin = userDao.findByLogin(login);
        byLogin.ifPresent(user -> userDao.updateProfile(user, name, lastName, email, age));
        UserDto userDto = null;
        if (byLogin.isPresent()) {
            final User updateUser = new User(byLogin.get().getId(), byLogin.get().getLogin(), byLogin.get().getRole(),
                    name, lastName, age, email, byLogin.get().getPassword(), byLogin.get().getStatus(), byLogin.get().getAvatarPath());
            userDto = convertToDto(updateUser);
        }
        return Optional.ofNullable(userDto);
    }

    @Override
    public boolean appointRecruiter(String recruiterLogin) {
        boolean result = false;
        final Optional<User> recruiterOptional = userDao.findByLogin(recruiterLogin);
        if (recruiterOptional.isPresent()) {
            User recruiter = recruiterOptional.get();
            recruiter.setRole(Role.HR);
            result = userDao.update(recruiter);
        }
        return result;
    }

    @Override
    public boolean banRecruiter(String recruiterLogin) {
        boolean result = false;
        final Optional<User> recruiterOptional = userDao.findByLogin(recruiterLogin);
        if (recruiterOptional.isPresent()) {
            User recruiter = recruiterOptional.get();
            recruiter.setRole(Role.GUEST);
            result = userDao.update(recruiter);
        }
        return result;
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
    public Optional<UserDto> findUserById(int id) {
        UserDto userDto = null;
        final Optional<User> byId = userDao.findById(id);
        if (byId.isPresent()) {
            userDto = convertToDto(byId.get());
        }
        return Optional.ofNullable(userDto);
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
    public boolean create(User object) {
        return userDao.create(object);
    }

    @Override
    public Optional<UserDto> loginUser(String login, String password) {
        UserDto userDto = null;
        Optional<User> user = userDao.findByLogin(login);
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
    public void banUser(String login) {
        final Optional<User> userOptional = userDao.findByLogin(login);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setStatus("HIDDEN");
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
        return new UserDto(user.getId(), user.getLogin(),
                user.getFirstName(), user.getLastName(),
                user.getAge(), user.getEmail(), user.getRole(),
                user.getStatus(), user.getAvatarPath(), user.getAvatarImg());
    }

    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public void setAvatarPath(String path, int id){
        try {
            userDao.uploadAvatarPath(id, path);
        } catch (UploadAvatarPathException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void writeAvatarImg(InputStream inputStream, int userId, File image) {
        try {
            userDao.writeAvatarImgtoDb(inputStream, userId, image);
        } catch (WriteAvatarImgDbException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int userId) {
        final Optional<User> byId = userDao.findById(userId);
        boolean result = false;
        if(byId.isPresent()){
          result = userDao.delete(byId.get());
        }
        if(!result){
            try {
                throw new DeleteUserException(userId);
            } catch (DeleteUserException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
