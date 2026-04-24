package com.azahartech.eventdev.presentacion;

import com.azahartech.eventdev.modelo.*;
import com.azahartech.eventdev.pagos.PagoBizum;
import com.azahartech.eventdev.pagos.PagoPaypal;
import com.azahartech.eventdev.pagos.ProcesadorPago;
import com.azahartech.eventdev.servicio.*;
import com.azahartech.eventdev.util.UtilidadLog;
import com.azahartech.eventdev.util.UtilidadValidacion;
import com.azahartech.eventdev.vista.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Scanner;

public class App {
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static ServicioUsuario SERVICIO_USUARIO = new ServicioUsuario();
    public final static ServicioEvento SERVICIO_EVENTO = new ServicioEvento();

    private static boolean demoMode = false;
    private static boolean consoleMode = false;
    public static boolean guardarEvento = false;
    public static boolean importarEventos = false;
    public static boolean guardarUsuarios = false;
    public static boolean importarUsuarios = false;
    public static boolean exportarCatalogo = false;
    public static boolean skipLogin = false;

    private static boolean continuidad = true;

    public static void main(String[] args) {

        for (String arg : args) {
            if (arg.equalsIgnoreCase("--demo") || arg.equalsIgnoreCase("-d")) {
                demoMode = true;
            } else if (arg.equalsIgnoreCase("--console") || arg.equalsIgnoreCase("-c")) {
                consoleMode = true;
            } else if (arg.equalsIgnoreCase("--importar_eventos")  || arg.equalsIgnoreCase("-ie")) {
                importarEventos = true;
            } else if (arg.equalsIgnoreCase("--guardar_eventos") || arg.equalsIgnoreCase("-ge")) {
                guardarEvento = true;
            } else if (arg.equalsIgnoreCase("--guardar_usuarios") || arg.equalsIgnoreCase("-gu")) {
                guardarUsuarios = true;
            } else if (arg.equalsIgnoreCase("--exportar_catalogo") || arg.equalsIgnoreCase("-ec")) {
                exportarCatalogo = true;
            } else if (arg.equalsIgnoreCase("--skip_login") || arg.equalsIgnoreCase("-sl")) {
                skipLogin = true;
            }
        }

        UtilidadLog.registrar(NivelLog.INFO,"Inicio de la aplicacíon");


        if (demoMode) {
            generarDemo();
        }

        if (importarEventos) {
            SERVICIO_EVENTO.importarEventosDesdeCSV("datos/eventos_importar.csv");
        }

        if (exportarCatalogo) {
            SERVICIO_EVENTO.exportarCatalogoAXML("datos/agenda_export.xml");
        }

        if (guardarUsuarios) {
            for (Usuario usuario : SERVICIO_USUARIO.listarTodosLosUsuario()) {
                System.out.println(usuario.getIntentosLogin());
            }
            SERVICIO_USUARIO.guardar();
        }


        if (consoleMode){
            console();

        } else {

            SwingUtilities.invokeLater(() -> {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (skipLogin) {
                    new VistaDashboard("Test").setVisible(true);
                } else {
                    new VistaLogin().setVisible(true);
                }
            });
        }
    }

    public static void console() {
        System.out.println("""
                =====================================================
                     EVENTDEV V1.0 - SISTEMA DE GESTIÓN INTEGRAL
                =====================================================
                """);

        if (!demoMode) {
            System.out.println("[FASE 1: REGISTRO DE USUARIOS]");
            registrarUsuarios();

            System.out.println("[FASE 2: REGISTRO DE EVENTOS]");
            registrarEventos();
        }

        System.out.println("[FASE 3: CONTROL DE ESTADOS]");
        controlEstados();

        System.out.println("[FASE 4: PASARELA DE PAGOS]");
        pasarelaPagos();

        System.out.println("[FASE 4: CONSULTAS Y STREAMS]");
        consultasYStreams();

        System.out.println("[FASE 5: EXPORTACIÓN POLIMÓRFICA]");
        exportacionPolimorfica();

        System.out.println("[FASE 6: CIERRE ESPECÍFICO DE EVENTOS]");
        cierreEspecificoEventos();

        System.out.println("""
                           =====================================================
                                           FIN DE LA DEMOSTRACIÓN
                           =====================================================
                           """);

        SCANNER.close();
    }

