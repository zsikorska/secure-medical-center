package com.example.ua.secorgapp.mysql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@SpringBootTest


public class TestSqlInjection {
    @Autowired
    private MySqlDatabase mySqlDatabase;

    @Test
    public void shouldInjectBetweenIds() throws SQLException {
        String id = " 1 OR ID BETWEEN 1 AND 10; -- ";
        String firstname = "'morrison'";

        String sql = "SELECT * FROM doctor WHERE ID = " + id + " OR firstname = " + firstname + ";";
        var resultSet = mySqlDatabase.executeRead(sql);

        while(resultSet.next()) {
            var a = resultSet.getLong("id");
            var b = resultSet.getString("firstname");
            var lastname = resultSet.getString("lastname");
            assert b != null;
            assert lastname != null;
        }
    }
    @Test
    public void shouldInjectAdditionalSelect() throws SQLException {
        String id = "5 UNION SELECT * FROM doctor WHERE ID = 3; -- ";
        String sql = "SELECT * FROM doctor WHERE ID=" + id + ";";

        var resultSet = mySqlDatabase.executeRead(sql);

        while(resultSet.next()) {
            var a = resultSet.getLong("id");
            var b = resultSet.getString("firstname");
            var lastname = resultSet.getString("lastname");
            assert b != null;
            assert lastname != null;
        }
    }


//    @Test
//    public void shouldInjectMultipleInserts() throws SQLException {
//
//        long id = 10;
//        String fname = "Dings";
//        String lname = "Dongs'),(11,'Dodel','Wutz'); -- ";
//
//        String sqlInsert        = "INSERT INTO doctor VALUES (" +id+ ",'" +fname+ "','" +lname+ "');";
//        String sqlReadTable     = "SELECT * FROM doctor";
//
//        try {
//            var resultNumber = mySqlDatabase.executeUpdate(sqlInsert);
//            assert false;
//        }catch (SQLIntegrityConstraintViolationException ex){}
//
//        var resultSet = mySqlDatabase.executeRead(sqlReadTable);
//
//        while(resultSet.next()) {
//            var a = resultSet.getLong("id");
//            var b = resultSet.getString("firstname");
//            var lastname = resultSet.getString("lastname");
//            assert b != null;
//            assert lastname != null;
//        }
//
//    }
//    @Test
//    public void shouldInjectDropTableDuringInsert() throws SQLException {
//
//        long id = 9999;
//        String fname = "Dings";
//        String lname = "Dongs'); DROP TABLE doctor,patient,appointment,diagnosis; -- ";
//
//        String sqlInsert        = "INSERT INTO doctor VALUES (" +id+ ",'" +fname+ "','" +lname+ "');";
//        String sqlReadTable     = "SELECT * FROM doctor";
//
//        var resultNumber = mySqlDatabase.executeUpdate(sqlInsert);
//
//        var resultSet = mySqlDatabase.executeRead(sqlReadTable);
//
//        while(resultSet.next()) {
//            var a = resultSet.getLong("id");
//            var b = resultSet.getString("firstname");
//            var lastname = resultSet.getString("lastname");
//            assert b != null;
//            assert lastname != null;
//        }
//    }
}
