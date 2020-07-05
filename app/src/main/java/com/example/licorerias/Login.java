package com.example.licorerias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends AppCompatActivity implements View.OnClickListener{

    Button btnLogin, btnRegPersona;
    EditText txtPassword, txtUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsuario=(EditText)findViewById(R.id.txtUsuario);
        txtPassword=(EditText)findViewById(R.id.txtPassword);
        btnLogin=(Button)findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Thread tr = new Thread(){
            @Override
            public void run() {
                final String resultado=LoginEnviar(txtUsuario.getText().toString(), txtPassword.getText().toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r=loginDatos(resultado);
                        if(r>0){
                            Intent i=new Intent(getApplicationContext(),IngresoPersonas.class);
                            i.putExtra("cod",txtUsuario.getText().toString());
                            startActivity(i);
                        }else{
                            Toast.makeText(getApplicationContext(),"Usuario o Password Incorrectos: "+txtUsuario.getText().toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        };
        tr.start();
    }

    public String LoginEnviar(String usu, String pass){
        URL url = null;
        String linea= "";
        int respuesta=0;

        StringBuilder resul = new StringBuilder();
        try {
            url=new URL("https://192.168.1.12/ServiciosWeb/login.php?usuario="+usu+"&pass="+pass);
            HttpURLConnection connection =(HttpURLConnection)url.openConnection();
            respuesta=connection.getResponseCode();

            if(respuesta==HttpURLConnection.HTTP_OK){
                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                while((linea=reader.readLine())!=null){
                    resul.append(linea);
                }
            }
        }catch (Exception e){}

        return resul.toString();
    }

    public int loginDatos(String response){
        int res=0;
        try{
            JSONArray json = new JSONArray(response);
            if(json.length()>0){
                res=1;
            }
        }catch (Exception e){}
        return res;
    }

    public void Registrar(View v){
        Intent Siguiente= new Intent(this, IngresoPersonas.class);
        startActivity(Siguiente);
    }
}