    // [FASE 1: REGISTRO DE USUARIOS]
    private static void registrarUsuarios(){
        boolean otroUsuario;

        do{
            Usuario usuario;

            String nombre, email, telefono;
            boolean esVip;

            System.out.print("Introduce el nombre:");
            nombre = SCANNER.nextLine();

            do {
                continuidad = true;
                System.out.print("Introduce el email:");
                email = SCANNER.nextLine();

                if (!UtilidadValidacion.esEmailValido(email)) {
                    System.out.println("Error: El email es incorrecto.");
                    continuidad = false;
                }
            } while (!continuidad);


            do {
                continuidad = true;
                System.out.print("Introduce el telefono:");
                telefono = SCANNER.nextLine();

                if (!UtilidadValidacion.esTelefonoValido(telefono)) {
                    System.out.println("Error: El telefono es incorrecto.");
                    continuidad = false;
                }
            } while (!continuidad);

            System.out.print("Eres Vip (Si/No Defecto: No):");
            esVip = SCANNER.nextLine().equalsIgnoreCase("si");

            usuario = new Usuario(nombre, email, telefono, esVip);

            SERVICIO_USUARIO.registrarUsuario(usuario);

            System.out.print("Quieres añadir a otro usuario? (Si/No Defecto: No): ");
            otroUsuario = SCANNER.nextLine().equalsIgnoreCase("si");

        } while (otroUsuario);

    }

    // [FASE 2: REGISTRO DE EVENTOS]
    private static void registrarEventos(){
        boolean otroEvento;
        do {
            Evento evento = null;
            Recinto recinto;

            String nombre, nombreRecinto, direccionRecinto;
            double precio = 0;
            LocalDate fecha = null;
            TipoEvento tipo;
            int aforoMaximo = 0;

            int opcion;

            System.out.print("Introduce el nombre del evento:");
            nombre = SCANNER.nextLine();

            do {
                continuidad = true;
                try {
                    System.out.print("Introduce el fecha del evento (YYYY-MM-DD):");
                    fecha = LocalDate.parse(SCANNER.nextLine());
                } catch (RuntimeException e) {
                    System.out.println("Formato invalido.");
                    continuidad = false;
                }
            } while (!continuidad);

            System.out.print("Introduce el nombre del recinto:");
            nombreRecinto = SCANNER.nextLine();

            System.out.print("Introduce la direccion del recinto:");
            direccionRecinto = SCANNER.nextLine();

            do {
                continuidad = true;

                try {
                    System.out.print("Introduce el aforo maximo del recinto:");
                    aforoMaximo = SCANNER.nextInt();
                    SCANNER.nextLine();

                    if (aforoMaximo < 0) {
                        continuidad = false;
                        System.out.println("Error: El valor tiene que ser mayor o igual a 0.");
                    }
                } catch (RuntimeException e) {
                    SCANNER.nextLine();
                    System.out.println("Error: El valor introducido tiene que ser digitos.");
                    continuidad = false;
                }
            } while (!continuidad);

            recinto = new Recinto(nombreRecinto, direccionRecinto, aforoMaximo);

            do {
                continuidad = true;
                try {
                    System.out.print("Introduce el precio del evento:");
                    precio = SCANNER.nextDouble();
                    SCANNER.nextLine();

                    if (precio < 0) {
                        System.out.println("Error: El valor tiene que ser mayor o igual a 0.");
                        continuidad = false;
                    }
                } catch (RuntimeException e) {
                    SCANNER.nextLine();
                    System.out.println("Error: El valor introducido tiene que ser digitos.");
                    continuidad = false;
                }
            } while (!continuidad);

            do {
                continuidad = true;
                try {
                    opcion = -1;
                    System.out.println("Dime el tipo de evento que es: ");
                    System.out.println("""
                            1. CONCIERTO
                            2. TEATRO
                            3. DEPORTE
                            4. FESTIVAL
                            """);
                    System.out.print("Dime una opcion:");
                    opcion = SCANNER.nextInt();
                    SCANNER.nextLine();

                    if (opcion < 1 || opcion > 4) {
                        System.out.println("Error: La opcion es invalida");
                        continuidad = false;
                    }

                    switch (opcion){
                        case 2:
                        case 4:
                            System.out.println("Esta opcion no esta activa. ");
                            continuidad = false;
                        break;
                    }

                } catch (RuntimeException e) {
                    SCANNER.nextLine();

                    System.out.println("Error: El valor introducido tiene que ser digitos.");

                    opcion = -1;

                    continuidad = false;
                }
            } while (!continuidad);

            switch (opcion) {
                case 1:
                    String bandaPrincipal, listaCanciones;
                    double costeMontaje = 0;

                    tipo = TipoEvento.CONCIERTO;

                    System.out.print("Introduce el nombre de la banda principal:");
                    bandaPrincipal = SCANNER.nextLine();

                    do {
                        continuidad = true;
                        try {
                            System.out.print("Introduce el coste  del evento:");
                            costeMontaje = SCANNER.nextDouble();
                            SCANNER.nextLine();

                            if (costeMontaje < 0) {
                                System.out.println("Error: El valor tiene que ser mayor o igual a 0.");
                                continuidad = false;
                            }
                        } catch (RuntimeException e) {
                            SCANNER.nextLine();
                            System.out.println("Error: El valor introducido tiene que ser digitos.");
                            continuidad = false;
                        }
                    } while (!continuidad);

                    System.out.print("Introduce las canciones que han tocado la banda principal:");
                    listaCanciones = SCANNER.nextLine();

                    evento = new Concierto(nombre, fecha, recinto, precio, tipo, bandaPrincipal, costeMontaje, listaCanciones);

                    break;
                case 2:
                    System.out.println("PROXIMAMENTE");
                    break;
                case 3:
                    String equipoLocal, equipoVisitante;
                    double costeSeguridad = 0;

                    System.out.print("Introduce el nombre del equipo Local:");
                    equipoLocal = SCANNER.nextLine();

                    System.out.print("Introduce el nombre del equipo Visitante:");
                    equipoVisitante = SCANNER.nextLine();

                    do {
                        continuidad = true;
                        try {
                            System.out.print("Introduce el coste  del evento:");
                            costeSeguridad = SCANNER.nextDouble();
                            SCANNER.nextLine();

                            if (costeSeguridad < 0) {
                                System.out.println("Error: El valor tiene que ser mayor o igual a 0.");
                                continuidad = false;
                            }
                        } catch (RuntimeException e) {
                            SCANNER.nextLine();
                            System.out.println("Error: El valor introducido tiene que ser digitos.");
                            continuidad = false;
                        }
                    } while (!continuidad);

                    evento = new Partido(nombre, fecha, recinto, precio, equipoLocal, equipoVisitante, costeSeguridad);
                    break;
                case 4:
                    System.out.println("PROXIMAMENTE");
                    break;
            }

            System.out.println("Se ha creado el evento");
            SERVICIO_EVENTO.registrarEvento(evento);

            System.out.print("Quieres crear otro evento (Si/No Defecto: No):");
            otroEvento = SCANNER.nextLine().equalsIgnoreCase("si");
        } while (otroEvento);
    }

