package com.example.sunflora.RoomDatabase.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "Planta")
public class PlantaRoom {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    String idPlanta;

    String nombre;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    byte[] fotoFlor;

    String localizacion;
    String tipo;
    String fechaInicioRecordatorio;

    public PlantaRoom(){
        idPlanta = UUID.randomUUID().toString();
    }

    public String getFechaInicioRecordatorio() { return fechaInicioRecordatorio; }

    public void setFechaInicioRecordatorio(String fechaInicioRecordatorio) { this.fechaInicioRecordatorio = fechaInicioRecordatorio; }

    public String getIdPlanta() {
        return idPlanta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getFotoFlor() {
        return fotoFlor;
    }

    public void setFotoFlor(byte[] fotoFlor) {
        this.fotoFlor = fotoFlor;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) { this.tipo = tipo; }

}
