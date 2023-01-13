package com.example.androidhms.reception.search.record;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidhms.R;
import com.example.androidhms.databinding.FragmentMonthBinding;

public class MonthFragment extends Fragment {
    FragmentMonthBinding bind;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bind = FragmentMonthBinding.inflate(inflater,container,false);

        return bind.getRoot();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }
}