package com.epam.jwd.hardziyevich.hr.pool;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPool {
    private static ConnectionPool instance = null;
    private static final int INITIAL_CONNECTION_AMOUNT = 8;
    private final ArrayBlockingQueue<ProxyConnection> connections  =
            new ArrayBlockingQueue<>(INITIAL_CONNECTION_AMOUNT);
    public static final String URL = "jdbc:mysql://localhost:3306/hr_webapp?serverTimezone=Europe/Moscow";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    private ConnectionPool(){

    }

    public static ConnectionPool getInstance(){
        if(instance == null){
            instance = new ConnectionPool();
        }
        return instance;
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public Connection retrieveConnection(){
        try {
            return connections.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
             throw new IllegalStateException(e);
        }
    }

    public void returnConnection(Connection connection){
        //todo: check connection on fake
        try {
            connections.put((ProxyConnection) connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }


    public void init() throws SQLException, InterruptedException {
        //todo: database initialization procedures
        registerDrivers();
        for(int i = 0; i < INITIAL_CONNECTION_AMOUNT; i++){
            final Connection realConnection = DriverManager.getConnection(URL, USER, PASSWORD);
            final ProxyConnection proxyConnection = new ProxyConnection(realConnection);
            connections.put(proxyConnection);
        }
    }

    public void destroy(){
        //todo: connection destroy procedures
        connections.forEach(ProxyConnection::closeConnection); //real close
        deregisterDrivers();
    }


    private static void registerDrivers(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.registerDriver(DriverManager.getDriver("jdbc:mysql://localhost:3306/hr"));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void deregisterDrivers(){
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while(drivers.hasMoreElements()){
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
