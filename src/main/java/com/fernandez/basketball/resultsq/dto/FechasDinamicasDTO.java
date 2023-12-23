package com.fernandez.basketball.resultsq.dto;

import java.util.List;

public class FechasDinamicasDTO {

    private List<String> fechas; // Ajusta el tipo de dato según el formato de tus fechas

    public List<String> getFechas() {
        return fechas;
    }

    public void setFechas(List<String> fechas) {
        this.fechas = fechas;
    }
}

