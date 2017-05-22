package com.example.juanjosecarosierra.proyecto_actaplus.Clases;

/**
 * Created by juanjosecarosierra on 25/4/17.
 */

public class Partido {
    private int Id_partidos;
    private int Id_anios;
    private int Id_ligas;
    private int Id_jornada;
    private int Id_arbitro;
    private String Id_equipo1;
    private String Id_equipo2;
    private int Resultado1;
    private int Resultado2;
    private String Acta;

    public int getId_partidos() {
        return Id_partidos;
    }

    public void setId_partidos(int id_partidos) {
        Id_partidos = id_partidos;
    }

    public int getId_anios() {
        return Id_anios;
    }

    public void setId_anios(int id_anios) {
        Id_anios = id_anios;
    }

    public int getId_ligas() {
        return Id_ligas;
    }

    public void setId_ligas(int id_ligas) {
        Id_ligas = id_ligas;
    }

    public int getId_jornada() {
        return Id_jornada;
    }

    public void setId_jornada(int id_jornada) {
        Id_jornada = id_jornada;
    }

    public int getId_arbitro() {
        return Id_arbitro;
    }

    public void setId_arbitro(int id_arbitro) {
        Id_arbitro = id_arbitro;
    }

    public String getId_equipo1() {
        return Id_equipo1;
    }

    public void setId_equipo1(String id_equipo1) {
        Id_equipo1 = id_equipo1;
    }

    public String getId_equipo2() {
        return Id_equipo2;
    }

    public void setId_equipo2(String id_equipo2) {
        Id_equipo2 = id_equipo2;
    }

    public int getResultado1() {
        return Resultado1;
    }

    public void setResultado1(int resultado1) {
        Resultado1 = resultado1;
    }

    public int getResultado2() {
        return Resultado2;
    }

    public void setResultado2(int resultado2) {
        Resultado2 = resultado2;
    }

    public String getActa() {
        return Acta;
    }

    public void setActa(String acta) {
        Acta = acta;
    }
}
