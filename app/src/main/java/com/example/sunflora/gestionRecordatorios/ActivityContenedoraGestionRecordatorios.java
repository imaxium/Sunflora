package com.example.sunflora.gestionRecordatorios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.sunflora.R;
import com.example.sunflora.RoomDatabase.Entities.PlantaRoom;

public class ActivityContenedoraGestionRecordatorios extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FragmentAñadirPlanta fragmentAñadirPlanta;
    PlantaRoom plantaRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedora_gestion_recordatorios);
        plantaRoom = new PlantaRoom();
        plantaRoom.generarUUID();

        fragmentAñadirPlanta = new FragmentAñadirPlanta();
        Bundle bundle = new Bundle();
        bundle.putString("idPlanta", plantaRoom.getIdPlanta());
        fragmentAñadirPlanta.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.ActivityContenedoraGestionRecordatorios, fragmentAñadirPlanta).commit();
    }
}