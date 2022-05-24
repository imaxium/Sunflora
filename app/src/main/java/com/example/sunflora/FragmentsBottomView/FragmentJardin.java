package com.example.sunflora.FragmentsBottomView;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sunflora.RoomDatabase.DatabaseYDAO.DAOPlantas;
import com.example.sunflora.RoomDatabase.DatabaseYDAO.PlantasDatabase;
import com.example.sunflora.RoomDatabase.Entities.PlantaRoom;
import com.example.sunflora.RoomDatabase.Entities.Recordatorios;
import com.example.sunflora.adapters.AdapterListaDePlantasYRecordatorios;
import com.example.sunflora.adapters.AdapterListaRecordatorios;
import com.example.sunflora.databinding.FragmentAnyadirPlantaBinding;
import com.example.sunflora.databinding.FragmentJardinBinding;
import com.example.sunflora.gestionRecordatorios.ActivityContenedoraGestionRecordatorios;
import com.example.sunflora.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentJardin extends Fragment {

    FragmentJardinBinding binding;
    DAOPlantas daoPlantas;
    public FragmentJardin() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentJardinBinding.inflate(inflater, container, false);
        daoPlantas = PlantasDatabase.getDBInstance(getContext()).daoPlantas();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.FABAAdirRecordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ActivityContenedoraGestionRecordatorios.class);
                startActivity(intent);
            }
        });

        recuperarPlantasYRecordatorios();
    }

    private void recuperarPlantasYRecordatorios() {

        ArrayList<PlantaRoom> listaPlantas = (ArrayList<PlantaRoom>) daoPlantas.recuperarPlantas();
        binding.RecyclerListaDePlantasYRecordatorios.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        AdapterListaDePlantasYRecordatorios adapterListaDePlantasYRecordatorios = new AdapterListaDePlantasYRecordatorios(getContext(), listaPlantas);
        binding.RecyclerListaDePlantasYRecordatorios.setAdapter(adapterListaDePlantasYRecordatorios);

    }

    @Override
    public void onResume() {
        super.onResume();
        recuperarPlantasYRecordatorios();
    }
}