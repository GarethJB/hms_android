package com.example.androidhms.reception.search.prescription;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PrescriptionAdapter extends RecyclerView.Adapter<PrescriptionAdapter.PViewHolder> {
    LayoutInflater inflater;


    @NonNull
    @Override
    public PViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class PViewHolder extends RecyclerView.ViewHolder {


        public PViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
