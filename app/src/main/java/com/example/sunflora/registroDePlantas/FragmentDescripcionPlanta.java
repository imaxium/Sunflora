package com.example.sunflora.registroDePlantas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sunflora.R;
import com.example.sunflora.databinding.FragmentAnyadirPlantaBinding;
import com.example.sunflora.databinding.FragmentInfoPlantaBinding;
import com.example.sunflora.model.Planta;

public class FragmentDescripcionPlanta extends Fragment {

    Planta planta;
    FragmentInfoPlantaBinding binding;

    public FragmentDescripcionPlanta() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            planta = (Planta) getArguments().getSerializable("planta");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInfoPlantaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.NombreComunPlanta.setText(planta.getNombre());
        binding.TextViewNombreCientifico.setText(planta.getNombreC());
        binding.TextViewTemperatura.setText(planta.getTemperatura());
        binding.textViewRegado.setText(planta.getRiego());
        binding.textViewDescripcionPlanta.setText(planta.getDescripcion());
        establecerFotoPlanta(view);
    }

    private void establecerFotoPlanta(View view) {

        Glide.with(view.getContext())
                .load(planta.getFoto())
                .placeholder(R.drawable.leaff)
                .error(R.drawable.leaff)
                .into(binding.ImageViewFotoPlanta);
    }
}