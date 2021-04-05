package com.epam.jwd.hardziyevich.hr.service;

import java.util.List;
import java.util.Optional;

public interface CommonService <T>{
    Optional<List<T>> findALl();

    Optional<T> save(T entity);
}
