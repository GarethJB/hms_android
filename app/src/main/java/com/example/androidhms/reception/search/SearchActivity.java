package com.example.androidhms.reception.search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivitySearchBinding;

public class SearchActivity extends AppCompatActivity {

    ActivitySearchBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivitySearchBinding.inflate(getLayoutInflater());
    setContentView(bind.getRoot());

        Intent intent = getIntent();
    }
}