package com.azahartech.eventdev.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class GestorPersistencia {
    public <T> void guardarDatos(List<T> datos, String rutaArchivo) {
        File archivo = new File(rutaArchivo);

        if (archivo.exists()){
            archivo.renameTo(new File(rutaArchivo + ".bak"));
        }

        try (FileOutputStream fos = new FileOutputStream(archivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            // Escribimos el objeto completo (la lista entera)
            oos.writeObject(datos);
            System.out.println("Datos guardados en " + archivo.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("Error al guardar datos: " + e.getMessage());
            e.printStackTrace(); // Útil para saber si falla
        }
    }
}
