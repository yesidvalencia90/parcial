package com.example.parcial;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static android.widget.Toast.LENGTH_SHORT;

public class registerActivity extends AppCompatActivity {

    private static final String TAG = "FormActivity";
    private EditText name;
    private EditText lastName;
    private EditText cc;
    private EditText phone;
    //private DatabaseHelper mDatabaseHelper;
    private int SELECT_PHOTO = 1;
    private Uri uri;
    private ImageView imageView;
    private EditText selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.name);
        lastName = findViewById(R.id.lastName);
        cc = findViewById(R.id.email);
        phone = findViewById(R.id.password);
        Button selectImage = findViewById(R.id.selectImage);
        imageView = findViewById(R.id.profileImage);
        selectedImage = findViewById(R.id.selectedImage);
        //mDatabaseHelper = new DatabaseHelper(this);

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                startActivityForResult(chooserIntent, SELECT_PHOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PHOTO
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null){
            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
                selectedImage.setText(bitMapToString(bitmap));
                Log.d(TAG, "Image: " + bitmap.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void cancelForm(View view) {
        Intent MainActivity = new Intent(this, MainActivity.class);
        startActivity(MainActivity);
    }

    public void saveForm(View view) {

        String insertName = "";
        String insertLastName = "";
        String insertPhone = "";
        String insertCc = "";
        String insertImage = "";

        if(!name.getText().toString().isEmpty()) {
            insertName = name.getText().toString();
        }else{
            name.setError("Por favor ingresa el Nombre");
        }

        if(!lastName.getText().toString().isEmpty()) {
            insertLastName = lastName.getText().toString();
        }else{
            lastName.setError("Por favor ingresa el Apellido");
        }

        if(!cc.getText().toString().isEmpty() && isEmailValid(cc.getText().toString())) {
            insertCc = cc.getText().toString();
        }else{
            cc.setError("Por favor ingresa un Email valido");
        }

        if(phone.getText().toString().isEmpty()){
            phone.setError("Por favor ingresa una contraseña");
        }else{
            insertPhone = phone.getText().toString();
        }

        if(!selectedImage.getText().toString().isEmpty()){
            insertImage = selectedImage.getText().toString();
        }

        if(insertName != "" && insertLastName != "" && insertPhone != "" && insertCc != ""){
            /*Boolean integrantExists = validateIdentification(insertCc);
            if(integrantExists == true){
                toastMessage("Ya existe un Integrante con esta identificacion, por favor verifica los datos.");
                cc.setText("");
                cc.requestFocus();
            }else {
                addData(insertName, insertLastName, insertCc, insertPhone, insertImage);
                name.setText("");
                lastName.setText("");
                cc.setText("");
                phone.setText("");
                name.requestFocus();
                imageView.setImageResource(0);
                selectedImage.setText("");
            }*/
            toastMessage("Esta funcion aun no esta disponible!");
        }/*else{
            toastMessage("Algo salio mal, por favor intentalo de nuevo.");
        }*/
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, LENGTH_SHORT).show();
    }

    public String bitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}