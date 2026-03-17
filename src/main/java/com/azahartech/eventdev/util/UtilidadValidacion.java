package com.azahartech.eventdev.util;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.time.LocalDate;
import java.util.*;

public class UtilidadValidacion {
    /**
     *
     * @param email
     * Sirve para la validacion del email.
     * @return
     */
    public static boolean esEmailValido(String email) {
        return email.matches("[a-zA-Z0-9\\.]+@[a-zA-Z0-9\\.]+\\.[a-z]{2,4}");
    }

    /**
     * @param fecha
     * Comprueba que la fecha de un evento sea futura.
     * */
    public static boolean esFechaFutura(LocalDate fecha) {
        if (fecha == null) {
            return false;
        } else {
            return fecha.isAfter(LocalDate.now());
        }
    }
    /**
     * Valida si un telefono tiene 9 digitos
     * @param telefono
     * @return
     */
    public static boolean esTelefonoValido(String telefono) {
        return telefono.matches("^[0-9]{9}");
    }

    /**
     * Este metodo genera un ID Automaticamente
     *
     * Formato:
     * 1 Palabra: AAAAA
     * 2 Palabra: AAABB
     * 3 Palabra: AABBC
     * 4 Palabra: AABCD
     * 5 Palabra: ABCDE
     * Mas de 6 : ABCDE
     *
     * @param nombre
     * @return devuelve un ID
     */
    public static String generaraIdAutomatico(String nombre){
        String resultado;

        final String PREDETERMINADO = "DEFAU";
        nombre = (nombre != null && nombre.strip() != "") ? nombre.strip() : PREDETERMINADO;

        int año = LocalDate.now().getYear();
        String siglas;

        String[] partesDeNombre = nombre.split(" ");

        switch (partesDeNombre.length){
            case 1:
                siglas = nombre.substring(0, (nombre.length() > 5)? 5: nombre.length()).toUpperCase();
                break;

            case 2:
                siglas = (partesDeNombre[0].substring(0, (partesDeNombre[0].length() >= 3) ? 3 : partesDeNombre[0].length()) +
                          partesDeNombre[1].substring(0, (partesDeNombre[1].length() >= 2) ? 2 : partesDeNombre[1].length())).toUpperCase();
                break;

            case 3:
                siglas = (partesDeNombre[0].substring(0, (partesDeNombre[0].length() >= 2) ? 2 : partesDeNombre[0].length()) +
                          partesDeNombre[1].substring(0, (partesDeNombre[1].length() >= 2) ? 2 : partesDeNombre[1].length()) +
                          partesDeNombre[2].substring(0, (partesDeNombre[2].length() >= 1) ? 1 : partesDeNombre[2].length())).toUpperCase();
                break;

            case 4:
                siglas = (partesDeNombre[0].substring(0, (partesDeNombre[0].length() >= 2) ? 2 : partesDeNombre[0].length()) +
                          partesDeNombre[1].substring(0, (partesDeNombre[1].length() >= 1) ? 1 : partesDeNombre[1].length()) +
                          partesDeNombre[2].substring(0, (partesDeNombre[2].length() >= 1) ? 1 : partesDeNombre[2].length()) +
                          partesDeNombre[3].substring(0, (partesDeNombre[3].length() >= 1) ? 1 : partesDeNombre[3].length())).toUpperCase();
                break;

            case 5:
            default:
                siglas = (partesDeNombre[0].substring(0, (partesDeNombre[0].length() >= 1) ? 1 : partesDeNombre[0].length()) +
                          partesDeNombre[1].substring(0, (partesDeNombre[1].length() >= 1) ? 1 : partesDeNombre[1].length()) +
                          partesDeNombre[2].substring(0, (partesDeNombre[2].length() >= 1) ? 1 : partesDeNombre[2].length()) +
                          partesDeNombre[3].substring(0, (partesDeNombre[3].length() >= 1) ? 1 : partesDeNombre[3].length()) +
                          partesDeNombre[4].substring(0, (partesDeNombre[4].length() >= 1) ? 1 : partesDeNombre[4].length())).toUpperCase();
                break;
        }


        resultado = String.format("EVT-%s-%s", año, siglas);

        return resultado;
    }

    public static boolean esCampoVacio(JTextComponent component) {
        boolean resultado;

        if (component instanceof JPasswordField) {
            resultado = "".equals(new String(((JPasswordField) component).getPassword()).trim());
        } else {
            resultado = "".equals(component.getText().trim());
        }


        return resultado;
    }
}
