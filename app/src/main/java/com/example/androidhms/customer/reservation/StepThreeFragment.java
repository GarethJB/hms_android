package com.example.androidhms.customer.reservation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.databinding.FragmentCustomerStepThreeBinding;


public class StepThreeFragment extends Fragment {
    FragmentCustomerStepThreeBinding bind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bind = FragmentCustomerStepThreeBinding.inflate(inflater, container, false);

        StepThreeAdapter adapter = new StepThreeAdapter(inflater, getContext());

        bind.rcvDate.setAdapter(adapter);
        bind.rcvDate.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        return bind.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }

}