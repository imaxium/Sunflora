package com.example.sunflora.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sunflora.R;

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

    public ImageView getFoto() {
        return foto;
    }

    public TextView getNombre() {
        return nombre;
    }

    public TextView getRegado() {
        return regado;
    }

    public CardView getCardView(){return cardView; }
}