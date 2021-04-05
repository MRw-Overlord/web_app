package com.epam.jwd.hardziyevich.hr.dao;

import java.util.List;
import java.util.Optional;

public interface CommonDao<T> {

    Optional<List<T>> findALl();

    Optional<T> save(T entity);

}
