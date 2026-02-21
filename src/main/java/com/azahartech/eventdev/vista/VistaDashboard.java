package com.azahartech.eventdev.vista;

import javax.swing.*;
import java.awt.*;

public class VistaDashboard extends JFrame {

    private static Container lienzo;

    public VistaDashboard() {
        this.setTitle("Dashboard");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);

        ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource("/compraIcon.png")).getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH));
        this.setIconImage(icon.getImage());


        BorderLayout borderLayout = new BorderLayout(10, 10);
        this.setLayout(borderLayout);

        lienzo = this.getContentPane();

        initUI();
    }

    private void initUI() {
        JPanel pnlBarraLateral = new JPanel();
        pnlBarraLateral.setBackground(Color.lightGray);

        GridLayout gridLayoutBarraLateral = new GridLayout(10,1);
        gridLayoutBarraLateral.setVgap(1);
        pnlBarraLateral.setBorder(BorderFactory.createCompoundBorder(pnlBarraLateral.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        pnlBarraLateral.setLayout(gridLayoutBarraLateral);

        String[] nombreBotones = {"Catalogo", "Mis Entradas", "Perfil", "Salir"};

        for (String nombre : nombreBotones) {
            JButton boton = new JButton(nombre);
            boton.setBorder(BorderFactory.createCompoundBorder(boton.getBorder(), BorderFactory.createEmptyBorder(10, 10 , 10, 10)));
            pnlBarraLateral.add(boton);
        }

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
    }
}
