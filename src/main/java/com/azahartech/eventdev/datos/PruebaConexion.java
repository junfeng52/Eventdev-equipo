package com.azahartech.eventdev.datos;

import com.azahartech.eventdev.modelo.Concierto;
import com.azahartech.eventdev.modelo.Evento;
import com.azahartech.eventdev.modelo.Recinto;
import com.azahartech.eventdev.modelo.TipoEvento;
import com.azahartech.eventdev.servicio.ServicioEvento;

import java.time.LocalDate;

public class PruebaConexion {
    public static void main(String[] args) {

        ServicioEvento servicioEvento = new ServicioEvento();
        EventoRepositorio eventoRepositorio = new EventoRepositorio();

//        servicioEvento.registrarEvento(new Concierto("Concierto Evento 1", LocalDate.of(2021, 12, 31), new Recinto("recinto1", "direccion1", 100), 100, TipoEvento.CONCIERTO, "Banda1", 2000,"Cancion1, Cancion2, Cancion23"));
//        servicioEvento.registrarEvento(new Concierto("Concierto Evento 3", LocalDate.of(2033, 12, 31), new Recinto("recinto3", "direccion3", 120), 120, TipoEvento.CONCIERTO, "Banda2", 4000,"Cancion5, Cancion6, Cancion31"));
//        servicioEvento.registrarEvento(new Concierto("Concierto Evento 5", LocalDate.of(2026, 12, 31), new Recinto("recinto5", "direccion5", 140), 140, TipoEvento.CONCIERTO, "Banda3", 20000,"Cancion2, Cancion23, Cancion13"));
//        servicioEvento.registrarEvento(new Concierto("Concierto Evento 7", LocalDate.of(2027, 12, 31), new Recinto("recinto7", "direccion7", 160), 160, TipoEvento.CONCIERTO, "Banda4", 7000,"Cancion6, Cancion23, Cancion35"));
        for (Evento evento : servicioEvento.listarTodosLosEventos()) {
            evento.mostrarInformacion();
        }

        eventoRepositorio.buscarPorId("EVT-2026-COEV3");
        eventoRepositorio.eliminar("EVT-2026-COEV3");
    }
}
