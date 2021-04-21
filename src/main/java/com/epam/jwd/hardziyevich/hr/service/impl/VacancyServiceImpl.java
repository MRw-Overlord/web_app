package com.epam.jwd.hardziyevich.hr.service.impl;

import com.epam.jwd.hardziyevich.hr.dao.impl.VacancyDaoImpl;
import com.epam.jwd.hardziyevich.hr.model.entity.Status;
import com.epam.jwd.hardziyevich.hr.model.entity.Vacancy;
import com.epam.jwd.hardziyevich.hr.model.entityDto.VacancyDto;
import com.epam.jwd.hardziyevich.hr.service.VacancyService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VacancyServiceImpl implements VacancyService {
    private static VacancyServiceImpl instance = null;
    private static VacancyDaoImpl vacancyDao;

    private VacancyServiceImpl() {

    }

    public static VacancyServiceImpl getInstance() {
        if (instance == null) {
            instance = new VacancyServiceImpl();
            vacancyDao = VacancyDaoImpl.getInstance();
        }
        return instance;
    }

    @Override
    public boolean create(VacancyDto object) {
        return false;
    }

    @Override
    public Optional<List<VacancyDto>> findAll() {
        List<VacancyDto> vacancyDtos = new ArrayList<>();
        final Optional<List<Vacancy>> allVacancy = vacancyDao.findAll();
        if (allVacancy.isPresent()) {
            vacancyDtos = allVacancy.get().stream().map(this::convertToDto).collect(Collectors.toList());
        }
        return Optional.of(vacancyDtos);
    }

    private VacancyDto convertToDto(Vacancy vacancy) {
        return new VacancyDto(vacancy.getId(), vacancy.getVacancyName(), vacancy.getCompanyName(), vacancy.getDescription(),
                vacancy.getSkillsDescription(), vacancy.getStatus());
    }

    @Override
    public boolean create(String vacancyName, String companyName, String description, String skillsDescription, Status status) {
        if (vacancyName.length() > 30 || vacancyName.isEmpty() || description.length() > 500
                || companyName.length() > 30
                || skillsDescription.length() > 500
                || status == null) {
            return false;
        }
        Vacancy vacancy = new Vacancy(vacancyName, companyName, description, skillsDescription, status);
        return vacancyDao.create(vacancy);
    }

    @Override
    public Optional<List<VacancyDto>> findAllRelevantVacancies() {
        List<VacancyDto> vacancyDtos = new ArrayList<>();
        Optional<List<Vacancy>> vacancies = vacancyDao.findAllRelevant();
        if (vacancies.isPresent()) {
            List<Vacancy> vacancyList = vacancies.get();
            vacancyDtos = vacancyList.stream()
                    .map(this::convertToDto).collect(Collectors.toList());
        }
        return Optional.of(vacancyDtos);
    }

    @Override
    public Optional<VacancyDto> findById(int id) {
        Optional<Vacancy> vacancyOptional = vacancyDao.findById(id);
        return vacancyOptional.map(this::convertToDto);
    }

    @Override
    public boolean update(int vacancyId, String vacancyName, String companyName, String description, String skillsDescription, Status status) {
        boolean result = true;
        if (vacancyDao.findById(vacancyId).isPresent() && !(vacancyName.length() > 30 || companyName.length() > 30 || description.length() > 500
                || vacancyName.isEmpty() || companyName.isEmpty() || description.isEmpty() || status == null)) {
            Vacancy newVacancy = new Vacancy(vacancyId, vacancyName, companyName, description, skillsDescription, status);
            vacancyDao.update(newVacancy);
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public boolean delete(int vacancyId) {
        boolean result = true;
        final Optional<Vacancy> vacancyOptional = vacancyDao.findById(vacancyId);
        if (vacancyOptional.isPresent()) {
            vacancyDao.delete(vacancyOptional.get());
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public Vacancy convertToModel(VacancyDto vacancyDto) {
        return new Vacancy(vacancyDto.getVacancyName(), vacancyDto.getCompanyName(),
                vacancyDto.getDescription(), vacancyDto.getSkillsDescription(), vacancyDto.getStatus());
    }

    @Override
    public boolean update(int vacancyId, String vacancyName, String companyName, String description, String skillsDescription) {
        boolean result = true;
        final Optional<Vacancy> byId = vacancyDao.findById(vacancyId);
        if (byId.isPresent() && !(vacancyName.length() > 30 || companyName.length() > 30 || description.length() > 500
                || vacancyName.isEmpty() || companyName.isEmpty() || description.isEmpty())) {
            Vacancy newVacancy = new Vacancy(vacancyId, vacancyName, companyName, description, skillsDescription, byId.get().getStatus());
            vacancyDao.update(newVacancy);
        } else {
            result = false;
        }
        return result;
    }
}
