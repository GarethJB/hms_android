package com.example.androidhms.reception.search;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ItemReceptionMedicalRecordBinding;
import com.example.androidhms.reception.vo.MedicalRecordVO;

import java.util.ArrayList;

public class SearchMedicalRecordAdapter extends RecyclerView.Adapter<SearchMedicalRecordAdapter.ViewHolder> {
    LayoutInflater inflater;
    MedicalRecordVO vo;
    ArrayList<MedicalRecordVO> recordList;

    public SearchMedicalRecordAdapter(LayoutInflater inflater, MedicalRecordVO vo, ArrayList<MedicalRecordVO> recordList) {
        this.inflater = inflater;
        this.vo = vo;
        this.recordList = recordList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_reception_medical_record,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
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
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
            h.bind.date.setText(recordList.get(i).getRecord_date());
            h.bind.treamentName.setText(recordList.get(i).getTreatment_name());
            h.bind.department.setText(recordList.get(i).getDepartment_name());
            h.bind.doctor.setText(recordList.get(i).getDoctor());
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            ItemReceptionMedicalRecordBinding bind;
        public ViewHolder(@NonNull View v) {
            super(v);
            bind =ItemReceptionMedicalRecordBinding.bind(v);
        }
    }
}
