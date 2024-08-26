package com.example.Arevalo_Saibene_Nosicoski.model;

public class Odontologo {
    private int id;
    private String nroMatricula;
    private String apellido;
    private String nombre;

    public Odontologo(int id, String nroMatricula, String apellido, String nombre) {
        this.id = id;
        this.nroMatricula = nroMatricula;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    public Odontologo(String nroMatricula, String apellido, String nombre) {
        this.nroMatricula = nroMatricula;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNroMatricula() {
        return nroMatricula;
    }

    public void setNroMatricula(String nroMatricula) {
        this.nroMatricula = nroMatricula;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Odontologo{" +
                "id=" + id +
                ", nroMatricula='" + nroMatricula + '\'' +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
