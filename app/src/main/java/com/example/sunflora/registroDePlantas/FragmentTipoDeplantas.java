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
import com.example.sunflora.adapter.AdapterTiposDePlantas;
import com.example.sunflora.model.TipoDePlanta;

import java.util.ArrayList;

public class FragmentTipoDeplantas extends Fragment {

    RecyclerView recyclerViewTipoDePlantas;
    AdapterTiposDePlantas adapterTiposDePlantas;

    public static FragmentTipoDeplantas newInstance(String param1, String param2) {
        FragmentTipoDeplantas fragment = new FragmentTipoDeplantas();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentTipoDeplantas() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tipo_deplantas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewTipoDePlantas = view.findViewById(R.id.RecyclerTipoDePlantas);

        ArrayList<TipoDePlanta> tipoDePlantaArrayList = new ArrayList<TipoDePlanta>();

        tipoDePlantaArrayList.add(new TipoDePlanta(1, R.drawable.icono_plantas_de_hoja_verde, "Plantas de hoja verde"));
        tipoDePlantaArrayList.add(new TipoDePlanta(2, R.drawable.icono_plantas_frutales, "Plantas frutales"));
        tipoDePlantaArrayList.add(new TipoDePlanta(3, R.drawable.icono_plantas_de_tallo_largo, "Plantas de tallo targo"));

        recyclerViewTipoDePlantas.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapterTiposDePlantas = new AdapterTiposDePlantas(view.getContext(), tipoDePlantaArrayList);
        recyclerViewTipoDePlantas.setAdapter(adapterTiposDePlantas);
    }
}