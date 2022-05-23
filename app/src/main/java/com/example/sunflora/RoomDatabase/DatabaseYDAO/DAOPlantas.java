package com.example.sunflora.RoomDatabase.DatabaseYDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.sunflora.RoomDatabase.Entities.PlantaRoom;
import com.example.sunflora.RoomDatabase.Entities.PlantaYRecordatorios;
import com.example.sunflora.RoomDatabase.Entities.Recordatorios;

import java.util.List;

@Dao
public interface DAOPlantas {

    @Insert
    void insertarPlanta(PlantaRoom plantaRoom);

    @Insert
    void insertarRecordatorios(Recordatorios recordatorio);

    @Transaction
    @Query("SELECT * FROM Planta WHERE idPlanta = :idPlanta")
    List<PlantaYRecordatorios> recuperarPlantaYRecordatorios(int idPlanta);

    @Delete
    void EliminarPlanta(PlantaRoom plantaRoom);

    @Delete
    void EliminarRecordatorio(Recordatorios recordatorios);


}