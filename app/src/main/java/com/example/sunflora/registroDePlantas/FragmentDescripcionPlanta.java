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
import com.example.sunflora.model.Planta;

public class FragmentDescripcionPlanta extends Fragment {

    Planta planta;
    TextView textViewNombreComun;
    TextView textViewNombreCientifico;
    TextView textViewTemperatura;
    TextView textViewRegado;
    TextView textViewDescripcion;
    ImageView imageViewFotoPlanta;

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
        return inflater.inflate(R.layout.fragment_info_planta, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewNombreComun = view.findViewById(R.id.NombreComunPlanta);
        textViewNombreCientifico = view.findViewById(R.id.TextViewNombreCientifico);
        textViewTemperatura = view.findViewById(R.id.TextViewTemperatura);
        textViewRegado = view.findViewById(R.id.textViewRegado);
        imageViewFotoPlanta = view.findViewById(R.id.ImageViewFotoPlanta);
        textViewDescripcion = view.findViewById(R.id.textView);

        textViewNombreComun.setText(planta.getNombre());
        textViewNombreCientifico.setText(planta.getNombreC());
        textViewTemperatura.setText(planta.getTemperatura());
        textViewRegado.setText(planta.getRiego());
        //textViewDescripcion.setText(planta.getDescripcion());
        establecerFotoPlanta(view);
    }

    private void establecerFotoPlanta(View view) {

        Glide.with(view.getContext())
                .load(planta.getFoto())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(R.drawable.microcarpa)
                .into(imageViewFotoPlanta);

    }


}