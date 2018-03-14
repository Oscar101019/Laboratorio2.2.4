package com.example.oscar.creditosacademicos.entidades;

/**
 * Created by oscar on 7/03/18.
 */

public class Actividad {
    private String actividad,creditos;
    private int id;

    public Actividad() {
    }


    public String getActividad() {
        return actividad;
    }

    public String getCreditos() {
        return creditos;
    }

    public int getId() {
        return id;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public void setId(int id) {
        this.id = id;
    }
}
