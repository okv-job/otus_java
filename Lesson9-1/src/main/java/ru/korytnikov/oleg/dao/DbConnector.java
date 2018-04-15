package ru.korytnikov.oleg.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

    public static Connection getConnection() {
        try {

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            String url = "jdbc:mysql://" +
                    "localhost:" +
                    "3306/" +
                    "Test?" +
                    "user=root&" +
                    "password=Rjhsnybrjd10&" +
                    "useSSL=false&" + "useLegacyDatetimeCode=false&serverTimezone=UTC";
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
