package com.example.androidhms.reception.search.record.detailrecord;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidhms.R;
import com.example.androidhms.databinding.FragmentReceptionMonthBinding;

public class MonthFragment extends Fragment {

    FragmentReceptionMonthBinding bind;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    //프래그먼트와 어댑터 붙이는 처리

        bind = FragmentReceptionMonthBinding.inflate(inflater,container,false);

        return  bind.getRoot();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }
}