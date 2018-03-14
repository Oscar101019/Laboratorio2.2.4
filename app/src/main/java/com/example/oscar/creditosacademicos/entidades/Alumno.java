package com.example.oscar.creditosacademicos.entidades;

/**
 * Created by oscar on 7/03/18.
 */

public class Alumno {
    private String nombre,noctrl,email,cel,carrera;
    private int id;

    public Alumno() {

    }

    public String getNombre() {
        return nombre;
    }

    public String getNoctrl() {
        return noctrl;
    }

    public String getEmail() {
        return email;
    }

    public String getCel() {
        return cel;
    }

    public String getCarrera() {
        return carrera;
    }

    public int getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNoctrl(String noctrl) {
        this.noctrl = noctrl;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void setId(int id) {
        this.id = id;
    }
}
