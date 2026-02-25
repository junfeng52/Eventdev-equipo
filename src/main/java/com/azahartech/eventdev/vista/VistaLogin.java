package com.azahartech.eventdev.vista;

import javax.swing.*;
import java.awt.*;

public class VistaLogin extends JFrame {
    private static Container lienzo;

    private JTextField txtEmail;
    private JPasswordField txtContraseña;
    private JButton btnLogin;
    private JButton btnRegistro;

    public VistaLogin(){
        initFrame();

        initUI();
    }

    private void initFrame() {
        this.setTitle("Acceso a EventDEV");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        BorderLayout borderLayout = new BorderLayout(10, 10);
        this.setLayout(borderLayout);

        lienzo = this.getContentPane();
    }

    private void initUI(){
        JPanel pnlFormulario = new JPanel();
        GridLayout gridLayout = new GridLayout(2, 2);

        gridLayout.setHgap(10);
        gridLayout.setVgap(10);

        pnlFormulario.setLayout(gridLayout);

        JLabel lblEmail = new JLabel("Email:");
        this.txtEmail = new JTextField();
        JLabel lblPassword = new JLabel("Contraseña:");
        this.txtContraseña = new JPasswordField();

        pnlFormulario.add(lblEmail);
        pnlFormulario.add(this.txtEmail);
        pnlFormulario.add(lblPassword);
        pnlFormulario.add(this.txtContraseña);

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

        this.btnLogin = new JButton("Entrar");
        this.btnRegistro = new JButton("Registrarse");

        pnlBotones.add(this.btnLogin);
        pnlBotones.add(this.btnRegistro);

        lienzo.add(pnlBotones, BorderLayout.SOUTH);


        initListeners();
    }

    private void initListeners() {
        this.txtContraseña.addActionListener(action -> intentarLogin());
        this.btnLogin.addActionListener(action -> intentarLogin());
    }

    private void intentarLogin() {
        {
            String email = this.txtEmail.getText();
            String contrasenya = new String(this.txtContraseña.getPassword());

            if (email.equals("admin@eventdev.com") && contrasenya.equals("1234")){
                JOptionPane.showMessageDialog(this, "¡Bienvenido al sistema, Admin!", "Acceso concedido", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.","Acceso denegado", JOptionPane.ERROR_MESSAGE);
            }

            txtContraseña.setText("");
            txtContraseña.requestFocus();
        }
    }
}