package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Detailed extends AppCompatActivity {
TextView detailed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        detailed = findViewById(R.id.detailedTextView);
        detailed.setText(getIntent().getStringExtra("detailedInfo"));
    }
}