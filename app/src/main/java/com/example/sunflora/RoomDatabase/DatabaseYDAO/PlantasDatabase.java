package com.example.sunflora.RoomDatabase.DatabaseYDAO;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sunflora.RoomDatabase.Entities.PlantaRoom;
import com.example.sunflora.RoomDatabase.Entities.Recordatorio;

@Database(entities = {PlantaRoom.class, Recordatorio.class}, version = 1)
public abstract class PlantasDatabase extends RoomDatabase {

    private static PlantasDatabase plantasDatabase = null;

    public abstract DAOPlantas daoPlantas();

    public static synchronized PlantasDatabase getDBInstance(Context context){
        if(plantasDatabase == null){
            plantasDatabase = Room.databaseBuilder(
                    context.getApplicationContext(),
                    PlantasDatabase.class,
                    "Plantas"
                    ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return plantasDatabase;
    }


}
