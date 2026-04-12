package com.azahartech.eventdev.util;

import java.io.*;
import java.util.ArrayList;
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

    public <T> List<T> cargarDatos(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        // 1. Comprobación defensiva: si no hay fichero, no hacemos nada
        if (!archivo.exists()) {
            return new ArrayList<>(); // Devuelve la lista vacía para empezar de 0
        }

        // 2. Flujo de lectura
        try (FileInputStream fis = new FileInputStream(archivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
        // 3. Deserialización y Casting
            Object objetoLeido = ois.readObject();
            return (List<T>) objetoLeido; // Advertencia de casting (es normal)

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
