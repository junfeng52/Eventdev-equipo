package com.azahartech.eventdev.servicio;

import com.azahartech.eventdev.modelo.*;
import com.azahartech.eventdev.datos.RepositorioGenerico;

import java.time.LocalDate;
import java.util.*;

import java.io.*;

/**
 * Clase ServicioEvento
 */
public class ServicioEvento {
    private RepositorioGenerico<Evento> repo = new RepositorioGenerico<>();
    private HashMap<String, Evento> mapaEventos = new HashMap<>();

    /**
     * Añadir un evento
     * @param nuevoEvento
     */
    public void registrarEvento(Evento nuevoEvento) {
        repo.guardar(nuevoEvento);
        mapaEventos.put(nuevoEvento.getId(), nuevoEvento);
    }

    /**
     * Buscar un evento por id
     * @param idABuscar
     * @return
     */
    public Evento buscarEventoPorId(String idABuscar) {
        return mapaEventos.get(idABuscar);
    }

    /**
     * Buscar un evento por precio mas alto
     * @return
     */
    public Evento buscarEventoMasCaro() {
        List<Evento> eventos = repo.listar();
        if (eventos.isEmpty()) return null;

        Evento masCaro = eventos.get(0);

        for (int i = 1; i < eventos.size(); i++) {
            if (eventos.get(i).getPrecio() > masCaro.getPrecio()) {
                masCaro = eventos.get(i);
            }
        }
        return masCaro;
    }

    /**
     * Mostrar catalogo
     */
    public void mostrarTodoElCatalogo() {
        mapaEventos.values().forEach(Evento::mostrarInformacion);
    }

    /**
     * Eliminar eventos pasados
     */
    public void eliminarEventosPasados() {
        Iterator<Evento> iterador = repo.listar().iterator();
        while (iterador.hasNext()) {
            Evento e = iterador.next();
            if (e.getFecha().isBefore(LocalDate.now())) {
                mapaEventos.remove(e.getId());
                System.out.println("Evento caducado eliminado: "+ e.getNombre());
                iterador.remove();
            }
        }
    }
    /**
     * Contar eventos gratuitos
     * @return
     */
    public long contarEventosGratuitos() {
        return mapaEventos.values().stream()
                .filter(e -> e.getPrecio() == 0)
                .count();
    }

    /**
     * Contar eventos por aforo
     * @param aforoMinimo
     * @return
     */
    public long contarEventosPorAforo(int aforoMinimo) {
        return mapaEventos.values().stream()
                .filter(e -> e.getRecinto().getAforoMaximo() >= aforoMinimo)
                .count();
    }

    /**
     * Cierre de eventos
     * @param sc
     */
    public void procesarCierreEventos(Scanner sc) {
        for (Evento e : mapaEventos.values()) {
            if (e.getEstado() == EstadoEvento.ACTIVO) {
                System.out.println("Cerrando: " + e.getNombre());

                if (e instanceof Partido p) {
                    System.out.print("Introduce resultado (ej. 2-1): ");
                    p.setResultadoMarcador(sc.nextLine());
                } else if (e instanceof Concierto c) {
                    System.out.print("Introduce lista de canciones: ");
                }
                e.finalizarEvento();
            }
        }
    }

    /**
     * Lista todos los eventos guardado en la Lista
     * @return
     */
    public List<Evento> listarTodosLosEventos(){
        return repo.listar();
    }

    /**
     * Genera informe financiero
     */
    public void generarInformeFinanciero() {
        Collection<Evento> eventos = mapaEventos.values();
        for (Evento e : mapaEventos.values()) {
            System.out.println("ID: " + e.getId());
            System.out.println("Evento: " + e.getNombre());
            System.out.printf(" - Coste Operativo: %.2f€%n", e.calcularCosteOperativo());
            System.out.printf(" - Precio Sugerido: %.2f€%n", e.calcularPrecioVentaRecomendado());
            System.out.println("-----------------------------------");
        }

    }

    public void importarEventosDesdeCSV(String rutaArchivo) {
        File ruta = new File(rutaArchivo);

        try (BufferedReader lectura = new BufferedReader(new FileReader(ruta))) {
            String linea;
            int numlinea = 1;

            lectura.readLine(); // Salta la primera fila donde estan las columnas

            while ((linea = lectura.readLine()) != null) {
                numlinea++;
                String[] datos = linea.split(";");

                String nombreStr;
                String ciudadStr;
                LocalDate fecha;
                int aforoInt;
                double precioDouble;

                try {
                    nombreStr = datos[0];
                } catch (RuntimeException e) {
                    System.out.println("Error en línea" + numlinea + ": Nombre invalido.");
                    nombreStr = null;
                }

                try {
                    ciudadStr = datos[1];
                } catch (RuntimeException e) {
                    System.out.println("Error en línea" + numlinea + ": Ciudad invalido.");
                    ciudadStr = null;
                }

                try {
                    fecha = LocalDate.parse(datos[2]);
                } catch (RuntimeException e) {
                    System.out.println("Error en línea" + numlinea + ": Fecha invalido.");
                    fecha = null;
                }

                try {
                    aforoInt = Integer.parseInt(datos[3]);
                } catch (RuntimeException e) {
                    System.out.println("Error en línea" + numlinea + ": Aforo invalido.");
                    aforoInt = 0;
                }

                try {
                    precioDouble = Double.parseDouble(datos[4]);
                } catch (RuntimeException e) {
                    System.out.println("Error en línea" + numlinea + ": Precio invalido.");
                    precioDouble = 0;
                }

                Partido nuevoEvento = new Partido(nombreStr, fecha, new Recinto(ciudadStr, null, aforoInt), precioDouble, null, null, 0);

                this.repo.guardar(nuevoEvento);
                System.out.println("Importado: " + nombreStr);
            }
        }catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado");
        } catch (IOException e) {
            System.err.println("Error de lectura");
        }
    }
}