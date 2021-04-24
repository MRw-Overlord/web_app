package com.epam.jwd.hardziyevich.hr.service;


import com.epam.jwd.hardziyevich.hr.model.entity.Respond;
import com.epam.jwd.hardziyevich.hr.model.entityDto.RespondDto;
import com.epam.jwd.hardziyevich.hr.model.entityDto.UserDto;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface RespondService extends CommonService<Respond, RespondDto> {

    /**
     * Method is used to retrieve and convert to DTO specified respond from database by its id.
     *
     * @param respondId is a id of specified respond
     * @return Optional RespondDto model
     */
    Optional<RespondDto> findRespondById(int respondId);

    boolean delete(int respondId);


    boolean create(int userId, int vacancyId, Timestamp date);

    Respond convertToModel(RespondDto respondDto);

    Optional<List<UserDto>> findAllUsersByIDWhichRespondVacancy(int vacancyId);
}
