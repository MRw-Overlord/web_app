package com.epam.jwd.hardziyevich.hr.dao;

import com.epam.jwd.hardziyevich.hr.model.entity.Respond;
import com.epam.jwd.hardziyevich.hr.model.entity.Vacancy;

import java.util.Optional;

public interface RespondDao extends CommonDao<Respond> {


    /**
     * Method is used to retrieve respond by specified ID from database
     *
     * @param id is an ID of specified Vacancy model
     * @return Optional Vacancy model
     */
    Optional<Respond> findById(int id);
}
