package com.azahartech.eventdev.modelo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDate;

/**
 * Clase Partido
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Partido extends Evento {
    private String equipoLocal;
    private String equipoVisitante;
    private double costeSeguridad;
    private double costeArbitraje;
    private String resultadoMarcador;

    public Partido() {}

    public Partido(String nombre, LocalDate fecha, Recinto recinto, double precio, String equipoLocal, String equipoVisitante, double costeSeguridad) {
        super(nombre, fecha, recinto, precio,TipoEvento.DEPORTE);
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.costeSeguridad = costeSeguridad;
    }

    /**
     * Cosultar el equipo local
     * @return
     */
    public String consultarEquipoLocal() { return equipoLocal; }

    /**
     * Consultar el equipo visitante
     * @return
     */
    public String consultarEquipoVisitante() { return equipoVisitante; }

    /**
     * Calucar el coste operativo
     * @return
     */
    @Override
    public double calcularCosteOperativo() {
        return 2000 + costeSeguridad;
    }

    /**
     * Mostrar la informacion del partido
     */
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.printf("Partido: %s vs %s%n", equipoLocal, equipoVisitante);
        if(resultadoMarcador != null) System.out.println("Resultado: " + resultadoMarcador);
    }

    /**
     * Guardar el resultado del partido
     * @param resultado
     */
    public void setResultadoMarcador(String resultado) { this.resultadoMarcador = resultado; }

    /**
     * Crear un archivo XML de la clase partido
     * @return
     */
    @Override
    public String aXML() {
        return "<partido><local>" + equipoLocal + "</local><visitante>" + equipoVisitante + "</visitante></partido>";
    }

    /**
     * Crear un archivo CSV de la clase partido
     * @return
     */
    @Override
    public String aCSV() {
        return super.aCSV() + ";" + equipoLocal + ";" + equipoVisitante;
    }
}
