package com.example.sunflora.RoomDatabase.Entities;

import androidx.room.Embedded;

import java.util.ArrayList;

public class PlantaYRecordatorios {

    @Embedded public PlantaRoom plantaRoom;
    public ArrayList<Recordatorio> listaRecordatorios;
}
