package com.azahartech.eventdev.vista;

import com.azahartech.eventdev.modelo.Evento;
import com.azahartech.eventdev.servicio.ServicioEvento;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import static com.azahartech.eventdev.presentacion.App.SERVICIO_EVENTO;

public class VistaDashboard extends JFrame {

    private Container lienzo;

    private JButton btnCatalogo, btnMisEntradas, btnPerfil, btnSalir;

    private String nombreUsuario;

    private ServicioEvento servicioEvento;

    private JTable eventosTable;

    private JMenuBar principalMenuBar;

    private JMenu archivoMenu;

    private JMenuItem importarMenuItem;
    private JMenuItem exportarMenuItem;
    private JMenuItem cerrarSessiónMenuItem;
    private JMenuItem salirMenuItem;

    private JFileChooser fileChooser = new JFileChooser();

    private JMenu accionesMenu;

    private JMenuItem nuevoEventoItem;

    private DefaultTableModel eventosTableModel;


    public VistaDashboard(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.servicioEvento = SERVICIO_EVENTO;
        this.fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo Xml", "xml"));
        this.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        initFrame();

        initUI();
    }

    private void initFrame() {
        this.setTitle("Dashboard");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);

        BorderLayout borderLayout = new BorderLayout(10, 10);
        this.setLayout(borderLayout);

