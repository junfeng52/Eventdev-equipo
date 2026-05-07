package com.azahartech.eventdev.servicio;

import com.azahartech.eventdev.datos.EventoRepositorio;
import com.azahartech.eventdev.datos.ListaEventosWrapper;
import com.azahartech.eventdev.modelo.*;
import com.azahartech.eventdev.datos.RepositorioGenerico;
import com.azahartech.eventdev.util.GestorPersistencia;
import com.azahartech.eventdev.util.UtilidadLog;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.time.LocalDate;
import java.util.*;

import java.io.*;

/**
 * Clase ServicioEvento
 */
public class ServicioEvento {
    private static final String FICHERO_DATOS = "datos/eventos.dat";
    private RepositorioGenerico<Evento> repo = new RepositorioGenerico<>();
    private EventoRepositorio eventoRepositorio = new EventoRepositorio();
    private HashMap<String, Evento> mapaEventos = new HashMap<>();
    private GestorPersistencia gestorPersistencia = new GestorPersistencia();


    public ServicioEvento(){
//        repo.cargarDatos(gestorPersistencia.cargarDatos(FICHERO_DATOS));
        repo.cargarDatos(eventoRepositorio.listarTodos());
    }

    /**
     * Añadir un evento
     * @param nuevoEvento
     */
    public void registrarEvento(Evento nuevoEvento) {
//        repo.guardar(nuevoEvento);
//        mapaEventos.put(nuevoEvento.getId(), nuevoEvento);
        eventoRepositorio.guardar(nuevoEvento);
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
    public List<Evento> mostrarTodoElCatalogo() {
//        mapaEventos.values().forEach(Evento::mostrarInformacion);
        return eventoRepositorio.listarTodos();
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
                    System.out.println("WARN: Fallo en línea" + numlinea + ": Nombre invalido. Log: " + e);
                    UtilidadLog.registrar(NivelLog.WARN, "Fallo en línea" + numlinea + ": Nombre invalido. Log: " + e);
                    nombreStr = null;
                }

                try {
                    ciudadStr = datos[1];
                } catch (RuntimeException e) {
                    System.out.println("WARN: Fallo en línea" + numlinea + ": Ciudad invalido. Log: " + e);
                    UtilidadLog.registrar(NivelLog.WARN, "Fallo en línea" + numlinea + ": Ciudad invalido. Log: " + e);
                    ciudadStr = null;
                }

                try {
                    fecha = LocalDate.parse(datos[2]);
                } catch (RuntimeException e) {
                    System.out.println("WARN: Fallo en línea" + numlinea + ": Fecha invalido. Log: " + e);
                    UtilidadLog.registrar(NivelLog.WARN, "Fallo en línea" + numlinea + ": Fecha invalido. Log: " + e);
                    fecha = null;
                }

                try {
                    aforoInt = Integer.parseInt(datos[3]);
                } catch (RuntimeException e) {
                    System.out.println("WARN: Fallo en línea" + numlinea + ": Aforo invalido. Log: " + e);
                    UtilidadLog.registrar(NivelLog.WARN, "Fallo en línea" + numlinea + ": Aforo invalido. Log: " + e);
                    aforoInt = 0;
                }

                try {
                    precioDouble = Double.parseDouble(datos[4]);
                } catch (RuntimeException e) {
                    System.out.println("WARN: Fallo en línea " + numlinea + ": Precio invalido. Log: " + e);
                    UtilidadLog.registrar(NivelLog.WARN, "Fallo en línea " + numlinea + ": Precio invalido. Log: " + e);
                    precioDouble = 0;
                }

                Partido nuevoEvento = new Partido(nombreStr, fecha, new Recinto(ciudadStr, null, aforoInt), precioDouble, null, null, 0);

                this.repo.guardar(nuevoEvento);
                UtilidadLog.registrar(NivelLog.INFO, "Importado: " + nombreStr);
            }
        }catch (FileNotFoundException e) {
            UtilidadLog.registrar(NivelLog.ERROR, "Archivo no encontrado");
        } catch (IOException e) {
            UtilidadLog.registrar(NivelLog.ERROR, "Error de lectura");
        }
    }

    public void guardar() {
        gestorPersistencia.guardarDatos(repo.listar(), FICHERO_DATOS);
    }

    public void exportarCatalogoAXML(String rutaArchivo) {
        try {
            ListaEventosWrapper wrapper = new ListaEventosWrapper();

            wrapper.setLista(this.repo.listar());

            JAXBContext jaxbContext = JAXBContext.newInstance(ListaEventosWrapper.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(wrapper, new File(rutaArchivo));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public boolean importarCatalogoDesdeXML(String rutaArchivo) {
        try {
            File archivo = new File(rutaArchivo);

            JAXBContext jaxbContext = JAXBContext.newInstance(ListaEventosWrapper.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            ListaEventosWrapper wrapper = (ListaEventosWrapper) unmarshaller.unmarshal(archivo);

            this.repo.guardar(wrapper.getLista());
            System.out.println(this.repo.listar());
            return true;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return false;
    }

}