    // [FASE 3: CONTROL DE ESTADOS]
    private static void controlEstados(){
        Evento evento = SERVICIO_EVENTO.listarTodosLosEventos().get(0);

        System.out.printf("> Estado inicial: %s\n", evento.getEstado());

        evento.activarEvento();
        System.out.printf("> Estado tras activarVenta(): %s\n", evento.getEstado());

        System.out.printf("> Precio de venta sugerido : %.2f EUR\n", evento.getPrecio());

        System.out.printf("> Eventos antes de limpieza: %d\n", SERVICIO_EVENTO.listarTodosLosEventos().size());

        SERVICIO_EVENTO.eliminarEventosPasados();

        System.out.printf("> Eventos tras la limpieza: %d\n", SERVICIO_EVENTO.listarTodosLosEventos().size());
    }

    // [FASE 4: PASARELA DE PAGOS]
    private static void pasarelaPagos(){
        Usuario usuario = SERVICIO_USUARIO.listarTodosLosUsuario().get(0);
        ProcesadorPago pagoPaypal = new PagoPaypal(usuario.getEmail());
        ProcesadorPago pagoBizum = new PagoBizum(usuario.getTelefono(), 1234);

        usuario.setTipoPago(pagoPaypal);
        System.out.println("--- Intento de Pago 1: PayPal ---");
        usuario.getTipoPago().procesarPago(45);

        usuario.setTipoPago(pagoBizum);
        System.out.println("--- Intento de Pago 2: Bizum ---");
        usuario.getTipoPago().procesarPago(45);

    }

    // [FASE 4: CONSULTAS Y STREAMS]
    private static void consultasYStreams(){
        System.out.print("> Dime el id para hacer una búsqueda instantánea:");
        String idABuscar = SCANNER.nextLine();

        Evento resultado = SERVICIO_EVENTO.buscarEventoPorId(idABuscar);
        System.out.printf("> Búsqueda instantánea (ID: %s): %s\n", idABuscar, ((resultado != null) ? resultado.getNombre() : "No hay ningun evento"));
        System.out.println("> Catálogo completo ordenado:");
        SERVICIO_EVENTO.mostrarTodoElCatalogo();
    }

