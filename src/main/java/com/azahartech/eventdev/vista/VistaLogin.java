package com.azahartech.eventdev.vista;

import javax.swing.*;
import java.awt.*;

public class VistaLogin extends JFrame {

    private static Container lienzo;

    public VistaLogin(){
        this.setTitle("Acceso a EventDEV");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        BorderLayout borderLayout = new BorderLayout(10, 10);
        this.setLayout(borderLayout);
        lienzo = this.getContentPane();

        initUI();
    }

    private void initUI(){
        JPanel pnlFormulario = new JPanel();
        GridLayout gridLayout = new GridLayout(2, 2);

        gridLayout.setHgap(10);
        gridLayout.setVgap(10);

        pnlFormulario.setLayout(gridLayout);

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        JLabel lblPassword = new JLabel("Contraseña:");
        JPasswordField txtPassword = new JPasswordField();

        pnlFormulario.add(lblEmail);
        pnlFormulario.add(txtEmail);
        pnlFormulario.add(lblPassword);
        pnlFormulario.add(txtPassword);

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

        JButton btnLogin = new JButton("Entrar");
        JButton btnRegistro = new JButton("Registrarse");

        pnlBotones.add(btnLogin);
        pnlBotones.add(btnRegistro);

        lienzo.add(pnlBotones, BorderLayout.SOUTH);

    }
}