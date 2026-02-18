package com.azahartech.eventdev.vista;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class TarjetaEvento extends JPanel {

    public static Container lienzo;

    public TarjetaEvento(String titulo, String fecha, String precio) {
        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);


        this.setBorder(BorderFactory.createLineBorder(Color.black));

        JTextArea taDescripcion = new JTextArea();
        this.add(taDescripcion, BorderLayout.CENTER);

        ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource("/compraIcon.png")).getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH));


        JButton btnComprar = new JButton("Comprar - " + precio, icon);
        this.add(btnComprar, BorderLayout.SOUTH);

        JLabel lblTitulo = new JLabel("Titulo");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setLocale(null);
        this.add(lblTitulo, BorderLayout.NORTH);
    }

}
