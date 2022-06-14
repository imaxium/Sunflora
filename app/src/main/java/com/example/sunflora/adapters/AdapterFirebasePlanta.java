package com.example.sunflora.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sunflora.registroDePlantas.FragmentDescripcionPlanta;
import com.example.sunflora.R;
import com.example.sunflora.model.Planta;
import com.example.sunflora.gestionRecordatorios.obtenerFoto;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AdapterFirebasePlanta extends FirebaseRecyclerAdapter<Planta, AdapterFirebasePlanta.ViewHolderPlanta> {

    //clase para obtener una foto de firebase
    obtenerFoto of;

    public AdapterFirebasePlanta(@NonNull FirebaseRecyclerOptions<Planta> options) {
        super(options);
        of = new obtenerFoto();
    }

    @NonNull
    @Override
    public AdapterFirebasePlanta.ViewHolderPlanta onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_planta, parent, false);
        return new ViewHolderPlanta(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolderPlanta holder, int position, @NonNull Planta planta) {
        holder.nombre.setText(planta.getNombre());
        holder.regado.setText(planta.getRiego());

        Glide.with(holder.foto.getContext())
                .load(planta.getFoto())
                .placeholder(R.drawable.leaff)
                .error(R.drawable.leaff)
                .into(holder.foto);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
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


    public class ViewHolderPlanta extends RecyclerView.ViewHolder {

        ImageView foto;
        TextView nombre, regado;
        CardView cardView;

        public ViewHolderPlanta(@NonNull View itemView) {
            super(itemView);

            foto = (ImageView) itemView.findViewById(R.id.img1);
            nombre = (TextView) itemView.findViewById(R.id.nombrePlanta);
            regado = (TextView) itemView.findViewById(R.id.tiemporegado);
            cardView = (CardView) itemView.findViewById(R.id.CardViewPlanta);
        }
    }
}
