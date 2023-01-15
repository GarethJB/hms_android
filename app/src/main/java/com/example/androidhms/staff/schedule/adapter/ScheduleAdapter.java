package com.example.androidhms.staff.schedule.adapter;

import android.content.res.ColorStateList;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ItemStaffMedicalRecordBinding;
import com.example.androidhms.staff.schedule.ScheduleActivity;
import com.example.androidhms.staff.vo.ScheduleVO;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>{

    private final ScheduleActivity activity;
    private final List<ScheduleVO> scList;
    private final int staff_level;
    private int selectedPosition = -1;

    public ScheduleAdapter(ScheduleActivity activity, List<ScheduleVO> scList, int staff_level) {
        this.activity = activity;
        this.scList = scList;
        this.staff_level = staff_level;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) return new ScheduleViewHolder(activity.getLayoutInflater().inflate(R.layout.item_staff_schedule_doctor, parent, false));
        else return new ScheduleViewHolder(activity.getLayoutInflater().inflate(R.layout.item_staff_schedule_nurse, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        ScheduleVO vo = scList.get(position);
        holder.tv_time.setText(vo.getTime());
        holder.tv_schedule.setText(vo.getContent());
        if (holder.getItemViewType() == 2) {
            holder.cb_complete.setEnabled(vo.isComplete());
        }
        if (selectedPosition == position) {
            holder.view.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(activity, R.color.second_color)));
            setColor(holder, ContextCompat.getColor(activity, R.color.white));
        } else {
            holder.view.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(activity, R.color.white)));
            setColor(holder, ContextCompat.getColor(activity, R.color.text_color));
        }
    }

    @Override
    public int getItemCount() {
        return scList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (staff_level == 1) return 1;
        else return 2;
    }

    private void setColor(ScheduleViewHolder holder, int color) {
        TextView[] tvArr = {holder.tv_time, holder.tv_schedule};
        for (TextView tv : tvArr) {
            tv.setTextColor(color);
        }
    }

    public class ScheduleViewHolder extends RecyclerView.ViewHolder {

        private final CardView view;
        private final TextView tv_time, tv_schedule;
        private final CheckBox cb_complete;

        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_schedule = itemView.findViewById(R.id.tv_schedule);
            cb_complete = itemView.findViewById(R.id.cb_complete);
            itemView.setOnClickListener(v -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                    activity.onScheduleClick(getAdapterPosition());
                    if (selectedPosition == getAdapterPosition()) {
                        selectedPosition = -1;
                    } else selectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }
}
