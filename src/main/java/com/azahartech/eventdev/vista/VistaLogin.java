package com.azahartech.eventdev.vista;

import com.azahartech.eventdev.servicio.ServicioEvento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static  com.azahartech.eventdev.presentacion.App.SERVICIO_EVENTO;

public class VistaLogin extends JFrame {
    private Container lienzo;
    private ServicioEvento servicioEvento;

    private JTextField txtEmail;
    private JPasswordField txtContraseña;
    private JButton btnLogin;
    private JButton btnRegistro;
    private JButton btnSalir;

    public VistaLogin(){
        this.servicioEvento = SERVICIO_EVENTO;

        initFrame();

        initUI();
    }

    private void initFrame() {
        this.setTitle("Acceso a EventDEV");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
        this.btnSalir = new JButton("Salir");

        pnlBotones.add(this.btnLogin);
        pnlBotones.add(this.btnRegistro);
        pnlBotones.add(this.btnSalir);

        lienzo.add(pnlBotones, BorderLayout.SOUTH);


        initListeners();
    }

    private void initListeners() {
        this.btnSalir.addActionListener(action -> intentarSalir());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                intentarSalir();
            }
        });

        this.txtContraseña.addActionListener(action -> intentarLogin());
        this.btnLogin.addActionListener(action -> intentarLogin());
    }

    private void intentarSalir() {
        int confirmar = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres cerrar la aplicación?", "Confirmar salida", JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void intentarLogin() {
        {
            String email = this.txtEmail.getText();
            String contrasenya = new String(this.txtContraseña.getPassword());

            if (email.equals("admin@eventdev.com") && contrasenya.equals("1234")){
                JOptionPane.showMessageDialog(this, "¡Bienvenido al sistema, Admin!", "Acceso concedido", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                VistaDashboard dashboard = new VistaDashboard(this.txtEmail.getText());
                dashboard.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.","Acceso denegado", JOptionPane.ERROR_MESSAGE);
            }

            txtContraseña.setText("");
            txtContraseña.requestFocus();
        }
    }
}