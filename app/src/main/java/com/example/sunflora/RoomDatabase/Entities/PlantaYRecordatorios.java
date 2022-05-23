package com.example.sunflora.RoomDatabase.Entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PlantaYRecordatorios {

    @Embedded
    public PlantaRoom plantaRoom;
    @Relation(
        parentColumn ="idPlanta",
        entityColumn = "idPlantaRef"
    )
    public List<Recordatorios> listaRecordatorios;
}
