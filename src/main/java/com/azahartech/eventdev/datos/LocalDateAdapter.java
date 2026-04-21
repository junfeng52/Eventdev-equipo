package com.azahartech.eventdev.datos;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    @Override
    public String marshal(LocalDate localDate) throws Exception {
        return localDate.toString();
    }

    @Override
    public LocalDate unmarshal(String s) throws Exception {
        return LocalDate.parse(s);
    }

}
