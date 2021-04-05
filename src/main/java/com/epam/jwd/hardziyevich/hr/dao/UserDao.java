package com.epam.jwd.hardziyevich.hr.dao;

import com.epam.jwd.hardziyevich.hr.model.User;
import com.epam.jwd.hardziyevich.hr.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements CommonDao<User> {

    public static final String URL = "jdbc:mysql://localhost:3306/hr_webapp?serverTimezone=Europe/Moscow";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    private static final String USER_COLUMN_PASSWORD = "user_password";
    public static final String USER_COLUMN_ID = "user_id";
    public static final String USER_COLUMN_NAME = "user_firstName";
    public static final String USER_COLUMN_LAST_NAME = "user_lastName";
    public static final String USER_COLUMN_AGE = "age";
    public static final String USER_COLUMN_LOGIN = "user_login";
    public static final String USER_COLUMN_MAIL = "user_email";
    public static final String FIND_ALL_USER_SQL = "SELECT user_info.user_id, user_login, user_password, user_firstName, user_lastName, user_email, age\n" +
            "FROM user_table\n" +
            "JOIN user_info\n" +
            "ON user_table.user_id = user_info.user_id;";
    public static final String FIND_USER_BY_LOGIN_SQL = "SELECT user_info.user_id, %s, user_password, user_firstName, user_lastName, user_email, age\n" +
            "FROM user_table\n" +
            "JOIN user_info\n" +
            "ON user_table.user_id = user_info.user_id;";

    @Override
    public Optional<List<User>> findALl() {
        try (final Connection conn = ConnectionPool.getInstance().retrieveConnection();
        final Statement statement = conn.createStatement();
        final ResultSet resultSet = statement.executeQuery(FIND_ALL_USER_SQL)) {
            List<User> users = new ArrayList<>();
            while(resultSet.next()){
                final User user = readUser(resultSet);
                users.add(user);
            }
            return Optional.of(users);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> save(User entity) {
        return Optional.empty();
    }

    public Optional<User> findByName(String userLogin){
        final String sqlFindByName = String
                .format(FIND_USER_BY_LOGIN_SQL, userLogin);
        try(final Connection conn = ConnectionPool.getInstance().retrieveConnection();
            final Statement statement = conn.createStatement();
        final ResultSet resultSet = statement.executeQuery(sqlFindByName)){
            final User user = readUser(resultSet);
            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private User readUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt(USER_COLUMN_ID),
                resultSet.getString(USER_COLUMN_NAME),
                resultSet.getString(USER_COLUMN_LAST_NAME),
                resultSet.getInt(USER_COLUMN_AGE),
                resultSet.getString(USER_COLUMN_LOGIN),
                resultSet.getString(USER_COLUMN_PASSWORD),
                resultSet.getString(USER_COLUMN_MAIL));
    }


}
