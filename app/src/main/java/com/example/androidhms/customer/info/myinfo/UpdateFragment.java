package com.example.androidhms.customer.info.myinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.androidhms.customer.vo.AccountVO;
import com.example.androidhms.databinding.FragmentCustomerMyinfoUpdateBinding;
import com.example.androidhms.staff.vo.PatientVO;

public class UpdateFragment extends Fragment {
    private FragmentCustomerMyinfoUpdateBinding bind;
    private PatientVO patient;
    private AccountVO account;

    public UpdateFragment(PatientVO patient, AccountVO account) {
        this.patient = patient;
        this.account = account;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentCustomerMyinfoUpdateBinding.inflate(inflater, container, false);

        bind.tvName.setText(patient.getName());
        bind.tvGender.setHint(patient.getGender());
        bind.tvEmail.setHint(account.getEmail());
        bind.tvPhone.setHint(patient.getPhone_number());


        return bind.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }

}