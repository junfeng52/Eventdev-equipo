package com.azahartech.eventdev.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UtilidadValidacionTest {

    @Test
    void generaraIdAutomatico_DebeDevolverTrue_SiElIdEsValido() {
        assertEquals("EVT-"+ LocalDate.now().getYear()+"-MADRI", UtilidadValidacion.generaraIdAutomatico("Madrid"));
        assertEquals("EVT-"+ LocalDate.now().getYear()+"-MADFE", UtilidadValidacion.generaraIdAutomatico("Madrid Festival"));
        assertEquals("EVT-"+ LocalDate.now().getYear()+"-MAFEM", UtilidadValidacion.generaraIdAutomatico("Madrid Festival Musical"));
        assertEquals("EVT-"+ LocalDate.now().getYear()+"-MAFMP", UtilidadValidacion.generaraIdAutomatico("Madrid Festival Musical POP"));
        assertEquals("EVT-"+ LocalDate.now().getYear()+"-MFMPK", UtilidadValidacion.generaraIdAutomatico("Madrid Festival Musical POP Koreano"));
        assertEquals("EVT-"+ LocalDate.now().getYear()+"-MFMPK", UtilidadValidacion.generaraIdAutomatico("Madrid Festival Musical POP Koreano BTS"));
    }
    @Test
    void generaraIdAutomatico_DebeDevolverTrue_SiElIdEsValidoConUnNombreNulo() {
        assertEquals("EVT-"+ LocalDate.now().getYear()+"-DEFAU", UtilidadValidacion.generaraIdAutomatico(null));
        assertEquals("EVT-"+ LocalDate.now().getYear()+"-DEFAU", UtilidadValidacion.generaraIdAutomatico(""));
        assertEquals("EVT-"+ LocalDate.now().getYear()+"-DEFAU", UtilidadValidacion.generaraIdAutomatico("       "));
    }


}