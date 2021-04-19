package com.epam.jwd.hardziyevich.hr.dao;

import com.epam.jwd.hardziyevich.hr.model.entity.Status;
import com.epam.jwd.hardziyevich.hr.model.entity.Vacancy;

import java.util.List;
import java.util.Optional;

public interface VacancyDao extends CommonDao<Vacancy> {
    /**
     * Method is used to retrieve all active vacancy from database vacancy
     *
     * @return Optional List of all retrieved Vacancy models
     */
    Optional<List<Vacancy>> findAllRelevant();

    /**
     * Method is used to retrieve all vacancy by status from database
     *
     * @param vacancyStatus describes the specified Horse model
     * @return Optional List of all retrieved Vacancy models
     */
    Optional<List<Vacancy>> findAllByStatus(Status vacancyStatus);

    /**
     * Method is used to retrieve vacancy by specified ID from database
     *
     * @param id is an ID of specified Vacancy model
     * @return Optional Vacancy model
     */
    Optional<Vacancy> findById(int id);
}
