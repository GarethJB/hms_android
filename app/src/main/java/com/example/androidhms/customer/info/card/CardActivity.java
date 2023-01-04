package com.example.androidhms.customer.info.card;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidhms.databinding.ActivityCustomerCardBinding;

public class CardActivity extends AppCompatActivity {
    private ActivityCustomerCardBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityCustomerCardBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        bind.toolbar.ivLeft.setOnClickListener(v -> {
            onBackPressed();
        });

    }
}