package com.example.sunflora.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public abstract class BaseAdapterFirebase<T, VH extends RecyclerView.ViewHolder> extends FirebaseRecyclerAdapter<T, VH> {

    public BaseAdapterFirebase(FirebaseRecyclerOptions<T> options) {
        super(options);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayout(), parent, false);
        return createViewHolder(view);
    }

    protected abstract int getLayout();

    protected abstract VH createViewHolder(View view);
}
