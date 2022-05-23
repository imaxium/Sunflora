package com.example.sunflora.model;

public class TipoDePlanta {

    int id, foto;
    String TituloTipoDeplanta;

    public TipoDePlanta(int id, int foto, String tituloTipoDeplanta) {
        this.id = id;
        this.foto = foto;
        TituloTipoDeplanta = tituloTipoDeplanta;
    }

    public TipoDePlanta() {
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

    public String getTituloTipoDeplanta() {
        return TituloTipoDeplanta;
    }

    public void setTituloTipoDeplanta(String tituloTipoDeplanta) {
        TituloTipoDeplanta = tituloTipoDeplanta;
    }
}
