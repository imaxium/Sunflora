package com.example.sunflora.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sunflora.R;
import com.example.sunflora.RoomDatabase.Entities.Recordatorio;
import com.example.sunflora.gestionRecordatorios.FragmentActualizarRecordatorio;

import java.util.ArrayList;

public class AdapterListaRecordatorios extends RecyclerView.Adapter<AdapterListaRecordatorios.ViewHolder> {

    LayoutInflater inflater;
    ArrayList<Recordatorio> listaRecordatorios = new ArrayList<Recordatorio>();
    Context context;

    public AdapterListaRecordatorios(Context context, ArrayList<Recordatorio> listaRecordatorios){
        this.inflater = LayoutInflater.from(context);
        this.listaRecordatorios = listaRecordatorios;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterListaRecordatorios.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fila_recordatorio, parent, false);
        return new AdapterListaRecordatorios.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListaRecordatorios.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tipoRecordatorio.setText(listaRecordatorios.get(position).getNombreRecordatorio());
        holder.descripcionRecordatorio.setText("Recordar cada: ");
        holder.descripcionRecordatorio.append(String.valueOf(listaRecordatorios.get(position).getDiasParaRecordar().size())+" dias, a las ");
        holder.descripcionRecordatorio.append(String.valueOf(listaRecordatorios.get(position).getHoraRecordatorio()+":"));
        holder.descripcionRecordatorio.append(String.valueOf(listaRecordatorios.get(position).getMinRecordatorio()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentActualizarRecordatorio far = new FragmentActualizarRecordatorio();
                Bundle bundle = new Bundle();
                bundle.putSerializable("claseRecordatorio", listaRecordatorios.get(position));
                far.setArguments(bundle);
                manager.beginTransaction().replace(R.id.ActivityContenedoraGestionRecordatorios, far).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaRecordatorios.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tipoRecordatorio;
        TextView descripcionRecordatorio;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tipoRecordatorio =(TextView) itemView.findViewById(R.id.TipoRecordatorio);
            descripcionRecordatorio =(TextView) itemView.findViewById(R.id.descripcionRecordatorio);
            cardView = (CardView) itemView.findViewById(R.id.CardViewRecordatorio);
        }
    }
}
