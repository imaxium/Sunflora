package com.example.sunflora.RoomDatabase.DatabaseYDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.sunflora.RoomDatabase.Entities.ContenidoNotificaciones;
import com.example.sunflora.RoomDatabase.Entities.PlantaRoom;
import com.example.sunflora.RoomDatabase.Entities.Recordatorio;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DAOPlantas {

    //si hubiese algun conflicto entre las claves primarias al final se remplazaria el recordatorio a insertar por el antiguo.


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarPlanta(PlantaRoom plantaRoom);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarRecordatorio(Recordatorio recordatorio);

    @Delete
    void EliminarPlanta(PlantaRoom plantaRoom);

    @Transaction
    @Query("SELECT * FROM Planta")
    List<PlantaRoom> recuperarPlantas();

    @Query("SELECT * FROM Recordatorio WHERE idPlantaRef = :idPlantaRef")
    List<Recordatorio> recuperarRecordatorios(String idPlantaRef);

    @Delete
    void eliminarRecordatorio(Recordatorio recordatorio);

    @Transaction
    @Query("DELETE FROM Recordatorio WHERE idPlantaRef = :idPlantaRef")
    void eliminarTodosLosRecordatoriosDeUnaPlanta(String idPlantaRef);

    @Query("SELECT * FROM ContenidoNotificaciones WHERE idRecordatorioRef = :idRecordatorio")
    ContenidoNotificaciones recuperarNotificacion(String idRecordatorio);

    @Transaction
    @Update
    void actualizarNotificaciones(ArrayList<ContenidoNotificaciones> contenidoNotificacion);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarNotificacion(ContenidoNotificaciones contenidoNotificaciones);

    @Query("SELECT * FROM ContenidoNotificaciones WHERE idPlantaRef = :idPlantaRef")
    List<ContenidoNotificaciones> recuperarNotificacionesPorPlanta(String idPlantaRef);

    @Query("SELECT * FROM ContenidoNotificaciones")
    List<ContenidoNotificaciones> recuperarTodasLasNotificaciones();
}
