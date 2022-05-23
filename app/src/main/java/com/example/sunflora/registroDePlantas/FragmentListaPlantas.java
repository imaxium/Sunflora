package com.example.sunflora.registroDePlantas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sunflora.R;
import com.example.sunflora.adapter.AdapterFirebasePlanta;
import com.example.sunflora.model.Planta;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentListaPlantas extends Fragment {

    AdapterFirebasePlanta adapterFirebasePlanta;
    RecyclerView RecyclerListaPlantas;
    String tipoDePlanta;

    public FragmentListaPlantas() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tipoDePlanta = getArguments().getString("tipoDePlanta");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_plantas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerListaPlantas = view.findViewById(R.id.RecyclerListaPlantas);

        RecyclerListaPlantas.setLayoutManager(new LinearLayoutManager(getContext()));


        FirebaseRecyclerOptions<Planta> options =
                new FirebaseRecyclerOptions.Builder<Planta>()
                        .setQuery (FirebaseDatabase.getInstance("https://sunflora-9b87b-default-rtdb.europe-west1.firebasedatabase.app").getReference().child(tipoDePlanta), Planta.class)
                        .build();

        adapterFirebasePlanta = new AdapterFirebasePlanta(options);

        RecyclerListaPlantas.setAdapter(adapterFirebasePlanta);

    }

    @Override
    public void onStart() {
        super.onStart();
        adapterFirebasePlanta.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterFirebasePlanta.stopListening();
    }


}