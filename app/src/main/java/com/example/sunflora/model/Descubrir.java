package com.example.sunflora.model;

public class Descubrir {

    int id, foto;
    String tituloDescubrir, DescripcionDescubrir;

    public Descubrir(int id, int foto, String tituloDescubrir, String descripcionDescubrir) {
        this.id = id;
        this.foto = foto;
        this.tituloDescubrir = tituloDescubrir;
        DescripcionDescubrir = descripcionDescubrir;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getTituloDescubrir() {
        return tituloDescubrir;
    }

    public void setTituloDescubrir(String tituloDescubrir) {
        this.tituloDescubrir = tituloDescubrir;
    }

    public String getDescripcionDescubrir() {
        return DescripcionDescubrir;
    }

    public void setDescripcionDescubrir(String descripcionDescubrir) {
        DescripcionDescubrir = descripcionDescubrir;
    }
}