        lienzo = this.getContentPane();
    }

    private void initUI() {
        initBarraLateral();

        initBarraDeEstado();

        initZonaCentral();

        initLista();

        initMenuBar();

        initListeners();
    }

    private void initBarraLateral() {
        JPanel pnlBarraLateral = new JPanel();
        pnlBarraLateral.setBackground(Color.lightGray);

        GridLayout gridLayoutBarraLateral = new GridLayout(10,1);
        gridLayoutBarraLateral.setVgap(1);
        pnlBarraLateral.setBorder(BorderFactory.createCompoundBorder(pnlBarraLateral.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        pnlBarraLateral.setLayout(gridLayoutBarraLateral);

        this.btnCatalogo = new JButton("Catalogo");
        this.btnMisEntradas = new JButton("Mis Entradas");
        this.btnPerfil = new JButton("Perfil");
        this.btnSalir = new JButton("Salir");


        this.btnCatalogo.setBorder(BorderFactory.createCompoundBorder(this.btnCatalogo.getBorder(), BorderFactory.createEmptyBorder(10, 10 , 10, 10)));
        this.btnMisEntradas.setBorder(BorderFactory.createCompoundBorder(this.btnMisEntradas.getBorder(), BorderFactory.createEmptyBorder(10, 10 , 10, 10)));
        this.btnPerfil.setBorder(BorderFactory.createCompoundBorder(this.btnPerfil.getBorder(), BorderFactory.createEmptyBorder(10, 10 , 10, 10)));
        this.btnSalir.setBorder(BorderFactory.createCompoundBorder(this.btnSalir.getBorder(), BorderFactory.createEmptyBorder(10, 10 , 10, 10)));


        pnlBarraLateral.add(this.btnCatalogo);
        pnlBarraLateral.add(this.btnMisEntradas);
        pnlBarraLateral.add(this.btnPerfil);
        pnlBarraLateral.add(this.btnSalir);


        lienzo.add(pnlBarraLateral, BorderLayout.WEST);
    }

    private void initZonaCentral() {
        JPanel pnlZonaCentral = new JPanel();
        pnlZonaCentral.setBackground(Color.white);

        lienzo.add(pnlZonaCentral, BorderLayout.CENTER);
    }

    private void initBarraDeEstado() {
        JPanel pnlBarraDeEstado = new JPanel();
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        pnlBarraDeEstado.setLayout(flowLayout);

        JLabel lblUsuario = new JLabel("Usuario: " + this.nombreUsuario);
        pnlBarraDeEstado.add(lblUsuario);

        JButton verDetallesButton = new JButton("Ver detalles");
        verDetallesButton.addActionListener(action -> verDetalles());

        pnlBarraDeEstado.add(verDetallesButton);

        lienzo.add(pnlBarraDeEstado, BorderLayout.SOUTH);
    }

    private void initLista() {
        JPanel pnlLista = new JPanel();
        GridLayout gridLayoutLista = new GridLayout(0, 1);
        gridLayoutLista.setHgap(10);
        gridLayoutLista.setVgap(10);
        pnlLista.setBorder(BorderFactory.createCompoundBorder(pnlLista.getBorder(), BorderFactory.createEmptyBorder(10,10,10,10)));
        pnlLista.setLayout(gridLayoutLista);

        String[] columnas = {"ID", "Nombre", "Fecha", "Precio"};

        eventosTableModel = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

//        for (int i = 0; i < 100; i++) {
//            servicioEvento.registrarEvento(new Partido("Partido " + i, LocalDate.now().plusDays(i), new Recinto("Recinto " + i, "Direccion " + i, (int) Math.pow(i + Math.sqrt(i+1) ,i)), (double) Math.round(Math.pow(i+1 + Math.sqrt(i+1) , Math.random()*3)*100)/100, "EquipoLocal " + i, "EquipoVisitante " + i, (double) Math.round(Math.pow(i+1 + Math.sqrt(i+1) , Math.random()*3)*100)/100));
//        }

        refrescarTabla();

        eventosTable = new JTable(eventosTableModel);


//        for (int i = 0; i < 10; i++) {
//            TarjetaEvento tarjetaEvento = new TarjetaEvento("Concierto A", "Teatro B", "12");
//            tarjetaEvento.setBorder(BorderFactory.createCompoundBorder(tarjetaEvento.getBorder(), BorderFactory.createEmptyBorder(10,10,10,10)));
//
//            pnlLista.add(tarjetaEvento);
//        }



        // JScrollPane scroll = new JScrollPane(pnlLista);
        JScrollPane scroll = new JScrollPane(eventosTable);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        lienzo.add(scroll, BorderLayout.CENTER);
    }

    private void initMenuBar() {
        this.principalMenuBar = new JMenuBar();

        this.archivoMenu = new JMenu("Archivo");

        this.importarMenuItem = new JMenuItem("Importar");
        this.exportarMenuItem = new JMenuItem("Exportar a XML");
        this.cerrarSessiónMenuItem = new JMenuItem("Cerrar sessión");
        this.salirMenuItem = new JMenuItem("Salir");

        this.archivoMenu.add(this.importarMenuItem);
        this.archivoMenu.add(this.exportarMenuItem);
        this.archivoMenu.add(this.cerrarSessiónMenuItem);
        this.archivoMenu.add(this.salirMenuItem);

        this.accionesMenu = new JMenu("Acciones");

        this.nuevoEventoItem = new JMenuItem("Nuevo evento");

        this.accionesMenu.add(this.nuevoEventoItem);

        this.principalMenuBar.add(archivoMenu);
        this.principalMenuBar.add(accionesMenu);

        this.setJMenuBar(this.principalMenuBar);
    }

    private void initListeners(){
        this.nuevoEventoItem.addActionListener(action -> crearNuevoEvento());

        this.importarMenuItem.addActionListener(action -> importarEventos());
        this.exportarMenuItem.addActionListener(action -> exportarEventos());

        this.cerrarSessiónMenuItem.addActionListener(action -> intentarCerrarSession());
        this.salirMenuItem.addActionListener(action -> intentarSalir());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                intentarCerrarSession();
            }
        });

        this.btnSalir.addActionListener(action -> intentarCerrarSession());
    }

    private void refrescarTabla(){
        eventosTableModel.setRowCount(0);
        for (Evento evento : servicioEvento.listarTodosLosEventos()) {
            Object[] datos = {evento.getId(), evento.getNombre(), evento.getFecha(), evento.getPrecio()};
            eventosTableModel.addRow(datos);
        }
        servicioEvento.guardar();
    }

    private void verDetalles() {
        int filaSeleccionada = eventosTable.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un evento");
        }
        String nombreEvento = eventosTable.getValueAt(filaSeleccionada, 1).toString();
        String fechaEvento = eventosTable.getValueAt(filaSeleccionada, 2).toString();
        String precioEvento = eventosTable.getValueAt(filaSeleccionada, 3).toString();
        JOptionPane.showMessageDialog(this, "Has seleccionado: " + nombreEvento + "\n Fecha: " + fechaEvento + "\n Precio: " + precioEvento + " €");
    }

    private void crearNuevoEvento() {
        NuevoEventoDialog nuevoEventoDialog = new NuevoEventoDialog(this);
        nuevoEventoDialog.setVisible(true);
        refrescarTabla();
    }

    private void intentarCerrarSession() {
        int confirmar = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres cerrar sesión?", "Confirmar cierre de session", JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {
            this.dispose();
            VistaLogin vistaLogin = new VistaLogin();
            vistaLogin.setVisible(true);
        }
    }

    private void intentarSalir() {
        int confirmar = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres cerrar el programa?", "Confirmar cierre del programa", JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void importarEventos() {
        this.fileChooser.showOpenDialog(this);
        File file = this.fileChooser.getSelectedFile();

        if (file != null) {
            this.servicioEvento.importarCatalogoDesdeXML(file.getAbsolutePath());
            refrescarTabla();
            JOptionPane.showMessageDialog(this, "Eventos importados con exito.");
        } else  {
            JOptionPane.showMessageDialog(this, "El archivo no se puede importar.");
        }
    }

    private void exportarEventos() {
        this.fileChooser.showSaveDialog(this);
        File file = fileChooser.getSelectedFile();
        if (!file.getAbsolutePath().endsWith(".xml")) {
            file = new File(file.getAbsolutePath() + ".xml");
        }
        if (file != null) {
            this.servicioEvento.exportarCatalogoAXML(file.getAbsolutePath());
            JOptionPane.showMessageDialog(this, "El archivo se ha exportar.");
        } else {
            JOptionPane.showMessageDialog(this, "El archivo no se puede exportar.");
        }
    }
}
