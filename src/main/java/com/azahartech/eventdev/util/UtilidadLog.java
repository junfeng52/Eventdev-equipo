package com.azahartech.eventdev.util;

import com.azahartech.eventdev.modelo.NivelLog;

import java.io.*;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class UtilidadLog {
    private static final String RUTA_CARPETA_DATOS = "datos/";
    private static final String RUTA_LOG = RUTA_CARPETA_DATOS + "auditoria.log";

    public static void registrar(NivelLog nivelLog, String mensaje) {
        File carpetaDatos = new File(RUTA_CARPETA_DATOS);
        File archivo = new File(RUTA_LOG);

        if (!carpetaDatos.exists() && !carpetaDatos.isDirectory()) {
            carpetaDatos.mkdir();
        }

        try (BufferedWriter escribir = new BufferedWriter(new FileWriter(archivo, true))) {
            escribir.write( "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+ "] [" + nivelLog + "] " + mensaje + "\n");
        } catch (IOException e) {
            System.out.println("Error de lectura");
        }
    }
}
