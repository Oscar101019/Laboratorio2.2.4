package com.example.oscar.creditosacademicos.entidades;

/**
 * Created by oscar on 14/03/18.
 */

public class Spinner {
    int id;
    String nombre;

    public Spinner(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Spinner() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
