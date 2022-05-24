package com.example.sunflora.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.sunflora.model.Descubrir;

import java.util.ArrayList;

public class AdapterDescubrir extends RecyclerView.Adapter<AdapterDescubrir.ViewHolder> {

    LayoutInflater inflater;
    ArrayList<Descubrir> listaDescubrir;
    Context context;

    public AdapterDescubrir(Context context, ArrayList<Descubrir> listaDescubrir) {
        this.inflater = LayoutInflater.from(context);
        this.listaDescubrir = listaDescubrir;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterDescubrir.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_descubrir, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDescubrir.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.fotoDescubrir.setImageResource(listaDescubrir.get(position).getFoto());
        holder.tituloDescubrir.setText(listaDescubrir.get(position).getTituloDescubrir());
        holder.descripcionDescubrir.setText(listaDescubrir.get(position).getDescripcionDescubrir());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidarOpcion(view, listaDescubrir.get(position).getId());
            }
        });
    }

    private void ValidarOpcion(View view, int id) {

        switch (id){
            case 1:
                //en caso de que pulsemos en esta tarjeta nos llevará a googleMaps y buscara floristerias alrededor de nosotros.
                Uri Direccion = Uri.parse("geo:0,0?q=floristerias");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Direccion);
                mapIntent.setPackage("com.google.android.apps.maps");

                context.startActivity(mapIntent);
                break;
            case 2:
                Navigation.findNavController(view).navigate(R.id.fragmentTipoDeplantas);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listaDescubrir.size();
    }

    public static class  ViewHolder extends RecyclerView.ViewHolder{

        TextView tituloDescubrir, descripcionDescubrir;
        ImageView fotoDescubrir;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloDescubrir = (TextView) itemView.findViewById(R.id.TituloDescubrir);
            descripcionDescubrir = (TextView) itemView.findViewById(R.id.descripcionDescubrir);
            fotoDescubrir = (ImageView) itemView.findViewById(R.id.ImageViewDescubrir);
            cardView = (CardView) itemView.findViewById(R.id.CardViewDescubrir);
        }
    }
}
