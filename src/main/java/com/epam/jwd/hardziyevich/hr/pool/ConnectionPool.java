package com.epam.jwd.hardziyevich.hr.pool;

import java.sql.Connection;
import com.mysql.cj.jdbc.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);

    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    public static final int INITIAL_CONNECTIONS_AMOUNT = 8;
    private final Stack<ProxyConnection> connections = new Stack<>();

    private static volatile ConnectionPool instance = null;

    private ConnectionPool(){

    }

    public static ConnectionPool getInstance(){
        if(instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }
    public Connection retrieveConnection() {
        ProxyConnection conn = null;
        lock.lock();
        try {
            final Thread thread = new Thread(ConnectionPool.getInstance()::initConnections);
            thread.start();
            while (connections.empty()) {
                notFull.await();
            }
            conn = connections.pop();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        } finally {
            lock.unlock();
        }
        return conn;
    }

    void returnConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            ProxyConnection proxyConnection = (ProxyConnection) connection;
            try {
                proxyConnection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
            connections.push(proxyConnection);
        }
    }

    public void initConnections() {
        lock.lock();
        try {
            DriverManager.registerDriver(new Driver());
            ResourceBundle resourceBundle = ResourceBundle.getBundle("hr_webapp");
            final String dbUrl = "jdbc:mysql://" +
                    resourceBundle.getString("database.host") + ':' +
                    resourceBundle.getString("database.port") + '/' +
                    resourceBundle.getString("database.name") + '?' +
                    resourceBundle.getString("database.params");
            final String dbUser = resourceBundle.getString("database.user");
            final String dbPassword = resourceBundle.getString("database.password");
            increaseConnections(dbUrl, dbUser, dbPassword);
            reduceConnections();
            notFull.signal();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    private void reduceConnections() {
        for (int i = connections.size(); i > INITIAL_CONNECTIONS_AMOUNT; i--) {
            final ProxyConnection connection = connections.pop();
            connection.realClose();
        }
    }

    private void increaseConnections(String dbUrl, String dbUser, String dbPassword) {
        for (int i = connections.size(); i < INITIAL_CONNECTIONS_AMOUNT; i++) {
            final Connection connection;
            try {
                connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                final ProxyConnection proxyConnection = new ProxyConnection(connection);
                connections.push(proxyConnection);
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public void destroy() {
        connections.forEach(ProxyConnection::realClose);
        final Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }
}