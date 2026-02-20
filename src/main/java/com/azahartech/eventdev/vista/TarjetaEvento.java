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

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(lblTitulo, BorderLayout.NORTH);


        JTextArea taDescripcion = new JTextArea("Fecha: " + fecha + "\nUbicacion confirmada.");
        taDescripcion.setForeground(Color.black);
        taDescripcion.setFont(new Font("Arial", Font.PLAIN, 14));
        taDescripcion.setEditable(false);

        this.add(taDescripcion, BorderLayout.CENTER);


        JButton btnComprar = new JButton("Comprar - " + precio);
        this.add(btnComprar, BorderLayout.SOUTH);

    }

}
