package com.epam.jwd.hardziyevich.hr.dao.impl;

import com.epam.jwd.hardziyevich.hr.dao.VacancyDao;
import com.epam.jwd.hardziyevich.hr.model.entity.Status;
import com.epam.jwd.hardziyevich.hr.model.entity.Vacancy;
import com.epam.jwd.hardziyevich.hr.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VacancyDaoImpl implements VacancyDao {
    public static final String CREATE_VACANCY_QUERY = "INSERT INTO vacancy " +
            "(vacancy_id, vacancyName, companyName, description, skillsDescription, status) " +
            "values (?, ?, ?, ?, ?, ?)";
    public static final String FIND_ALL_VACANCY = "SELECT vacancy_id, vacancyName, companyName, description, skillsDescription, status from vacancy;";
    public static final String UPDATE_VACANCY_QUERY = "UPDATE vacancy SET vacancy_id=?, vacancyName=?, companyName=?, description=?, skillsDescription=?, status=?;";
    public static final String DELETE_VACANCY_QUERY = "DELETE from vacancy where vacancy_id=?";
    public static final String FIND_ALL_RELEVANT_VACANCIES = "SELECT vacancy_id, vacancyName, companyName, description, skillsDescription, status from vacancy where status = 'ACTIVE'";
    public static final String FIND_ALL_VACANCIES_BYSTATUS_QUERY = "SELECT vacancy_id, vacancyName, companyName, description, skillsDescription, status from vacancy where status = ?";
    public static final String FIND_ALL_VACANCIES_BY_ID_QUERY = "SELECT vacancy_id, vacancyName, companyName, description, skillsDescription, status from vacancy where vacancy_id=?";
    private static VacancyDaoImpl instance = null;

    private VacancyDaoImpl() {

    }

    public static VacancyDaoImpl getInstance() {
        if (instance == null) {
            instance = new VacancyDaoImpl();
        }
        return instance;
    }


    @Override
    public boolean create(Vacancy object) {
        boolean result = false;
        try (final Connection connection = ConnectionPool.getInstance().retrieveConnection();
             final PreparedStatement vacancyStatement = connection.prepareStatement(CREATE_VACANCY_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            try {
                vacancyStatement.setLong(1, object.getId());
                vacancyStatement.setString(2, object.getVacancyName());
                vacancyStatement.setString(3, object.getCompanyName());
                vacancyStatement.setString(3, object.getDescription());
                vacancyStatement.setString(4, object.getSkillsDescription());
                vacancyStatement.setString(5, object.getStatus().name());
                vacancyStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

    @Override
    public Optional<List<Vacancy>> findAll() {
        List<Vacancy> vacancies = new ArrayList<>();
        try (final Connection connection = ConnectionPool.getInstance().retrieveConnection();
             final PreparedStatement statement = connection.prepareStatement(FIND_ALL_VACANCY)) {
            final ResultSet vacancySet = statement.executeQuery();
            while (vacancySet.next()) {
                Vacancy vacancy = new Vacancy(
                        vacancySet.getInt("vacancy_id"),
                        vacancySet.getString("vacancyName"),
                        vacancySet.getString("companyName"),
                        vacancySet.getString("description"),
                        vacancySet.getString("skillsDescription"),
                        Status.defineStatusByName(vacancySet.getString("status"))
                );
                vacancies.add(vacancy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(vacancies);
    }

    @Override
    public Vacancy update(Vacancy object) {
        try (final Connection connection = ConnectionPool.getInstance().retrieveConnection();
             final PreparedStatement statement = connection.prepareStatement(UPDATE_VACANCY_QUERY)) {
            statement.setInt(1, object.getId());
            statement.setString(2, object.getVacancyName());
            statement.setString(3, object.getCompanyName());
            statement.setString(4, object.getSkillsDescription());
            statement.setString(5, object.getStatus().name());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public Vacancy delete(Vacancy object) {
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection();
             final PreparedStatement deleteVacancy = connection.prepareStatement(DELETE_VACANCY_QUERY)) {
            connection.setAutoCommit(false);
            try {
                deleteVacancy.setInt(1, object.getId());
                deleteVacancy.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<List<Vacancy>> findAllRelevant() {
        List<Vacancy> vacancies = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection();
        final PreparedStatement statement = connection.prepareStatement(FIND_ALL_RELEVANT_VACANCIES)) {
            final ResultSet vacancySet = statement.executeQuery();
            while(vacancySet.next()){
                Vacancy vacancy = new Vacancy(vacancySet.getInt("vacancy_id"),
                        vacancySet.getString("vacancyName"),
                        vacancySet.getString("companyName"),
                        vacancySet.getString("description"),
                        vacancySet.getString("skillsDescription"),
                        Status.defineStatusByName(vacancySet.getString("status")));
                vacancies.add(vacancy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(vacancies);
    }

    @Override
    public Optional<List<Vacancy>> findAllByStatus(Status vacancyStatus) {
        List<Vacancy> vacancies = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection();
            final PreparedStatement statement = connection.prepareStatement(FIND_ALL_VACANCIES_BYSTATUS_QUERY)) {
            statement.setString(1, vacancyStatus.name());
            final ResultSet vacancySet = statement.executeQuery();
            while(vacancySet.next()){
                Vacancy vacancy = new Vacancy(vacancySet.getInt("vacancy_id"),
                        vacancySet.getString("vacancyName"),
                        vacancySet.getString("companyName"),
                        vacancySet.getString("description"),
                        vacancySet.getString("skillsDescription"),
                        Status.defineStatusByName(vacancySet.getString("status")));
                vacancies.add(vacancy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(vacancies);
    }

    @Override
    public Optional<Vacancy> findById(int id) {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection();
            final PreparedStatement statement = connection.prepareStatement(FIND_ALL_VACANCIES_BY_ID_QUERY)) {
            statement.setInt(1, id);
            final ResultSet vacancySet = statement.executeQuery();
            while(vacancySet.next()){
                Vacancy vacancy = new Vacancy(vacancySet.getInt("vacancy_id"),
                        vacancySet.getString("vacancyName"),
                        vacancySet.getString("companyName"),
                        vacancySet.getString("description"),
                        vacancySet.getString("skillsDescription"),
                        Status.defineStatusByName(vacancySet.getString("status")));
                return Optional.of(vacancy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}