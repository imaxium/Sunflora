package com.example.sunflora.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sunflora.R;
import com.example.sunflora.RoomDatabase.ConversorDeDatos;
import com.example.sunflora.RoomDatabase.Entities.PlantaRoom;
import com.example.sunflora.RoomDatabase.Entities.PlantaYRecordatorios;
import com.example.sunflora.gestionRecordatorios.ActivityContenedoraGestionRecordatorios;

import java.util.ArrayList;

public class AdapterListaDePlantasYRecordatorios extends RecyclerView.Adapter<AdapterListaDePlantasYRecordatorios.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<PlantaRoom> listaPlantasYRecordatorios;
    Context context;
    public AdapterListaDePlantasYRecordatorios(Context context, ArrayList<PlantaRoom> listaPlantasYRecordatorios){
        this.context = context;
        this.listaPlantasYRecordatorios = listaPlantasYRecordatorios;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AdapterListaDePlantasYRecordatorios.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_recordatorio_jardin, parent, false);
        return new AdapterListaDePlantasYRecordatorios.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nombrePlanta.setText(listaPlantasYRecordatorios.get(position).getNombre());
        holder.fotoPlanta.setImageBitmap(ConversorDeDatos.convertirByteArrayABitmap(listaPlantasYRecordatorios.get(position).getFotoFlor()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityContenedoraGestionRecordatorios.class);
                intent.putExtra("clasePlanta", (Parcelable) listaPlantasYRecordatorios.get(position));
                intent.putExtra("opcion", "info planta");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPlantasYRecordatorios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nombrePlanta;
        ImageView fotoPlanta;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombrePlanta = (TextView) itemView.findViewById(R.id.TextviewNombrePlantaJardin);
            fotoPlanta = (ImageView) itemView.findViewById(R.id.ImageViewPlantaJardin);
            cardView =(CardView) itemView.findViewById(R.id.CardViewPlantaYRecordatorioJardin);
        }
    }
}
