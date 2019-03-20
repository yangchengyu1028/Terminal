package com.ycy.util;

import java.sql.*;

/**
 * 连接数据库
 */
public class DBUtil {
    private static final String DRIVER = ReadFile.getDRIVER();
    private static final String URL = ReadFile.getURL();
    private static final String USER = ReadFile.getUSER();
    private static final String PASSWORD = ReadFile.getPASSWORD();


    static{
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Connection getConn(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
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
