package com.example.sunflora.model;

import java.io.Serializable;

public class Planta implements Serializable {

    String descripcion, foto, nombre, nombreC, riego, temperatura;

    public Planta(String descripcion, String foto, String nombre, String nombreC, String riego, String temperatura) {
        this.descripcion = descripcion;
        this.foto = foto;
        this.nombre = nombre;
        this.nombreC = nombreC;
        this.riego = riego;
        this.temperatura = temperatura;
    }

    public Planta() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public String getRiego() {
        return riego;
    }

    public void setRiego(String riego) {
        this.riego = riego;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }
}
