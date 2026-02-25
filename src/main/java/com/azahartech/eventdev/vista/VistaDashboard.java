package com.azahartech.eventdev.vista;

import javax.swing.*;
import java.awt.*;

public class VistaDashboard extends JFrame {

    private static Container lienzo;

    private JButton btnCatalogo, btnMisEntradas, btnPerfil, btnSalir;

    public VistaDashboard() {
        initFrame();

        initUI();
    }

    private void initFrame() {
        this.setTitle("Dashboard");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);

        BorderLayout borderLayout = new BorderLayout(10, 10);
        this.setLayout(borderLayout);

        lienzo = this.getContentPane();
    }

    private void initUI() {
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

        JPanel pnlBarraDeEstado = new JPanel();
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        pnlBarraDeEstado.setLayout(flowLayout);

        JLabel lblUsuario = new JLabel("Usuario: Invitado");
        pnlBarraDeEstado.add(lblUsuario);

        lienzo.add(pnlBarraDeEstado, BorderLayout.SOUTH);

        JPanel pnlZonaCentral = new JPanel();
        pnlZonaCentral.setBackground(Color.white);

        lienzo.add(pnlZonaCentral, BorderLayout.CENTER);

        JPanel pnlLista = new JPanel();
        GridLayout gridLayoutLista = new GridLayout(0, 1);
        gridLayoutLista.setHgap(10);
        gridLayoutLista.setVgap(10);
        pnlLista.setBorder(BorderFactory.createCompoundBorder(pnlLista.getBorder(), BorderFactory.createEmptyBorder(10,10,10,10)));
        pnlLista.setLayout(gridLayoutLista);

        for (int i = 0; i < 10; i++) {
            TarjetaEvento tarjetaEvento = new TarjetaEvento("Concierto A", "Teatro B", "12");
            tarjetaEvento.setBorder(BorderFactory.createCompoundBorder(tarjetaEvento.getBorder(), BorderFactory.createEmptyBorder(10,10,10,10)));

            pnlLista.add(tarjetaEvento);
        }

        JScrollPane scroll = new JScrollPane(pnlLista);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        lienzo.add(scroll, BorderLayout.CENTER);

        initListeners();
    }

    private void initListeners(){
        this.btnSalir.addActionListener(action -> {
            int confirmar = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres cerrar la aplicación?", "Confirmar salida", JOptionPane.YES_NO_OPTION);

            if (confirmar == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }
}
