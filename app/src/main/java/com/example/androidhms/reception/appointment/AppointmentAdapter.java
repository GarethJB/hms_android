package com.example.androidhms.reception.appointment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.R;
import com.example.androidhms.reception.vo.MedicalReceiptVO;

import java.util.ArrayList;


public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AViewHolder> {
    LayoutInflater inflater;
    ArrayList<MedicalReceiptVO> list;
    AppointmentActivity activity;

    public AppointmentAdapter(LayoutInflater inflater, ArrayList<MedicalReceiptVO> list, AppointmentActivity activity) {
        this.inflater = inflater;
        this.list = list;
        this.activity = activity;
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
        return list.size();
    }
    public class AViewHolder extends RecyclerView.ViewHolder {

        public AViewHolder(@NonNull View v) {
            super(v);
        }
    }
}
