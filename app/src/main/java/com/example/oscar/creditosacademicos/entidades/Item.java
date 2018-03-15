package com.example.oscar.creditosacademicos.entidades;

/**
 * Created by oscar on 14/03/18.
 */

public class Item {
    private String actividad,fechaIni,fechaFin;
    private int idAl,idAct,creditos;

    public Item() {
    }


    public String getActividad() {
        return actividad;
    }

    public int getCreditos() {
        return creditos;
    }

    public int getIdAct() {
        return idAct;
    }

    public int getIdAl() {
        return idAl;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public void setIdAct(int id) {
        this.idAct = idAct;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setIdAl(int idAl) {
        this.idAl = idAl;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
}
