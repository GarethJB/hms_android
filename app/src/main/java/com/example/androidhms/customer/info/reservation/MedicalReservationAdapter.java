package com.example.androidhms.customer.info.reservation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.R;
import com.example.androidhms.customer.vo.MedicalReceiptVO;
import com.example.androidhms.databinding.ItemCustomerReservationMedicalBinding;

import java.util.ArrayList;

public class MedicalReservationAdapter extends RecyclerView.Adapter<MedicalReservationAdapter.ViewHolder> {
    LayoutInflater inflater;
    Context context;
    private ArrayList<MedicalReceiptVO> receipt = new ArrayList<>();

    public MedicalReservationAdapter(LayoutInflater inflater, Context context, ArrayList<MedicalReceiptVO> receipt) {
        this.inflater = inflater;
        this.context = context;
        this.receipt = receipt;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_customer_reservation_medical, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.bind.tvDepartment.setText(receipt.get(i).getDepartment_name());
        h.bind.tvName.setText(receipt.get(i).getName());
        h.bind.tvDate.setText(receipt.get(i).getTime());
        h.bind.tvLocation.setText(receipt.get(i).getLocation());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return receipt.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemCustomerReservationMedicalBinding bind;

        public ViewHolder(@NonNull View v) {
            super(v);
            bind = ItemCustomerReservationMedicalBinding.bind(v);
        }
    }
}
