package com.azahartech.eventdev.datos;

import java.sql.SQLException;
import java.sql.Statement;

public class EsquemaDB {

    public static void crearTablas() {
        String crearTablaEventos = "CREATE TABLE IF NOT EXISTS eventos (" +
                    " id VARCHAR(36) PRIMARY KEY," +
                    " nombre VARCHAR(100) NOT NULL," +
                    " recinto VARCHAR(100)," +
                    " precio DECIMAL(10, 2)," +
                    " fecha DATE," +
                    " estado VARCHAR(100)," +
                    " tipo VARCHAR(100)" +
                    ");";

        String crearTablaUsuarios = "CREATE TABLE IF NOT EXISTS usuarios (\n" +
                                    " id VARCHAR(36) PRIMARY KEY," +
                                    " nombreUsuario VARCHAR(50)," +
                                    " email VARCHAR(100) UNIQUE," +
                                    " telefono VARCHAR(9)," +
                                    " esVip BOOLEAN DEFAULT FALSE," +
                                    " tipoPago VARCHAR(100)" +
                                    ");";

        try (Statement statement = ConexionBD.conectar().createStatement()) {
            statement.executeUpdate(crearTablaEventos);
            statement.executeUpdate(crearTablaUsuarios);
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }
}
