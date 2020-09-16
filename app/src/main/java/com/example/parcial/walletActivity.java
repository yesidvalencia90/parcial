package com.example.parcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class walletActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
    }

    public void back(View view) {
        Intent back = new Intent(this, mainMenuActivity.class);
        startActivity(back);
    }

    public void addMoney(View view) {
        toastMessage("Esta funcion aun no esta disponible!");
    }

    private void toastMessage(String message){
        Toast.makeText(getBaseContext(), message, LENGTH_SHORT).show();
    }
}