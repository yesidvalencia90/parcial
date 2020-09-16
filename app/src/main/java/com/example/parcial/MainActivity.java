package com.example.parcial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void register(View view) {
        Intent registerActivity = new Intent(this, registerActivity.class);
        startActivity(registerActivity);
    }

    public void login(View view) {
        Intent login = new Intent(this, loginActivity.class);
        startActivity(login);
    }
}