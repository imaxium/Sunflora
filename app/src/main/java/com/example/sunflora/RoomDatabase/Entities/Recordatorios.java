package com.example.sunflora.RoomDatabase.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Recordatorios {
    @PrimaryKey(autoGenerate = true)
    int idRecordatorio;
    String nombreRecordatorio;
    int ciclo;
    int horaRecordatorio;
    int minRecordatorio;
    String idPlantaRef;

    public int getIdRecordatorio() {
        return idRecordatorio;
    }

    public void setIdRecordatorio(int idRecordatorio) {
        this.idRecordatorio = idRecordatorio;
    }

    public String getNombreRecordatorio() {
        return nombreRecordatorio;
    }

    public void setNombreRecordatorio(String nombreRecordatorio) {
        this.nombreRecordatorio = nombreRecordatorio;
    }

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public int getHoraRecordatorio() {
        return horaRecordatorio;
    }

    public void setHoraRecordatorio(int horaRecordatorio) {
        this.horaRecordatorio = horaRecordatorio;
    }

    public int getMinRecordatorio() {
        return minRecordatorio;
    }

    public void setMinRecordatorio(int minRecordatorio) {
        this.minRecordatorio = minRecordatorio;
    }

    public void setIdPlantaRef(String idPlantaRef) {
        this.idPlantaRef = idPlantaRef;
    }
}
