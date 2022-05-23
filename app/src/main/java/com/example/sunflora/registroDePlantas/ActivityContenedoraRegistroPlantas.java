package com.example.sunflora.registroDePlantas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.sunflora.R;

public class ActivityContenedoraRegistroPlantas extends AppCompatActivity {

    FragmentListaPlantas fragmentListaPlantas;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedora_registro_plantas);

        String tipoDePlanta = getIntent().getStringExtra("tipoDePlanta");
        fragmentListaPlantas = new FragmentListaPlantas();

        Bundle bundle = new Bundle();
        bundle.putString("tipoDePlanta", tipoDePlanta);
        fragmentListaPlantas.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.ActivityContenedoraRegistroPlantas, fragmentListaPlantas).commit();

    }


}