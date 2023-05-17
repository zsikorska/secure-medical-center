package com.example.ua.secorgapp.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MySqlDatabase {

    private final Connection connection;

    @Autowired
    public MySqlDatabase(MySqlLoader mySqlLoader) throws SQLException {
        this.connection = mySqlLoader.getConnection();
    }

    public ResultSet executeRead(String sql) throws SQLException {
        return connection.createStatement().executeQuery(sql);
    }

    public int executeUpdate(String sql) throws SQLException {
        return connection.createStatement().executeUpdate(sql);
    }

    public boolean isTableExists(String tableName) throws SQLException {
        var resultSet = connection.getMetaData().getTables(null, null, "%", null);
        while(resultSet.next()){
            var table = resultSet.getString("TABLE_NAME");
            if(table.equals(tableName)){
                return true;
            }
        }
        return false;
    }
}
