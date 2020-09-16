package com.example.parcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class mainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void wallet(View view) {
        Intent wallet = new Intent(this, walletActivity.class);
        startActivity(wallet);
    }

    public void registerExpense(View view) {
        Intent registerExpense = new Intent(this, registerExpenseActivity.class);
        startActivity(registerExpense);
    }

    public void record(View view) {
        Intent record = new Intent(this, recordActivity.class);
        startActivity(record);
    }
}