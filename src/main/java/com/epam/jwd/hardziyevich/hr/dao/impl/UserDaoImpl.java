package com.epam.jwd.hardziyevich.hr.dao.impl;

import com.epam.jwd.hardziyevich.hr.dao.UserDao;
import com.epam.jwd.hardziyevich.hr.exception.UploadAvatarPathException;
import com.epam.jwd.hardziyevich.hr.exception.WriteAvatarImgDbException;
import com.epam.jwd.hardziyevich.hr.model.entity.Role;
import com.epam.jwd.hardziyevich.hr.model.entity.User;
import com.epam.jwd.hardziyevich.hr.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final String GET_USER_BY_ID_QUERY = "SELECT user_info.user_id, user_login, user_password, user_firstName, user_lastName, user_email, age, user_role_name, user_status, avatarPath, avatarImg\n" +
            "from user_table\n" +
            "JOIN user_info\n" +
            "ON user_table.user_id = user_info.user_id\n" +
            "WHERE user_table.user_id= ?";
    public static final String DEFAULT_AVATAR_JPG = "F:/Work/EPAM/HR/src/main/webapp/static/userAvatarDump/DefaultAvatar.jpg";
    public static final String DELETE_USER_FROMDB_QUARE = "DELETE from user_table where user_id=?";
    public static final String SET_USER_AVATAR_PATH = "UPDATE user_info SET avatarPath=? WHERE user_id=?";
    private static String DEFAULT_ENCODE_AVATAR_STRING;
    private static UserDaoImpl instance = null;

    private UserDaoImpl() {

    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
            File file = new File(DEFAULT_AVATAR_JPG);
            try (final InputStream binaryStream = new FileInputStream(file)) {
                final BufferedImage read = ImageIO.read(binaryStream);
                DEFAULT_ENCODE_AVATAR_STRING = encodeToString(read);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public static final String GET_ALL_USERS_QUERY = "SELECT user_info.user_id, user_login, user_password, user_firstName, user_lastName, user_email, age, user_role_name, user_status, avatarPath, avatarImg\n" +
            "from user_table\n" +
            "JOIN user_info\n" +
            "ON user_table.user_id = user_info.user_id";

    public static final String GET_USER_BY_LOGIN_QUERY = "SELECT user_info.user_id, user_login, user_password, user_firstName, user_lastName, user_email, age, user_role_name, user_status, avatarPath, avatarImg\n" +
            "from user_table\n" +
            "JOIN user_info\n" +
            "ON user_table.user_id = user_info.user_id\n" +
            "WHERE user_login= ?";
    public static final String UPDATE_USER = "UPDATE user_table\n" +
            "INNER JOIN user_info ON user_info.user_id=user_table.user_id\n" +
            "SET user_table.user_login=?, user_table.user_password=?, user_info.user_firstName=?, user_info.user_lastName=?, user_info.user_email=?, \n" +
            "    user_info.age=?, user_table.user_role_name=?, user_table.user_status=?  where user_table.user_id=?";
    public static final String UPDATE_USER_PROFILE = "UPDATE user_info SET user_firstName=?, user_lastName=?, user_email=?, age=? WHERE user_id=?; ";
    public static final String CREATE_USER_QUERY = "INSERT INTO user_table(user_login, user_password) VALUES (?, ?)"; //todo: check user_role_name
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public boolean create(User object) {
        boolean result = false;
        try (final Connection connection = ConnectionPool.getInstance().retrieveConnection();
             final PreparedStatement statement = connection.prepareStatement(CREATE_USER_QUERY)) {
            final String login = object.getLogin();
            final String password = BCrypt.hashpw(object.getPassword(), BCrypt.gensalt());
            statement.setString(1, login);
            statement.setString(2, password);
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public Optional<List<User>> findAll() {
        List<User> users = new ArrayList<>();
        try (final Connection connection = ConnectionPool.getInstance().retrieveConnection();
             final Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS_QUERY);
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("user_id"),
                        resultSet.getString("user_login"),
                        Role.defineRoleByName(resultSet.getString("user_role_name")),
                        resultSet.getString("user_firstName"),
                        resultSet.getString("user_lastName"),
                        resultSet.getInt("age"),
                        resultSet.getString("user_email"),
                        resultSet.getString("user_password"),
                        resultSet.getString("user_status"),
                        resultSet.getString("avatarPath"),
                        getAvatar(resultSet)));
            }
        } catch (SQLException | IOException e) {
            logger.error(e.getMessage());
        }
        return Optional.of(users);
    }

    @Override
    public void updateProfile(User object, String name, String lastName, String email, Integer age) {
        try (final Connection connection = ConnectionPool.getInstance().retrieveConnection();
             final PreparedStatement statement = connection.prepareStatement(UPDATE_USER_PROFILE)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setInt(4, age);
            statement.setInt(5, object.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public boolean update(User object) {
        boolean result = false;
        try (final Connection connection = ConnectionPool.getInstance().retrieveConnection();
             final PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setString(1, object.getLogin());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getFirstName());
            statement.setString(4, object.getLastName());
            statement.setString(5, object.getEmail());
            statement.setInt(6, object.getAge());
            statement.setString(7, object.getRole().name());
            statement.setString(8, object.getStatus());
            statement.setString(9, String.valueOf(object.getId()));
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean delete(User object) {
        boolean result = false;
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection();
             final PreparedStatement deleteUser = connection.prepareStatement(DELETE_USER_FROMDB_QUARE)) {
            connection.setAutoCommit(false);
            try {
                deleteUser.setInt(1, object.getId());
                deleteUser.executeUpdate();
                connection.commit();
                result = true;
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        User user = null;
        try (final Connection connection = ConnectionPool.getInstance().retrieveConnection();
             final PreparedStatement statement = connection.prepareStatement(GET_USER_BY_LOGIN_QUERY)) {
            statement.setString(1, login);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt("user_id"),
                        resultSet.getString("user_login"),
                        Role.defineRoleByName(resultSet.getString("user_role_name")),
                        resultSet.getString("user_firstName"),
                        resultSet.getString("user_lastName"),
                        resultSet.getInt("age"),
                        resultSet.getString("user_email"),
                        resultSet.getString("user_password"),
                        resultSet.getString("user_status"),
                        resultSet.getString("avatarPath"),
                        getAvatar(resultSet));
            }
        } catch (SQLException | IOException e) {
            logger.error(e.getMessage());
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findById(int id) {
        User user = null;
        try (final Connection connection = ConnectionPool.getInstance().retrieveConnection();
             final PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID_QUERY)) {
            statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt("user_id"),
                        resultSet.getString("user_login"),
                        Role.defineRoleByName(resultSet.getString("user_role_name")),
                        resultSet.getString("user_firstName"),
                        resultSet.getString("user_lastName"),
                        resultSet.getInt("age"),
                        resultSet.getString("user_email"),
                        resultSet.getString("user_password"),
                        resultSet.getString("user_status"),
                        resultSet.getString("avatarPath"),
                        getAvatar(resultSet));
            }
        } catch (SQLException | IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    private String getAvatar(ResultSet resultSet) throws SQLException, IOException {
        String avatarImg = null;
        Blob blob = resultSet.getBlob("avatarImg");
        avatarImg = getStringImg(avatarImg, blob);
        return avatarImg;
    }

    private String getStringImg(String avatarImg, Blob blob) throws SQLException, IOException {
        if (blob != null) {
            try (final InputStream binaryStream = blob.getBinaryStream()) {
                final BufferedImage read = ImageIO.read(binaryStream);
                if (read != null) {
                    avatarImg = encodeToString(read);
                }
            }
        } else {
            avatarImg = DEFAULT_ENCODE_AVATAR_STRING;
        }
        return avatarImg;
    }

    private static String encodeToString(BufferedImage image) {
        String base64String = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", bos);
            byte[] imageBytes = bos.toByteArray();
            BASE64Encoder encoder = new BASE64Encoder();
            base64String = encoder.encode(imageBytes);
            bos.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return base64String;
    }

    public void uploadAvatarPath(int userId, String avatarPath) throws UploadAvatarPathException {
        boolean result = false;
        try (final Connection connection = ConnectionPool.getInstance().retrieveConnection();
             final PreparedStatement statement = connection.prepareStatement(SET_USER_AVATAR_PATH)) {
            statement.setInt(2, userId);
            statement.setString(1, avatarPath);
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!result) {
            throw new UploadAvatarPathException(userId);
        }
    }

    @Override
    public void writeAvatarImgtoDb(InputStream inputStream, int userId, File image) throws WriteAvatarImgDbException {
        boolean result = false;
        try (final Connection connection = ConnectionPool.getInstance().retrieveConnection();
             final PreparedStatement statement = connection.prepareStatement("UPDATE user_info SET avatarImg=? WHERE user_id=?; ")) {
            statement.setInt(2, userId);
            BufferedImage image1 = ImageIO.read(image);
            Blob blob = connection.createBlob();
            try (OutputStream outputStream = blob.setBinaryStream(1)) {
                ImageIO.write(image1, "jpg", outputStream);
            }
            statement.setBlob(1, blob);
            statement.executeUpdate();
            result = true;
        } catch (SQLException | IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        if (!result) {
            throw new WriteAvatarImgDbException(userId);
        }
    }

    @Override
    public Blob getAvatarImg(int userId) {
        Blob blob = null;
        try (final Connection connection = ConnectionPool.getInstance().retrieveConnection();
             final PreparedStatement statement = connection.prepareStatement("SELECT avatarImg from user_info WHERE user_id=?; ")) {
            statement.setInt(1, userId);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                blob = resultSet.getBlob("avatarImg");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return blob;
    }
}