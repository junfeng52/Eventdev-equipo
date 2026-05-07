package com.azahartech.eventdev.datos;

import com.azahartech.eventdev.modelo.Evento;
import com.azahartech.eventdev.modelo.Partido;
import com.azahartech.eventdev.modelo.Recinto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventoRepositorio {

    public boolean guardar(Evento evento) {
        boolean successful = false;

        String sqlQuerry = "INSERT INTO eventos (id, nombre, recinto, precio, fecha, estado, tipo) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConexionBD.conectar().prepareStatement(sqlQuerry)) {
            preparedStatement.setString(1, evento.getId());
            preparedStatement.setString(2, evento.getNombre());
            preparedStatement.setString(3, evento.getRecinto().getNombre());
            preparedStatement.setDouble(4, evento.getPrecio());
            preparedStatement.setDate(5, Date.valueOf(evento.getFecha()));
            preparedStatement.setString(6, evento.getEstado().toString());
            preparedStatement.setString(7, evento.getTipo().toString());

            preparedStatement.execute();
            successful = true;

        } catch (SQLException e) {
            System.err.println("Error: "+ e);
        }

        return successful;
    }

    public List<Evento> listarTodos() {
        ArrayList resultado = new ArrayList<>();

        String sqlQuerry = "SELECT * FROM eventos";

        try (Statement statement = ConexionBD.conectar().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlQuerry);

            while (resultSet.next()) {
                try {
                    Evento evento = new Partido(
                            resultSet.getString("nombre"),
                            resultSet.getDate("fecha").toLocalDate(),
                            new Recinto(resultSet.getString("recinto"), null, 0),
                            resultSet.getInt("precio"),
                            null,
                            null,
                            0
                    );
                    resultado.add(evento);
                } catch (Exception e) {
                    System.err.println("Error: " + e);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }

        return resultado;
    }

    public Evento buscarPorId(String id) {
        Evento result = null;

        String sqlQuerry = "SELECT * FROM eventos WHERE id = ?";

        try (PreparedStatement preparedStatement = ConexionBD.conectar().prepareStatement(sqlQuerry)) {
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                try {
                    result = new Partido(
                            resultSet.getString("nombre"),
                            resultSet.getDate("fecha").toLocalDate(),
                            new Recinto(resultSet.getString("recinto"), null, 0),
                            resultSet.getInt("precio"),
                            null,
                            null,
                            0
                    );
                } catch (Exception e) {
                    System.err.println("Error: " + e);
                }
            }


        } catch (SQLException e) {
            System.err.println("Error: "+ e);
        }

        return result;
    }

    public boolean eliminar(String id) {
        boolean successful = false;

        String sqlQuerry = "DELETE FROM eventos WHERE id = ?";

        try (PreparedStatement preparedStatement = ConexionBD.conectar().prepareStatement(sqlQuerry)) {
            preparedStatement.setString(1, id);

            successful = preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("Error: "+ e);
        }

        return successful;
    }
}
