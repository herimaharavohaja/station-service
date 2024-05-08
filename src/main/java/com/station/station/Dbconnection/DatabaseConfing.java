package com.station.station.Dbconnection;

import lombok.Value;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DatabaseConfing {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/station";
    static final String USER = "postgres";
    static final String PASS = "fitiavana";


    public Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found", e);
        }
    }
}
