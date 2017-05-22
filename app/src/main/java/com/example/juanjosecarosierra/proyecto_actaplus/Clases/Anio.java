package com.example.juanjosecarosierra.proyecto_actaplus.Clases;

/**
 * Created by juanjosecarosierra on 25/4/17.
 */

//hola juan carlos!!

public class Anio {

    int juanjo=0;

    private int Id_anios;
    private String Anio;

    public int getId_anios() {
        return Id_anios;
    }

    public void setId_anios(int id_anios) {
        Id_anios = id_anios;
    }

    public String getAnio() {
        return Anio;
    }

    public void setAnio(String anio) {
        Anio = anio;
    }

    @Override
    public String toString() {
        return getAnio();
    }
}
