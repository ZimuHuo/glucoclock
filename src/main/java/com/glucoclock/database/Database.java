package com.glucoclock.database;

import java.net.URISyntaxException;
import java.sql.*;

public class Database {

    public Database(){
    }


    public static ResultSet getResultSet(String sqlStr) throws SQLException, URISyntaxException {

        Connection conn = getConnection();
        Statement s = conn.createStatement();
        ResultSet resultSet = s.executeQuery(sqlStr);
        return resultSet;
    }

    public static Connection getConnection() throws URISyntaxException, SQLException {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        return DriverManager.getConnection(dbUrl);
    }

    public static Object getObject(String sqlStr1, String sqlStr2) throws SQLException, URISyntaxException {
        Connection conn = getConnection();
        Statement s = conn.createStatement();
        ResultSet resultSet = s.executeQuery(sqlStr1);
        Object data = null;
        if (resultSet.next()) {
            data = resultSet.getObject(sqlStr2);
        }else{
            return null;
        }
        return data;
    }

}
