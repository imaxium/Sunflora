package com.example.sunflora.RoomDatabase.DatabaseYDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.sunflora.RoomDatabase.Entities.PlantaRoom;
import com.example.sunflora.RoomDatabase.Entities.Recordatorio;

import java.util.List;

@Dao
public interface DAOPlantas {

    //si hubiese algun conflicto entre las claves primarias al final se remplazaria el recordatorio a insertar por el antiguo.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarPlanta(PlantaRoom plantaRoom);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarRecordatorio(Recordatorio recordatorio);

    @Transaction
    @Query("SELECT * FROM Planta")
    List<PlantaRoom> recuperarPlantas();

    @Query("SELECT * FROM Recordatorio WHERE idPlantaRef = :idPlantaRef")
    List<Recordatorio> recuperarRecordatorios(String idPlantaRef);

    // a parte de la planta, elimina todos los recordatorios asociados a su UUID
    @Delete
    void EliminarPlanta(PlantaRoom plantaRoom);

    @Delete
    void eliminarRecordatorio(Recordatorio recordatorio);

}
