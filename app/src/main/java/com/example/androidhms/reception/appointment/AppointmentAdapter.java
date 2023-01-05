package com.example.androidhms.reception.appointment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.R;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AViewHolder> {
    LayoutInflater inflater;

    public AppointmentAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public AViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_reception_appointment_list,parent,false);
        AViewHolder viewHolder = new AViewHolder(v);
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onBindViewHolder(@NonNull AViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class AViewHolder extends RecyclerView.ViewHolder {

        public AViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
