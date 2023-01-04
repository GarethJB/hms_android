package com.example.androidhms.appointment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.databinding.FragmentAppointBinding;

import java.util.Calendar;


public class AppointFragment extends Fragment {

    FragmentAppointBinding bind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentAppointBinding.inflate(inflater,container,false);

        bind.recvAppointmentList.setAdapter(new AppointmentAdapter(inflater));
        bind.recvAppointmentList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));


        return bind.getRoot();    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }


}//oncreate