    // [FASE 5: EXPORTACIÓN POLIMÓRFICA]
    private static void exportacionPolimorfica(){
        for (Evento evento : SERVICIO_EVENTO.listarTodosLosEventos()) {
            System.out.println("----------------------------------------------------");
            System.out.println("EXPORTANDO XML: ");
            System.out.println(evento.aXML());
            System.out.println("EXPORTANDO CSV: ");
            System.out.println(evento.aCSV());
        }

        for (Usuario usuario : SERVICIO_USUARIO.listarTodosLosUsuario()) {
            System.out.println("----------------------------------------------------");
            System.out.println("EXPORTANDO XML: ");
            System.out.println(usuario.aXML());
            System.out.println("EXPORTANDO CSV: ");
            System.out.println(usuario.aCSV());
        }

    }

    // [FASE 6: CIERRE ESPECÍFICO DE EVENTOS]
    private static void cierreEspecificoEventos(){
        for (Evento evento : SERVICIO_EVENTO.listarTodosLosEventos()) {
            System.out.println("Cerrando Evento: " + evento.getNombre());
            evento.finalizarEvento();
            if (evento.getEstado() == EstadoEvento.FINALIZADO){
                System.out.println("[OK] Evento finalizado con éxito.");
            } else {
                System.out.println("[Error] Evento no se ha finalizado con éxito.");
            }
        }
    }

    private static void generarDemo(){
        SERVICIO_EVENTO.registrarEvento(new Concierto("Concierto Evento 1", LocalDate.of(2021, 12, 31), new Recinto("recinto1", "direccion1", 100), 100, TipoEvento.CONCIERTO, "Banda1", 2000,"Cancion1, Cancion2, Cancion23"));
        SERVICIO_EVENTO.registrarEvento(new Concierto("Concierto Evento 3", LocalDate.of(2033, 12, 31), new Recinto("recinto3", "direccion3", 120), 120, TipoEvento.CONCIERTO, "Banda2", 4000,"Cancion5, Cancion6, Cancion31"));
        SERVICIO_EVENTO.registrarEvento(new Concierto("Concierto Evento 5", LocalDate.of(2026, 12, 31), new Recinto("recinto5", "direccion5", 140), 140, TipoEvento.CONCIERTO, "Banda3", 20000,"Cancion2, Cancion23, Cancion13"));
        SERVICIO_EVENTO.registrarEvento(new Concierto("Concierto Evento 7", LocalDate.of(2027, 12, 31), new Recinto("recinto7", "direccion7", 160), 160, TipoEvento.CONCIERTO, "Banda4", 7000,"Cancion6, Cancion23, Cancion35"));

        SERVICIO_EVENTO.registrarEvento(new Partido("Partido Evento 2", LocalDate.of(2022, 12, 31), new Recinto("recinto2", "direccion2", 110), 110, "equipoLocal1", "EquipoVisitante1", 7000));
        SERVICIO_EVENTO.registrarEvento(new Partido("Partido Evento 4", LocalDate.of(2031, 12, 31), new Recinto("recinto4", "direccion4", 130), 130, "equipoLocal2", "EquipoVisitante2", 70000));
        SERVICIO_EVENTO.registrarEvento(new Partido("Partido Evento 6", LocalDate.of(2026, 12, 31), new Recinto("recinto6", "direccion6", 150), 150, "equipoLocal3", "EquipoVisitante3", 17000));
        SERVICIO_EVENTO.registrarEvento(new Partido("Partido Evento 8", LocalDate.of(2027, 12, 31), new Recinto("recinto8", "direccion8", 170), 170, "equipoLocal4", "EquipoVisitante4", 27000));

        SERVICIO_USUARIO.registrarUsuario(new Usuario("Usuario1", "Usuario1@usuario1.com", "612345678", false));
        SERVICIO_USUARIO.registrarUsuario(new Usuario("Usuario2", "Usuario2@usuario2.com", "612341234", false));
        SERVICIO_USUARIO.registrarUsuario(new Usuario("Usuario3", "Usuario3@usuario3.com", "622345678", true));
        SERVICIO_USUARIO.registrarUsuario(new Usuario("Usuario4", "Usuario4@usuario4.com", "632345678", false));
    }
}