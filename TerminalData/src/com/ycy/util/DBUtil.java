package com.ycy.util;

import java.sql.*;

/**
 * 连接数据库
 */
public class DBUtil {
    private static final String DRIVER= ReadFile.getDRIVER();
    private static final String URL1 = ReadFile.getURL1();
    private static final String USER1 = ReadFile.getUSER1();
    private static final String PASSWORD1 = ReadFile.getPASSWORD1();
    private static final String URL2 = ReadFile.getURL2();
    private static final String USER2 = ReadFile.getUSER2();
    private static final String PASSWORD2 = ReadFile.getPASSWORD2();

    static{
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Connection getConnZD(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL1, USER1, PASSWORD1);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection getConnYS(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL2, USER2, PASSWORD2);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Statement stmt, Connection conn){

        try {
            if(stmt != null){
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn){
        try {
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(stmt != null){
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(ResultSet rs, Statement stmt1, Statement stmt2,Connection conn){
        try {
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(stmt1 != null){
                stmt1.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(stmt2 != null){
                stmt2.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(ResultSet rs1, ResultSet rs2,Statement stmt1, Statement stmt2, Connection conn){
        try {
            if(rs1 != null){
                rs1.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(rs2 != null){
                rs2.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(stmt1 != null){
                stmt1.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(stmt2 != null){
                stmt2.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs, Statement stmt1, Statement stmt2,Statement stmt3,Connection conn){
        try {
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(stmt1 != null){
                stmt1.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(stmt2 != null){
                stmt2.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       if (stmt3 !=null){
           try {
               stmt3.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
