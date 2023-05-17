package com.example.ua.secorgapp.mysql;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class MySqlLoader {
    @Value("${spring.datasource.url}")
    private String URL;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, username, password);
    }

}
