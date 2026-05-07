package com.azahartech.eventdev.datos;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionBD {
    private static final String URL = "jdbc:mariadb://127.0.0.1:3306/eventdev_db";
    private static final String USER = "eventdev";
    private static final String PASS = "eventdev";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }


}
