package com.example.androidhms.customer.reservation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.androidhms.databinding.FragmentCustomerStepFourBinding;

public class StepFourFragment extends Fragment {
    FragmentCustomerStepFourBinding bind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentCustomerStepFourBinding.inflate(inflater, container, false);


        return bind.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }

}