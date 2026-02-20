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
        JPanel pnlPrincipal = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
        pnlPrincipal.setLayout(borderLayout);

        JPanel pnlBarraLateral = new JPanel();
        pnlBarraLateral.setBackground(Color.lightGray);

        GridLayout gridLayoutBarraLateral = new GridLayout(10,1);
        pnlBarraLateral.setLayout(gridLayoutBarraLateral);

        JButton catalogo = new JButton("Catalogo");
        JButton misEntradas = new JButton("Mis Entradas");
        JButton perfil = new JButton("Perfil");
        JButton salir = new JButton("Salir");

        pnlBarraLateral.add(catalogo);
        pnlBarraLateral.add(misEntradas);
        pnlBarraLateral.add(perfil);
        pnlBarraLateral.add(salir);

        pnlPrincipal.add(pnlBarraLateral, BorderLayout.WEST);

        JPanel pnlBarraDeEstado = new JPanel();
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        pnlBarraDeEstado.setLayout(flowLayout);

        JLabel lblUsuario = new JLabel("Usuario: Invitado");

        pnlPrincipal.add(lblUsuario, BorderLayout.SOUTH);

        JPanel pnlZonaCentral = new JPanel();
        pnlZonaCentral.setBackground(Color.white);

        pnlPrincipal.add(pnlZonaCentral);

        JPanel pnlLista = new JPanel();
        GridLayout gridLayoutLista = new GridLayout(0, 1);
        pnlLista.setLayout(gridLayoutLista);

        for (int i = 0; i < 10; i++) {
            TarjetaEvento tarjetaEvento = new TarjetaEvento("Concierto A", "Teatro B", "12");
            tarjetaEvento.setBorder(BorderFactory.createEmptyBorder());
            pnlLista.add(tarjetaEvento);
        }

        JScrollPane scroll = new JScrollPane(pnlLista);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        pnlPrincipal.add(scroll, BorderLayout.CENTER);



        lienzo.add(pnlPrincipal);
    }
}
