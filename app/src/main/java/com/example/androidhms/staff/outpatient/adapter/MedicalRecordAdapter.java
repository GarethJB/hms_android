package com.example.androidhms.staff.outpatient.adapter;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ItemStaffMedicalRecordBinding;
import com.example.androidhms.staff.outpatient.MedicalRecordFragment;
import com.example.androidhms.staff.vo.MedicalRecordVO;

import java.util.ArrayList;

public class MedicalRecordAdapter extends RecyclerView.Adapter<MedicalRecordAdapter.MedicalRecordViewHolder> {

    private MedicalRecordFragment fragment;
    private ArrayList<MedicalRecordVO> mrList;
    private int selectedPosition = -1;

    public MedicalRecordAdapter(MedicalRecordFragment fragment, ArrayList<MedicalRecordVO> mrList) {
        this.fragment = fragment;
        this.mrList = mrList;
    }

    @NonNull
    @Override
    public MedicalRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MedicalRecordViewHolder(fragment.getLayoutInflater()
                .inflate(R.layout.item_staff_medical_record, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalRecordViewHolder holder, int position) {
        MedicalRecordVO vo = mrList.get(position);
        holder.bind.tvDate.setText(vo.getTreatment_date());
        holder.bind.tvPatientName.setText(vo.getPatient_name());
        holder.bind.tvStaffName.setText(vo.getStaff_name());
        holder.bind.tvTreatmentName.setText(vo.getTreatment_name());
        if (position == selectedPosition) {

        } else {

        }
    }

    @Override
    public int getItemCount() {
        return mrList.size();
    }

    public class MedicalRecordViewHolder extends RecyclerView.ViewHolder {

        public ItemStaffMedicalRecordBinding bind;

        public MedicalRecordViewHolder(@NonNull View itemView) {
            super(itemView);
            bind = ItemStaffMedicalRecordBinding.bind(itemView);
            itemView.setOnClickListener(v -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION
                        && mrList.get(getAdapterPosition()).getAdmission().equals("N"))
                    fragment.onMedicalRecordClick(getAdapterPosition());
                    selectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
            });

        }
    }

}
