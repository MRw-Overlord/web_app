package com.epam.jwd.hardziyevich.hr.dao.impl;

import com.epam.jwd.hardziyevich.hr.dao.RespondDao;
import com.epam.jwd.hardziyevich.hr.model.entity.Respond;
import com.epam.jwd.hardziyevich.hr.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RespondDaoImpl implements RespondDao {
    private static final String CREATE_RESPONSE_QUERY = "INSERT into respond (user_id, date) VALUES (?, ?)";
    private static final String FIND_ALL_RESPONDS = "select * from respond left join respond_vacancy rv on respond.respond_id = rv.respond_id;";
    private static final String DELETE_RESPOND_QUERY = "DELETE respond, respond_vacancy from respond inner join respond_vacancy on respond.respond_id = respong_vacancy.respond_id where respond.respond_id = ?";
    private static final String FIND_ALL_RESPONDS_BY_ID_QUERY = "select * from respond left join respond_vacancy rv on respond.respond_id = rv.respond_id where respond_id = ?";
    private static final String CREATE_RESPONSE_VACANCY_QUERY = "insert into respond_vacancy (respond_id, vacancy_id) values (?, ?)";
    private static final String FIND_ALL_RESPOND_VACANCY = "select * from respond_vacancy";
    public static final String FIND_USERS_ID_BY_ID_VACANCY = "SELECT user_id from respond join respond_vacancy rv on respond.respond_id = rv.respond_id where vacancy_id = ?";
    private static volatile RespondDaoImpl instance = null;

    private RespondDaoImpl() {

    }

    public static RespondDaoImpl getInstance() {
        if (instance == null) {
            synchronized (RespondDaoImpl.class) {
                if (instance == null) {
                    instance = new RespondDaoImpl();
                }
            }
        }
        return instance;
    }

    private static final Logger LOGGER = LogManager.getLogger(RespondDaoImpl.class);

    @Override
    public Optional<List<Integer>> findUsersIdRespondVacancyById(int vacancyId) {
        List<Integer> usersId = new ArrayList<>();
        try (final Connection connection = ConnectionPool.getInstance().retrieveConnection();
             final PreparedStatement statement1 = connection.prepareStatement(FIND_USERS_ID_BY_ID_VACANCY)) {
            statement1.setInt(1, vacancyId);
            final ResultSet respondsSet = statement1.executeQuery();
            while (respondsSet.next()) {
                usersId.add(respondsSet.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return Optional.of(usersId);
    }


    @Override
    public boolean create(Respond object) {
        boolean result = false;
        try (final Connection connection = ConnectionPool.getInstance().retrieveConnection();
             final PreparedStatement responseStatement1 = connection.prepareStatement(CREATE_RESPONSE_QUERY, Statement.RETURN_GENERATED_KEYS);
             final PreparedStatement responseStatement2 = connection.prepareStatement(CREATE_RESPONSE_VACANCY_QUERY)) {
            connection.setAutoCommit(false);
            try {
                responseStatement1.setInt(1, object.getUserId());
                responseStatement1.setTimestamp(2, object.getDate());
                int affectedRows = responseStatement1.executeUpdate();
                ResultSet responseStatement1GeneratedKeys = responseStatement1.getGeneratedKeys();
                int key = 0;
                if (responseStatement1GeneratedKeys.next()) {
                    key = responseStatement1GeneratedKeys.getInt(1);
                }
                responseStatement2.setInt(1, key);
                responseStatement2.setInt(2, object.getVacancyId());
                responseStatement2.executeUpdate();
                result = true;
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return result;
    }

    /*public int createAndGetId(Respond object){
        int genId = 0;
        try(final Connection connection = ConnectionPool.getInstance().retrieveConnection();
            final PreparedStatement responseStatement = connection.prepareStatement(CREATE_RESPONSE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            try{
                responseStatement.setInt(1, object.getUserId());
                responseStatement.setTimestamp(2, object.getDate());
                responseStatement.executeUpdate();
                genId = responseStatement.getGeneratedKeys().getInt(1);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return genId;
    }*/

    @Override
    public Optional<List<Respond>> findAll() {
        List<Respond> responds = new ArrayList<>();
        try (final Connection connection = ConnectionPool.getInstance().retrieveConnection();
             final PreparedStatement statement1 = connection.prepareStatement(FIND_ALL_RESPONDS)) {
            final ResultSet respondsSet = statement1.executeQuery();
            while (respondsSet.next()) {
                Respond respond = new Respond(
                        respondsSet.getInt("respond_id"),
                        respondsSet.getInt("vacancy_id"),
                        respondsSet.getInt("user_id"),
                        respondsSet.getTimestamp("date")
                );
                responds.add(respond);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return Optional.of(responds);
    }

    @Override
    public boolean update(Respond object) {
        return false;
    }

    @Override
    public boolean delete(Respond object) {
        boolean result = false;
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection();
             final PreparedStatement deleteRespond = connection.prepareStatement(DELETE_RESPOND_QUERY)) {
            connection.setAutoCommit(false);
            try {
                deleteRespond.setInt(1, object.getRespondId());
                deleteRespond.executeUpdate();
                connection.commit();
                result = true;
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<Respond> findById(int id) {
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection();
             final PreparedStatement statement = connection.prepareStatement(FIND_ALL_RESPONDS_BY_ID_QUERY)) {
            statement.setInt(1, id);
            final ResultSet respondSet = statement.executeQuery();
            while (respondSet.next()) {
                Respond respond = new Respond(respondSet.getInt("respond_id"),
                        respondSet.getInt("vacancy_id"),
                        respondSet.getInt("user_id"),
                        respondSet.getTimestamp("date"));
                return Optional.of(respond);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
