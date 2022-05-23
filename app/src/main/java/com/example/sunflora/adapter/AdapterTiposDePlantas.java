package com.example.sunflora.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sunflora.registroDePlantas.ActivityContenedoraRegistroPlantas;
import com.example.sunflora.R;
import com.example.sunflora.model.TipoDePlanta;

import java.util.ArrayList;

public class AdapterTiposDePlantas extends RecyclerView.Adapter<AdapterTiposDePlantas.ViewHolder> {

    LayoutInflater inflater;
    ArrayList<TipoDePlanta> listaTipoDePlanta = new ArrayList<TipoDePlanta>();
    Context context;

    public AdapterTiposDePlantas(Context context, ArrayList<TipoDePlanta> listaTipoDePlanta) {
        this.inflater = LayoutInflater.from(context);
        this.listaTipoDePlanta = listaTipoDePlanta;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterTiposDePlantas.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_descubrir, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTiposDePlantas.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.fotoPlanta.setImageResource(listaTipoDePlanta.get(position).getFoto());
        holder.tipoDePlanta.setText(listaTipoDePlanta.get(position).getTituloTipoDeplanta());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidarOpcion(view, listaTipoDePlanta.get(position).getId());
            }
        });
    }

    private void ValidarOpcion(View view, int id) {
        Intent intent = new Intent(context, ActivityContenedoraRegistroPlantas.class);

        switch (id){
            case 1:
                intent.putExtra("tipoDePlanta", "HojaVerde");
                break;
            case 2:
                intent.putExtra("tipoDePlanta", "PlantasFrutales");
                break;
            case 3:
                intent.putExtra("tipoDePlanta", "TalloLargo");
                break;
        }
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return listaTipoDePlanta.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{

        TextView tipoDePlanta;
        ImageView fotoPlanta;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tipoDePlanta = (TextView) itemView.findViewById(R.id.TituloDescubrir);
            fotoPlanta = (ImageView) itemView.findViewById(R.id.ImageViewDescubrir);
            cardView = (CardView) itemView.findViewById(R.id.CardViewDescubrir);
        }
    }
}
