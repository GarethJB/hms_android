package com.example.androidhms.customer.join;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.androidhms.R;
import com.example.androidhms.customer.common.FragmentControl;
import com.example.androidhms.databinding.ActivityCustomerPatientRegisterBinding;

public class PatientRegisterActivity extends AppCompatActivity {
    private ActivityCustomerPatientRegisterBinding bind;
    private FragmentControl control;
    private PatientRegisterFragment patientRegister;
    private AdditionalFragment additional;
    int social_id;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityCustomerPatientRegisterBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        control = new FragmentControl(this);
        patientRegister = new PatientRegisterFragment();
        additional = new AdditionalFragment();


        change(1);

    }

    public void change(int test){
        switch (test) {
            case 1:
                control.addFragment(R.id.register_container, patientRegister);
                control.showFragment(patientRegister);
                break;
            case 2:
                control.addFragment(R.id.register_container, additional);
                control.hideFragment(patientRegister);
                control.showFragment(additional);
        }

    }

    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }

}