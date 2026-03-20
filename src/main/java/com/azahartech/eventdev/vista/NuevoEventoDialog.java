package com.azahartech.eventdev.vista;

import com.azahartech.eventdev.modelo.Partido;
import com.azahartech.eventdev.servicio.ServicioEvento;
import com.azahartech.eventdev.util.UtilidadValidacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class NuevoEventoDialog extends JDialog {
    private ServicioEvento servicioEvento;

    private JTextField nombreText;
    private JTextField fechaText;
    private JTextField precioText;

    private JButton btnCancelar;
    private JButton btnGuardar;

    public NuevoEventoDialog(JFrame padre, ServicioEvento servicioEvento) {
        super(padre, "Nuevo Evento", true);

        this.servicioEvento = servicioEvento;

        this.setSize(700, 200);
        this.setLocationRelativeTo(this);

        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setVgap(100);
        borderLayout.setHgap(10);

        JPanel panelPrincipal = new JPanel();

        panelPrincipal.setLayout(borderLayout);

        GridLayout gridLayout = new GridLayout(0,2);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);

        JPanel infoLayout = new JPanel();
        infoLayout.setLayout(gridLayout);
        infoLayout.setBorder(BorderFactory.createCompoundBorder(infoLayout.getBorder(), BorderFactory.createEmptyBorder(10, 10, 10, 200)));


        JLabel nombreLabel = new JLabel("Nombre");
        this.nombreText = new JTextField();

        JLabel fechaLabel = new JLabel("Fecha");
        this.fechaText = new JTextField();
        this.fechaText.setToolTipText("Formato: AAAA-MM-DD");

        JLabel precioLabel = new JLabel("Precio");
        this.precioText = new JTextField();

        infoLayout.add(nombreLabel);
        infoLayout.add(this.nombreText);
        infoLayout.add(fechaLabel);
        infoLayout.add(this.fechaText);
        infoLayout.add(precioLabel);
        infoLayout.add(this.precioText);

        JPanel botonerasPanel = new JPanel();

        this.btnCancelar = new JButton("Cancelar");
        this.btnGuardar = new JButton("Guardar");

        botonerasPanel.add(this.btnGuardar);
        botonerasPanel.add(this.btnCancelar);

        panelPrincipal.add(infoLayout, BorderLayout.CENTER);
        panelPrincipal.add(botonerasPanel, BorderLayout.EAST);

        this.setContentPane(panelPrincipal);
        initListener();
    }

    private void initListener(){
        this.precioText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (UtilidadValidacion.esPrecioValido(precioText.getText().trim())){
                    precioText.setForeground(Color.WHITE);
                } else {
                    precioText.setForeground(Color.RED);
                }
                super.keyTyped(e);
            }
        });
        this.btnCancelar.addActionListener(action -> cancelar());
        this.btnGuardar.addActionListener(action -> guardar());
    }

    private void cancelar() {
        this.dispose();
    }

    private void guardar() {
        String nombre = null;
        LocalDate fecha = null;
        double precio = -1;

        boolean nombreValida;
        boolean fechaValida;
        boolean precioValido;

        nombre = this.nombreText.getText().trim();
        nombreValida = nombre.length() > 0;

        try {
            fecha = LocalDate.parse(this.fechaText.getText().trim());
            fechaValida = true;
        } catch (RuntimeException e) {
            fechaValida = false;
        }

        try {
            precio = Double.parseDouble(this.precioText.getText().trim());
            precioValido = true;
        } catch (RuntimeException e) {
            precioValido = false;
        }
        if (nombreValida && fechaValida && precioValido) {
            System.out.printf("%s %s %.2f", nombre, fecha, precio);
            this.servicioEvento.registrarEvento(new Partido(nombre, fecha, null, precio, null, null, 0));
        } else {
            String msg = "Parametros invalidos en: \n";
            ArrayList<String> invalidos = new ArrayList<>();
            if (!nombreValida) {
                invalidos.add("Nombre (El nombre no puede estar vacio)");
            }

            if (!fechaValida) {
                invalidos.add("Fecha (El formato de fecha tiene que ser YYYY-MM-DD)");
            }

            if (!precioValido) {
                invalidos.add("Precio (Tiene que ser numeros y el delimitador tiene que ser '.')");
            }
            JOptionPane.showMessageDialog(this, msg + String.join("\n", invalidos));
        }

    }
}
