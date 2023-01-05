package com.example.androidhms.customer.info.timetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ItemCustomerStaffListBinding;
import com.example.androidhms.staff.vo.StaffVO;

import java.util.ArrayList;

public class StaffListAdapter extends RecyclerView.Adapter<StaffListAdapter.ViewHolder> {
    LayoutInflater inflater;
    Context context;
    ArrayList<StaffVO> staff;

    public StaffListAdapter(LayoutInflater inflater, Context context, ArrayList<StaffVO> staff) {
        this.inflater = inflater;
        this.context = context;
        this.staff = staff;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_customer_staff_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {


    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return staff.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ItemCustomerStaffListBinding bind;

        public ViewHolder(@NonNull View v) {
            super(v);
            bind = ItemCustomerStaffListBinding.bind(v);
        }
    }

}
