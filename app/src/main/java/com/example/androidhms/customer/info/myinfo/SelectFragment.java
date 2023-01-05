package com.example.androidhms.customer.info.myinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.androidhms.customer.vo.AccountVO;
import com.example.androidhms.databinding.FragmentCustomerMyinfoSelectBinding;
import com.example.androidhms.staff.vo.PatientVO;

public class SelectFragment extends Fragment {
    private FragmentCustomerMyinfoSelectBinding bind;
    private PatientVO patient;
    private AccountVO account;

    public SelectFragment(PatientVO patient, AccountVO account) {
        this.patient = patient;
        this.account = account;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentCustomerMyinfoSelectBinding.inflate(inflater, container, false);

        bind.tvName.setText(patient.getName());
        bind.tvGender.setText(patient.getGender());
        bind.tvEmail.setText(account.getEmail());
        bind.tvPhone.setText(patient.getPhone_number());
        bind.tvDate.setText(account.getDate());


        return bind.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }
}