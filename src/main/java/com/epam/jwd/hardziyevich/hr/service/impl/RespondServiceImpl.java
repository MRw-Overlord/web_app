package com.epam.jwd.hardziyevich.hr.service.impl;


import com.epam.jwd.hardziyevich.hr.dao.RespondDao;
import com.epam.jwd.hardziyevich.hr.dao.impl.RespondDaoImpl;
import com.epam.jwd.hardziyevich.hr.model.entity.Respond;
import com.epam.jwd.hardziyevich.hr.model.entityDto.RespondDto;
import com.epam.jwd.hardziyevich.hr.model.entityDto.UserDto;
import com.epam.jwd.hardziyevich.hr.service.RespondService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RespondServiceImpl implements RespondService {
    private static RespondServiceImpl instance = null;
    private RespondDao respondDao;

    private RespondServiceImpl(RespondDao respondDao) {
        this.respondDao = RespondDaoImpl.getInstance();
    }

    public static RespondServiceImpl getInstance() {
        if (instance == null) {
            instance = new RespondServiceImpl(RespondDaoImpl.getInstance());
        }
        return instance;
    }

    @Override
    public Optional<List<UserDto>> findAllUsersByIDWhichRespondVacancy(int vacancyId) {
        /*return respondDao.findUsersIdRespondVacancyById(vacancyId).map(
                usersId -> usersId.stream()
                        .map(userID -> UserServiceImpl.getInstance().findUserById(userID).get())
                        .collect(Collectors.toList());*/
        final Optional<List<Integer>> usersId = respondDao.findUsersIdRespondVacancyById(vacancyId);
        List<UserDto> users = null;
        if (usersId.isPresent()) {
            final List<Integer> usersIds = usersId.get();
            users = usersIds.stream().map(userId -> UserServiceImpl.getInstance().findUserById(userId).get()).collect(Collectors.toList());
        }
        return Optional.ofNullable(users);
    }

    @Override
    public boolean create(RespondDto object) {
        return false;
    }

    @Override
    public Optional<List<RespondDto>> findAll() {
        List<RespondDto> respondDtos = new ArrayList<>();
        final Optional<List<Respond>> allVacancy = respondDao.findAll();
        if (allVacancy.isPresent()) {
            respondDtos = allVacancy.get().stream().map(this::convertToDto).collect(Collectors.toList());
        }
        return Optional.of(respondDtos);
    }

    @Override
    public Optional<RespondDto> findRespondById(int respondId) {
        Optional<Respond> respondOptional = respondDao.findById(respondId);
        return respondOptional.map(this::convertToDto);
    }

    @Override
    public boolean delete(int respondId) {
        boolean result = false;
        final Optional<Respond> respondOptional = respondDao.findById(respondId);
        if (respondOptional.isPresent()) {
            try {
                result = respondDao.delete(respondOptional.get());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public boolean create(int userId, int vacancyId, Timestamp date) {
        if (date == null) {
            return false;
        }
        Respond respond = new Respond(userId, vacancyId, date);
        return respondDao.create(respond);
    }

    @Override
    public Respond convertToModel(RespondDto respondDto) {
        return new Respond(respondDto.getRespondId(), respondDto.getVacancyId(), respondDto.getUserId(), respondDto.getDate());
    }

    private RespondDto convertToDto(Respond respond) {
        return new RespondDto(respond.getRespondId(), respond.getVacancyId(), respond.getUserId(), respond.getDate());
    }

    public void setRespondDao(RespondDao respondDao) {
        this.respondDao = respondDao;
    }
}
