package com.example.androidhms.reception.search.record.detailrecord;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidhms.R;
import com.example.androidhms.databinding.FragmentAppointListBinding;
import com.example.androidhms.reception.vo.MedicalRecordVO;

import java.util.ArrayList;


public class MainFragment extends Fragment {

    MedicalRecordVO vo;
    ArrayList<MedicalRecordVO> list;
    FragmentAppointListBinding bind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bind =FragmentAppointListBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}