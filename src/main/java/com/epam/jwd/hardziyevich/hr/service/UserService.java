package com.epam.jwd.hardziyevich.hr.service;

import com.epam.jwd.hardziyevich.hr.dao.UserDao;
import com.epam.jwd.hardziyevich.hr.model.User;
import com.epam.jwd.hardziyevich.hr.model.UserDto;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class UserService implements CommonService<UserDto> {

    private final UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }


    @Override
    public Optional<List<UserDto>> findALl() {
        return userDao.findALl().map(
                users -> users.stream()
                        .map(this::convertToDto)
                        .collect(toList()));
    }

    @Override
    public Optional<UserDto> save(UserDto entity) {
        return Optional.empty();
    }

    private UserDto convertToDto(User user) {
        return new UserDto(user.getFirstName(), user.getLastName(), user.getAge(), user.getLogin(), user.getMail());
    }

    public Optional<UserDto> login(String name, String password){
        final Optional<User> user = userDao.findByName(name);
        if (!user.isPresent()) {
            BCrypt.checkpw(password, "defaultUser");
            return Optional.empty();
        }
        final String rPassword = user.get().getPassword();
        if (BCrypt.checkpw(password, rPassword)) {
            return Optional.of(convertToDto(user.get()));
        } else {
            return Optional.empty();
        }
    }
}
