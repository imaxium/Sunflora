package com.example.sunflora.FragmentsBottomView;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sunflora.gestionRecordatorios.ActivityContenedoraGestionRecordatorios;
import com.example.sunflora.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragmentJardin extends Fragment {

    FloatingActionButton FAB;
    public FragmentJardin() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jardin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FAB = view.findViewById(R.id.FABAñadirRecordatorio);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ActivityContenedoraGestionRecordatorios.class);
                startActivity(intent);
            }
        });
    }
}