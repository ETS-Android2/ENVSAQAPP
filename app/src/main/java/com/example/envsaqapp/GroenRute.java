package com.example.envsaqapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class GroenRute extends AppCompatActivity {
    private static double userX;
    private static double userY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groen_rute);

        Intent intent = getIntent();
        userX = intent.getDoubleExtra("userX", userX);
        userY = intent.getDoubleExtra("userY", userY);


    }
}