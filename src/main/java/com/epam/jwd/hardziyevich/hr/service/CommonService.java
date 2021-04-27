package com.epam.jwd.hardziyevich.hr.service;

import java.util.List;
import java.util.Optional;

/**
 * Service that creates and reads data transfer object models
 *
 * @param <K> defines DTO model
 */
public interface CommonService<T, K> {
    /**
     * Method is used to validate model and save it in database.
     *
     * @param object describes a model
     * @return true if committed otherwise false
     */
    boolean create(T object);

    /**
     * Method is used to reads data transfer object models.
     *
     * @return Optional<List> of Data Transfer Objects of all found
     * models
     */
    Optional<List<K>> findAll();
}

