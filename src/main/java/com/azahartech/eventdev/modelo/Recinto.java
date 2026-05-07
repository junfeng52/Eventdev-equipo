package com.azahartech.eventdev.modelo;

import com.azahartech.eventdev.util.Exportable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Arrays;
/**
 *
 * El proposito de la clase Recinto modela los datos de cada recinto
 * con su nombre, direccion, aforomaximo y asientos vips.
 *
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Recinto implements Exportable, Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String direccion;
    private int aforoMaximo;
    private boolean[] asientosVip;

    public Recinto() {}

    public Recinto(String nuevoNombre, String nuevoDireccion, int nuevoAforoMaximo){
        nombre = nuevoNombre;
        direccion = nuevoDireccion;
        aforoMaximo = nuevoAforoMaximo;
        this.asientosVip = new boolean[10];
        for (int i = 0; i < asientosVip.length; i++){
            asientosVip[i] = false;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getAforoMaximo() {
        return aforoMaximo;
    }

    public boolean[] getAsientosVip() {
        return asientosVip;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setAforoMaximo(int aforoMaximo) {
        this.aforoMaximo = aforoMaximo;
    }

    public void mostrarInformacion(){
        System.out.printf("El nombre del recinto es %s, la direccion es %s y el aforo maximo es %d%n",nombre,direccion,aforoMaximo);
        System.out.println("Quedan " + contarAsientosVipLibres() + " asientos vips libres");
    }

    /**
     *
     * @param numeroAsiento El metodo reservarAsientosVip recibe un numero de asiento a reservar,
     * tambien comprueba que el asiento este en el rango de asientos disponibles y que el asiento no este reservado.
     *
     * */
    public boolean reservarAsientoVip(int numeroAsiento){
        if (numeroAsiento < 1 || numeroAsiento > 10){
            System.out.println("El numero de asiento no esta en el rango");
            return false;
        } if (asientosVip[numeroAsiento -1]){
            System.out.println("Este asiento ya esta reservado");
            return false;
        }
        asientosVip[numeroAsiento - 1] = true;
        return true;
    }

    /**
     *
     * @return El metodo contarAsientosVipLibres devuelto un numero entero de los asientos vips libres
     *
     */
    public int contarAsientosVipLibres(){
        int contador = 0;
        for (int i = 0; i < asientosVip.length; i++){
            if(asientosVip[i] == false){
                contador++;
            }
        }
        return contador;
    }


    @Override
    public String toString() {
        return "Datos del Recinto\n" +
                "Nombre: " + nombre + "\n" +
                "Direccion: " + direccion + "\n" +
                "AforoMaximo: " + aforoMaximo + "\n" +
                "AsientosVip" + Arrays.toString(asientosVip);
    }

    @Override
    public String aXML() {
        return "<nombre>" + this.nombre + "</nombre>" +
                "<direccion>" + this.direccion + "</direccion>" +
                "<aforomaximo>" + this.aforoMaximo + "</aforomaximo>" +
                "<asientosvip>" + contarAsientosVipLibres() + "</asientosvip>";
    }

    @Override
    public String aCSV() {
        return this.nombre + ";" + this.direccion + ";";
    }

}
