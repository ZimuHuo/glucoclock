package com.glucoclock.database;

import java.net.URISyntaxException;
import java.sql.*;

public class Database {

    public Database(){
    }


    private static ResultSet selectTable(String sqlStr1) throws URISyntaxException, SQLException {
        Connection conn = getConnection();
        Statement s = conn.createStatement();
        return s.executeQuery(sqlStr1);
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
        ResultSet resultSet = selectTable(sqlStr1);
        Object data = null;
        if (resultSet.next()) {
            data = resultSet.getObject(sqlStr2);
        }else{
            return null;
        }
        return data;
    }


    public static Object getPatintInfo(String id, String sqlStr2) throws SQLException, URISyntaxException{
        String sqlStr = "select * from patients_db where id="+id;
        return getObject(sqlStr,sqlStr2);
    }
    public static void updatePatientInfo(String id, String col, String updatedinfo)throws SQLException, URISyntaxException {
        String sqlStr = "update patients_db set " + col + " = '" +updatedinfo+" ' where" +" id="+id;
        updateTable(sqlStr);
    }

    public static void updateTable(String sqlStr1) throws URISyntaxException, SQLException {
        Connection conn = getConnection();
        Statement s = conn.createStatement();
        s.executeQuery(sqlStr1);
    }
    public static void createTable(String sqlStr1) throws URISyntaxException, SQLException {
        Connection conn = getConnection();
        Statement s = conn.createStatement();
        ResultSet resultSet = s.executeQuery(sqlStr1);
    }

    public static void insertPatient(String sqlStr1)throws URISyntaxException, SQLException {
        Connection conn = getConnection();
        Statement s = conn.createStatement();
        s.executeQuery(sqlStr1);
    }




}
