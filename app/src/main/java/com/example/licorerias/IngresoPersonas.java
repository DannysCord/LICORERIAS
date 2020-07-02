package com.example.licorerias;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

public class IngresoPersonas extends AppCompatActivity {

    private EditText txtnombre, apellido, correo;
    private Button save;
    private AsyncHttpClient cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personas);

        txtnombre=(EditText) findViewById(R.id.txtNombre);
        apellido=(EditText) findViewById(R.id.txtApellido);
        correo=(EditText) findViewById(R.id.txtCorreo);
        save=(Button) findViewById(R.id.btnSave);

        cliente= new AsyncHttpClient();

        //genera el evento
    }

    public void botonGuardar(View v){
                if(txtnombre.getText().toString().isEmpty() || apellido.getText().toString().isEmpty() || correo.getText().toString().isEmpty()){
                    Toast.makeText(IngresoPersonas.this,"Hay Textos Vacíos!!",Toast.LENGTH_SHORT).show();
                }else{
                    Persona p = new Persona();
                    p.setNombre(txtnombre.getText().toString().replaceAll(" ","%20"));
                    p.setApellido(apellido.getText().toString().replaceAll(" ","%20"));
                    p.setCorreo(correo.getText().toString().replaceAll(" ","%20"));
                    agregarPersona(p);
                }
            }

    private void agregarPersona(Persona p){
        Toast.makeText(IngresoPersonas.this,""+p,Toast.LENGTH_SHORT).show();
        String url = "https://licoreriaecuador.000webhostapp.com/agregar.php?";
        String parametro = "Nombre="+p.getNombre()+"&Apellido="+p.getApellido()+"&Correo="+p.getCorreo();
        Toast.makeText(IngresoPersonas.this,""+url+parametro,Toast.LENGTH_SHORT).show();
        cliente.post(url + parametro, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200){
                    Toast.makeText(IngresoPersonas.this, "Persona Ingresada Correctamente!!", Toast.LENGTH_SHORT).show();
                    txtnombre.setText("");
                    apellido.setText("");
                    correo.setText("");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }
}
