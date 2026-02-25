package com.azahartech.eventdev.vista;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class TarjetaEvento extends JPanel {

    public static Container lienzo;
    private JButton btnComprar;

    public TarjetaEvento(String titulo, String fecha, String precio) {
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setHgap(10);
        borderLayout.setVgap(10);
        this.setLayout(borderLayout);

        this.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(lblTitulo, BorderLayout.NORTH);


        JTextArea taDescripcion = new JTextArea("Fecha: " + fecha + "\nUbicacion confirmada.");
        taDescripcion.setForeground(Color.black);
        taDescripcion.setFont(new Font("Arial", Font.PLAIN, 14));
        taDescripcion.setBackground(null);
        taDescripcion.setEditable(false);

        this.add(taDescripcion, BorderLayout.CENTER);


        this.btnComprar = new JButton("Comprar - " + precio);
        this.add(this.btnComprar, BorderLayout.SOUTH);

        this.btnComprar.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(this, "¿Quieres comprar una entrada para " + titulo + "?", "Confirmar compra", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(this, "¡Entrada comprada! (simulación)", "Exito", JOptionPane.INFORMATION_MESSAGE);
                this.btnComprar.setEnabled(false);
                this.btnComprar.setText("Comprado");
            }
        });
    }

}
