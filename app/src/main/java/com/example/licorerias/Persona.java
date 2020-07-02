package com.example.licorerias;

public class Persona {

    private int codigo;
    private String Nombre;
    private String Apellido;
    private String Correo;

    public Persona() {
    }

    public Persona(int codigo, String nombre, String apellido, String correo) {
        this.codigo = codigo;
        Nombre = nombre;
        Apellido = apellido;
        Correo = correo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    @Override
    public String toString() {
        return "Personas{" +
                "codigo=" + codigo +
                ", Nombre='" + Nombre + '\'' +
                ", Apellido='" + Apellido + '\'' +
                ", Correo='" + Correo + '\'' +
                '}';
    }
}
