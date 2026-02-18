package com.azahartech.eventdev.vista;

import javax.swing.*;
import java.awt.*;

public class VistaRegistro extends JFrame {

    private static Container lienzo;


    public VistaRegistro(){
        super("Acceso a EventDEV");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        BorderLayout borderLayout = new BorderLayout(10, 10);
        this.setLayout(borderLayout);
        lienzo = this.getContentPane();

        initUI();
    }

    private void initUI(){
        JPanel pnlFormulario = new JPanel();
        GridLayout gridLayout = new GridLayout(5, 2);

        gridLayout.setHgap(10);
        gridLayout.setVgap(10);

        pnlFormulario.setLayout(gridLayout);

        JLabel lblNombreCompleto = new JLabel("Nombre completo:");
        JTextField txtNombreCompleto = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();

        JLabel lblPassword = new JLabel("Contraseña:");
        JPasswordField txtPassword = new JPasswordField();

        JLabel lblRepetirContraseña = new JLabel("Repetir Contraseña:");
        JPasswordField txtRepetirContraseña = new JPasswordField();

        JLabel lblEdad = new JLabel("Edad:");
        JPasswordField txtEdad = new JPasswordField();


        pnlFormulario.add(lblNombreCompleto);
        pnlFormulario.add(txtNombreCompleto);

        pnlFormulario.add(lblEmail);
        pnlFormulario.add(txtEmail);

        pnlFormulario.add(lblPassword);
        pnlFormulario.add(txtPassword);

        pnlFormulario.add(lblRepetirContraseña);
        pnlFormulario.add(txtRepetirContraseña);

        pnlFormulario.add(lblEdad);
        pnlFormulario.add(txtEdad);

        lienzo.add(pnlFormulario, BorderLayout.CENTER);

        JLabel bienvenido = new JLabel("Bienvenido a EventDEV");
        bienvenido.setFont(new Font("Arial", Font.BOLD, 18));
        bienvenido.setHorizontalAlignment(SwingConstants.CENTER);

        lienzo.add(bienvenido, BorderLayout.NORTH);


        JPanel pnlBotones = new JPanel();

        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
        flowLayout.setHgap(10);
        flowLayout.setVgap(10);
        pnlBotones.setLayout(flowLayout);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        pnlBotones.add(btnGuardar);
        pnlBotones.add(btnCancelar);

        lienzo.add(pnlBotones, BorderLayout.SOUTH);

    }
}
