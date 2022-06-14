package com.example.sunflora.RoomDatabase.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.sunflora.RoomDatabase.typeConverter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

@Entity(foreignKeys = @ForeignKey(entity = PlantaRoom.class, parentColumns = "idPlanta", childColumns = "idPlantaRef"))
public class Recordatorio implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = false)
    String idRecordatorio;
    String nombreRecordatorio;
    int horaRecordatorio;
    int minRecordatorio;
    String idPlantaRef;

    public Recordatorio() {}

    public String getIdRecordatorio() {
        return idRecordatorio;
    }

    public void setIdRecordatorio(String idRecordatorio) {
        this.idRecordatorio = idRecordatorio;
    }

    public String getNombreRecordatorio() {
        return nombreRecordatorio;
    }

    public void setNombreRecordatorio(String nombreRecordatorio) { this.nombreRecordatorio = nombreRecordatorio; }

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

    public void generarUUID(){
        idRecordatorio = UUID.randomUUID().toString();
    }
}
