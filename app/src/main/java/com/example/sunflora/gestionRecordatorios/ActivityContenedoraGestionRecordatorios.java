package com.example.sunflora.gestionRecordatorios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.sunflora.R;

public class ActivityContenedoraGestionRecordatorios extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FragmentAñadirPlanta fragmentAñadirPlanta;
    FragmentCrearRecordatorio fragmentCrearRecordatorio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedora_gestion_recordatorios);

        fragmentAñadirPlanta = new FragmentAñadirPlanta();
        fragmentCrearRecordatorio = new FragmentCrearRecordatorio();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.ActivityContenedoraGestionRecordatorios, fragmentAñadirPlanta).commit();
    }
}