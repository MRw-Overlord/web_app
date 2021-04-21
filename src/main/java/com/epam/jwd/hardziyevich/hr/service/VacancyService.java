package com.epam.jwd.hardziyevich.hr.service;

import com.epam.jwd.hardziyevich.hr.model.entity.Status;
import com.epam.jwd.hardziyevich.hr.model.entity.Vacancy;
import com.epam.jwd.hardziyevich.hr.model.entityDto.VacancyDto;

import java.util.List;
import java.util.Optional;

public interface VacancyService extends CommonService<Vacancy, VacancyDto> {

    boolean create(String vacancyName, String companyName, String description, String skillsDescription, Status status);

    Optional<List<VacancyDto>> findAllRelevantVacancies();

    Optional<VacancyDto> findById(int id);

    boolean update(int vacancyId,
                   String vacancyName,
                   String companyName,
                   String description,
                   String skillsDescription,
                   Status status);

    boolean delete(int vacancyId);

    Vacancy convertToModel(VacancyDto vacancyDto);

    boolean update(int vacancyId, String vacancyName, String companyName, String description, String skillsDescription);
}
