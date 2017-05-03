package com.example.juanjosecarosierra.proyecto_actaplus;

/**
 * Created by juanjosecarosierra on 25/4/17.
 */

public class Arbitro {
    private int  Id_arbitros;
    private String Usuario;
    private String Pass;
    private String Nombre;
    private String Apellidos;
    private String Direccion;
    private String Tlf;
    private String CCC;

    public int getId_arbitros() {
        return Id_arbitros;
    }

    public void setId_arbitros(int id_arbitros) {
        Id_arbitros = id_arbitros;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getTlf() {
        return Tlf;
    }

    public void setTlf(String tlf) {
        Tlf = tlf;
    }

    public String getCCC() {
        return CCC;
    }

    public void setCCC(String CCC) {
        this.CCC = CCC;
    }
}
