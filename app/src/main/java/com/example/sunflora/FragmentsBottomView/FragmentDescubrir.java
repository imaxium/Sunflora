package com.example.sunflora.FragmentsBottomView;

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
import com.example.sunflora.adapters.AdapterDescubrir;
import com.example.sunflora.model.Descubrir;

import java.util.ArrayList;

public class FragmentDescubrir extends Fragment {

    AdapterDescubrir adapterDescubrir;
    RecyclerView recyclerViewDescubrir;
    public FragmentDescubrir() {
        // Required empty public constructor
    }

    public static FragmentDescubrir newInstance(String param1, String param2) {
        FragmentDescubrir fragment = new FragmentDescubrir();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_descubrir, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewDescubrir = (RecyclerView) view.findViewById(R.id.RecyclerViewDescubrir);
        //Navigation.findNavController(view).navigate(R.id.fragmentInfoPlanta);
        //Navigation.findNavController(v).popBackStack();

        ArrayList<Descubrir> descubrirArrayList = new ArrayList<Descubrir>();
        descubrirArrayList.add(new Descubrir(1, R.drawable.floristeria, "Descubre plantas a tu alrededor", "descubre planas a tu alrededor"));
        descubrirArrayList.add(new Descubrir(2, R.drawable.microcarpa, "Mira nuestro registro de plantas", "Mira nuestro registro de plantas"));
        descubrirArrayList.add(new Descubrir(3, R.drawable.googlelens, "Reconoce las plantas de tu casa", "descubre planas a tu alrededor"));

        recyclerViewDescubrir.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterDescubrir = new AdapterDescubrir(getContext(), descubrirArrayList);
        recyclerViewDescubrir.setAdapter(adapterDescubrir);

    }
}