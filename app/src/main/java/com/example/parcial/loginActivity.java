package com.example.parcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import static android.widget.Toast.LENGTH_SHORT;

public class loginActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
    }

    public void cancel(View view) {
        Intent MainActivity = new Intent(this, MainActivity.class);
        startActivity(MainActivity);
    }

    public void login(View view) {

        String insertUserName = "";
        String insertPassword = "";

        if(!userName.getText().toString().isEmpty() && isEmailValid(userName.getText().toString())) {
            insertUserName = userName.getText().toString();
        }else{
            userName.setError("El usuario no es valido");
        }

        if(password.getText().toString().isEmpty()){
            password.setError("La contraseña no es valida");
        }else{
            insertPassword = password.getText().toString();
        }

        if(insertUserName != "" && insertPassword != ""){
            if (insertUserName.equals("admin@admin.com") && insertPassword.equals("admin")){
                Intent mainMenu = new Intent(this, mainMenuActivity.class);
                startActivity(mainMenu);
            } else {
                toastMessage("Esta funcion aun no esta disponible!");
            }
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, LENGTH_SHORT).show();
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}