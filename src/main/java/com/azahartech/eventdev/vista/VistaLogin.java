package com.azahartech.eventdev.vista;

import javax.swing.*;
import java.awt.*;

public class VistaLogin extends JFrame {
    public VistaLogin(){
        super("Acceso a EventDEV");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setLayout(new BorderLayout(10, 10));

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

        this.add(pnlFormulario, BorderLayout.CENTER);

        JLabel bienvenido = new JLabel("Bienvenido a EventDEV");
        bienvenido.setFont(new Font("Arial", Font.BOLD, 18));
        bienvenido.setHorizontalAlignment(SwingConstants.CENTER);

        this.add(bienvenido, BorderLayout.NORTH);

        JPanel pnlBotones = new JPanel();

        pnlBotones.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton btnLogin = new JButton("Entrar");
        JButton btnRegistro = new JButton("Registrarse");

        pnlBotones.add(btnLogin);
        pnlBotones.add(btnRegistro);

        this.add(pnlBotones, BorderLayout.SOUTH);

    }
}