package com.example.sunflora.gestionRecordatorios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.example.sunflora.R;
import com.example.sunflora.RoomDatabase.Entities.PlantaRoom;

public class ActivityContenedoraGestionRecordatorios extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FragmentAñadirPlanta fragmentAñadirPlanta;
    FragmentInfoPlantaCreada fragmentInfoPlantaCreada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedora_gestion_recordatorios);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        String opcion = getIntent().getStringExtra("opcion");
        switch (opcion){
            case "añadir planta":
                PlantaRoom plantaRoom = new PlantaRoom();
                plantaRoom.generarUUID();

                fragmentAñadirPlanta = new FragmentAñadirPlanta();
                Bundle bundle = new Bundle();
                bundle.putString("idPlanta", plantaRoom.getIdPlanta());
                fragmentAñadirPlanta.setArguments(bundle);
                fragmentTransaction.replace(R.id.ActivityContenedoraGestionRecordatorios, fragmentAñadirPlanta).commit();
                break;
            case "info planta":
                fragmentInfoPlantaCreada = new FragmentInfoPlantaCreada();
                PlantaRoom plantaInfo = (PlantaRoom) getIntent().getSerializableExtra("clasePlanta");
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("clasePlanta", plantaInfo);
                fragmentInfoPlantaCreada.setArguments(bundle1);
                fragmentTransaction.replace(R.id.ActivityContenedoraGestionRecordatorios, fragmentInfoPlantaCreada).commit();
                break;
            default:
                Toast.makeText(this, "no se ha podido realzar las operaciones", Toast.LENGTH_SHORT).show();
        }




    }
}