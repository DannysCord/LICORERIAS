package com.example.licorerias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void Registrar(View v){
        Intent Siguiente= new Intent(this, IngresoPersonas.class);
        startActivity(Siguiente);
    }

    public void Login(View v){

    }
}
