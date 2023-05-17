package com.example.ua.secorgapp.mysql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
public class TestMySqlDatabase {

    @Autowired
    private MySqlLoader mySqlLoader;
    @Autowired
    private MySqlDatabase mySqlDatabase;

    @Test
    public void shouldConnectToDb() throws SQLException {
        var connection = mySqlLoader.getConnection();
        assert connection != null;
    }

    @Test
    public void shouldPerformSelect() throws SQLException {
        var sql = "SELECT * FROM doctor;";
        var resultSet = mySqlDatabase.executeRead(sql);

        while(resultSet.next()){
            var id = resultSet.getLong("id");
            var firstname = resultSet.getString("firstname");
            var lastname = resultSet.getString("lastname");
            assert firstname != null;
            assert lastname != null;
        }
    }

    @Test
    public void shouldPerformInsert() throws SQLException {
        String sql = "INSERT INTO doctor VALUES (999,'HOMER','SIM-DUDE-SON')";
        var resultNumber = mySqlDatabase.executeUpdate(sql);
        assert resultNumber != 0;
    }
}
