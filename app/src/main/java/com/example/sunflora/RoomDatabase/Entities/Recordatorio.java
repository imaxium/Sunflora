package com.example.sunflora.RoomDatabase.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.sunflora.RoomDatabase.typeConverter;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(foreignKeys = @ForeignKey(entity = PlantaRoom.class, parentColumns = "idPlanta", childColumns = "idPlantaRef"))
public class Recordatorio implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int idRecordatorio;
    String nombreRecordatorio;
    int ciclo;
    int horaRecordatorio;
    int minRecordatorio;
    String idPlantaRef;

    @TypeConverters(typeConverter.class)
    ArrayList<String> diasParaRecordar;

    public Recordatorio() {}

    public int getIdRecordatorio() {
        return idRecordatorio;
    }

    public void setIdRecordatorio(int idRecordatorio) {
        this.idRecordatorio = idRecordatorio;
    }

    public String getNombreRecordatorio() {
        return nombreRecordatorio;
    }

    public void setNombreRecordatorio(String nombreRecordatorio) { this.nombreRecordatorio = nombreRecordatorio; }

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public int getHoraRecordatorio() {
        return horaRecordatorio;
    }

    public void setHoraRecordatorio(int horaRecordatorio) { this.horaRecordatorio = horaRecordatorio; }

    public int getMinRecordatorio() {
        return minRecordatorio;
    }

    public void setMinRecordatorio(int minRecordatorio) {
        this.minRecordatorio = minRecordatorio;
    }

    public void setIdPlantaRef(String idPlantaRef) {
        this.idPlantaRef = idPlantaRef;
    }

    public String getIdPlantaRef() {
        return idPlantaRef;
    }

    public ArrayList<String> getDiasParaRecordar() { return diasParaRecordar; }

    public void setDiasParaRecordar(ArrayList<String> diasParaRecordar) { this.diasParaRecordar = diasParaRecordar; }
}
