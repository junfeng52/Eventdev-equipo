package com.azahartech.eventdev.modelo;

import com.azahartech.eventdev.util.Exportable;
import com.azahartech.eventdev.util.UtilidadValidacion;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase abstracta que representa un evento cultural o deportivo.
 * Sirve como plantilla base para diferentes tipos de eventos específicos
 * (conciertos, partidos deportivos, obras de teatro, etc.).
 *
 * Esta clase encapsula la información común a todos los eventos y
 * proporciona métodos para calcular precios de venta con margen estándar.
 */

public abstract class Evento implements Exportable, Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private LocalDate fecha;
    private Recinto recinto;
    private double precio;
    private String id;
    private EstadoEvento estado;
    private TipoEvento tipo;

    // Metodo
    /**
     * Constructor para crear un nuevo evento.
     * Inicializa los atributos básicos comunes a todos los tipos de eventos.
     * El estado se establece por defecto como "PLANIFICADO".
     */
    public Evento(String nombre, LocalDate fecha, Recinto recinto, double precio, TipoEvento nuevoTipo) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.recinto = recinto;
        this.precio = precio;
        estado = EstadoEvento.PLANIFICADO;
        tipo = nuevoTipo;
        this.id = UtilidadValidacion.generaraIdAutomatico(nombre);

    }

    // Getters

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public Recinto getRecinto() {
        return recinto;
    }

    public String getId() {
        return id;
    }

    public EstadoEvento getEstado() {
        return estado;
    }

    public TipoEvento getTipo() {
        return tipo;
    }


    // Setters


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setRecinto(Recinto recinto) {
        this.recinto = recinto;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setEstado(EstadoEvento estado) {
        this.estado = estado;
    }

    public void setTipo(TipoEvento tipo) {
        this.tipo = tipo;
    }

    public final double calcularPrecioVentaRecomendado() {
        return calcularCosteOperativo() * 1.3;
    }

    public abstract double calcularCosteOperativo();

    public void mostrarInformacion(){
        System.out.printf("---EVENTO---%nEl evento %s se realizará el dia %s en %s%n---%n", nombre, fecha, recinto.getNombre());
    }

    public void activarEvento() {
        this.estado = EstadoEvento.ACTIVO;
    }

    public void finalizarEvento() {
        this.estado = EstadoEvento.FINALIZADO;
    }

    @Override
    public boolean equals(Object otroEvento) {
        if (this == otroEvento) return true;
        if (otroEvento == null || getClass() != otroEvento.getClass()) return false;
        Evento evento = (Evento) otroEvento;
        return id != null && id.equals(evento.id);
    }
    @Override
    public String aXML() {
        return "\n\t<nombre>" + this.nombre + "</nombre>\n" +
                "\t<fecha>" + this.fecha + "</fecha>\n" +
                "\t<aforo>" + this.recinto.getAforoMaximo() + "</aforo>\n" +
                "\t<precio>" + this.precio + "</precio>\n" +
                "\t<codigoevento>" + getId() + "</codigoevento>";
    }

    @Override
    public String aCSV() {
        return this.nombre + ";" + this.fecha + ";" + this.recinto.getAforoMaximo() + ";"
                + this.precio + ";" + ";" + getId();
    }

}

