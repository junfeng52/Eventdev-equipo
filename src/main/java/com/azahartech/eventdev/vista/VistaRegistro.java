package com.azahartech.eventdev.vista;

import com.azahartech.eventdev.util.UtilidadValidacion;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class VistaRegistro extends JFrame {

    private static Container lienzo;

    private JTextField txtNombreCompleto;
    private JTextField txtEmail;
    private JPasswordField txtContraseña;
    private JPasswordField txtRepetirContraseña;
    private JTextField txtEdad;

    private JButton btnGuardar;
    private JButton btnCancelar;



    public VistaRegistro(){
        initFrame();
        initUI();
    }

    private void initFrame() {
        this.setTitle("Acceso a EventDEV");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        BorderLayout borderLayout = new BorderLayout(10, 10);
        this.setLayout(borderLayout);

        lienzo = this.getContentPane();
    }

    private void initUI(){
        JPanel pnlFormulario = new JPanel();
        GridLayout gridLayout = new GridLayout(5, 2);

        gridLayout.setHgap(10);
        gridLayout.setVgap(10);

        pnlFormulario.setLayout(gridLayout);

        JLabel lblNombreCompleto = new JLabel("Nombre completo:");
        this.txtNombreCompleto = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        this.txtEmail = new JTextField();

        JLabel lblPassword = new JLabel("Contraseña:");
        this.txtContraseña = new JPasswordField();

        JLabel lblRepetirContraseña = new JLabel("Repetir Contraseña:");
        this.txtRepetirContraseña = new JPasswordField();

        JLabel lblEdad = new JLabel("Edad:");
        this.txtEdad = new JTextField();


        pnlFormulario.add(lblNombreCompleto);
        pnlFormulario.add(this.txtNombreCompleto);

        pnlFormulario.add(lblEdad);
        pnlFormulario.add(this.txtEdad);

        pnlFormulario.add(lblEmail);
        pnlFormulario.add(this.txtEmail);

        pnlFormulario.add(lblPassword);
        pnlFormulario.add(this.txtContraseña);

        pnlFormulario.add(lblRepetirContraseña);
        pnlFormulario.add(this.txtRepetirContraseña);

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

        this.btnGuardar = new JButton("Guardar");
        this.btnCancelar = new JButton("Cancelar");

        pnlBotones.add(this.btnGuardar);
        pnlBotones.add(this.btnCancelar);

        lienzo.add(pnlBotones, BorderLayout.SOUTH);

        initListener();
    }

    private void initListener() {
        this.txtRepetirContraseña.addActionListener(action -> intentaGuardar());
        this.btnGuardar.addActionListener(action -> intentaGuardar());
    }

    private void intentaGuardar() {
        if (this.txtNombreCompleto.getText().trim().equals("")){
            System.out.println("El campo de nombre completo esta vacia");
        }
        if (this.txtEmail.getText().trim().equals("")){
            System.out.println("El campo de email esta vacia");
        }
        if (this.txtEdad.getText().trim().equals("")){
            System.out.println("El campo de edad esta vacia");
        }
        if (new String(this.txtContraseña.getPassword()).trim().equals("")){
            System.out.println("El campo de contraseña esta vacia");
        }
        if (new String(this.txtRepetirContraseña.getPassword()).trim().equals("")){
            System.out.println("El campo de repetir contraseña esta vacia");
        }
        if (!(new String(this.txtContraseña.getPassword()).trim().equals("")) && !(new String(this.txtRepetirContraseña.getPassword()).trim().equals("")) && new String(this.txtContraseña.getPassword()).equals(new String(this.txtRepetirContraseña.getPassword()))) {
            System.out.println("Contraseña Correcta");
        } else if (!(new String(this.txtContraseña.getPassword()).trim().equals("")) && !(new String(this.txtRepetirContraseña.getPassword()).trim().equals("")) && !(new String(this.txtContraseña.getPassword()).equals(new String(this.txtRepetirContraseña.getPassword())))){
            System.out.println("Contraseña incorrecta");
        }

    }
}
