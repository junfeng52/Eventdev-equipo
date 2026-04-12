package com.azahartech.eventdev.modelo;

import com.azahartech.eventdev.pagos.ProcesadorPago;
import com.azahartech.eventdev.util.Exportable;
import com.azahartech.eventdev.util.UtilidadValidacion;

import java.util.Objects;

/**
 * Representa un usuario en el sistema de gestión de eventos.
 * Esta clase encapsula la información básica de un usuario, incluyendo
 * datos personales y su estado de membresía VIP. Implementa la interfaz
 * {@link Exportable} para permitir la exportación de datos en diferentes
 * formatos (XML y CSV).
 * @author Pau Pasalodos Guiral
 * @version 1.0

 */
public class Usuario implements Exportable {
    private String nombre;
    private String email;
    private String telefono;
    private transient boolean esVip;
    private ProcesadorPago tipoPago;

    /**
     * Crea una nuevo objeto Usuario con los parámetros especificados.
     * @param nombre el nombre completo del usuario
     * @param email la dirección de correo electrónico del usuario
     * @param telefono el número de teléfono del usuario
     * @param esVip {@code true} si el usuario es VIP, {@code false} en caso contrario
     */
    public Usuario(String nombre, String email, String telefono, boolean esVip) {
        if (!UtilidadValidacion.esEmailValido(email)) {
            throw new IllegalArgumentException("Email inválido: " + email);
        }
        if (!UtilidadValidacion.esTelefonoValido(telefono)) {
            throw new IllegalArgumentException("Teléfono inválido: " + telefono);
        }

        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.esVip = esVip;
    }

    /**
     * Obtiene el nombre del usuario.
     * @return el nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     * @param nombre el nuevo nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la dirección de correo electrónico del usuario.
     * @return el email del usuario
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece la dirección de correo electrónico del usuario.
     * @param email el nuevo email del usuario
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el número de teléfono del usuario.
     * @return el teléfono del usuario
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del usuario.
     * @param telefono el nuevo número de teléfono del usuario
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Verifica si el usuario tiene estado de membresía VIP.
     * @return {@code true} si el usuario es VIP, {@code false} en caso contrario
     */
    public boolean isEsVip() {
        return esVip;
    }

    /**
     * Establece el estado de membresía VIP del usuario.
     * @param esVip {@code true} para marcar el usuario como VIP, {@code false} en caso contrario
     */
    public void setEsVip(boolean esVip) {
        this.esVip = esVip;
    }

    /**
     * Convierte los datos del usuario a formato XML.
     * @return una representación en XML del usuario
     */
    public ProcesadorPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(ProcesadorPago tipoPago) {
        this.tipoPago = tipoPago;
    }
    @Override
    public String aXML() {
        return "\t<usuario>\n" +
                "\t\t<nombre>" + nombre + "</nombre>\n" +
                "\t\t<email>" + email + "</email>\n" +
                "\t\t<telefono" + telefono + "</telefono>\n" +
                "\t\t<esVip>" + esVip + "</esVip>\n" +
                "\t</usuario>\n";
    }

    /**
     * Convierte los datos del usuario a formato CSV.
     * @return una representación en CSV del usuario en el orden: nombre, email, telefono, esVip
     */
    @Override
    public String aCSV() {
        return  nombre + "," +
                email + "," +
                telefono + "," +
                esVip;
    }

    /**
     *
     * @param obj   the reference object with which to compare.
     * @return {@code true} si el email del usuario es el mismo, {@code false} si no lo es
     *
     */
    @Override
    public boolean equals(Object obj) {
        boolean variable = false;
        if ((obj == null) || (getClass() != obj.getClass())) {
            variable = false;
        } else if  (this == obj){
            variable = true;
        } else {
            Usuario otro = (Usuario) obj;
            variable = this.email.equals(otro.getEmail());
        }
        return variable;
    }
}