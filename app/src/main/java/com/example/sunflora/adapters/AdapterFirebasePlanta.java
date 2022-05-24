package com.example.sunflora.adapters;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.sunflora.registroDePlantas.FragmentDescripcionPlanta;
import com.example.sunflora.R;
import com.example.sunflora.model.Planta;
import com.example.sunflora.obtenerFoto;
import com.example.sunflora.viewholder.ViewHolderPlanta;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AdapterFirebasePlanta extends BaseAdapterFirebase<Planta, ViewHolderPlanta> {

    //clase para obtener una foto de firebase
    obtenerFoto of;

    public AdapterFirebasePlanta(@NonNull FirebaseRecyclerOptions<Planta> options) {
        super(options);
        of = new obtenerFoto();
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolderPlanta holder, int position, @NonNull Planta planta) {
        holder.getNombre().setText(planta.getNombre());
        holder.getRegado().setText(planta.getRiego());

        Glide.with(holder.getFoto().getContext())
                .load(planta.getFoto())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(R.drawable.microcarpa)
                .into(holder.getFoto());

        holder.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentDescripcionPlanta fragmentDescripcionPlanta = new FragmentDescripcionPlanta();
                Bundle bundle = new Bundle();
                bundle.putSerializable("planta", planta);
                fragmentDescripcionPlanta.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.ActivityContenedoraRegistroPlantas, fragmentDescripcionPlanta).addToBackStack(null).commit();

            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.row_planta;
    }

    @Override
    protected ViewHolderPlanta createViewHolder(View view) {
        return new ViewHolderPlanta(view);
    }
}
