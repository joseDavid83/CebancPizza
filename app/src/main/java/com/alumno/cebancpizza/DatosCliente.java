package com.alumno.cebancpizza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DatosCliente extends AppCompatActivity{
    private Button sig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_cliente);

        sig=(Button)findViewById(R.id.btnSiguiente);

        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarDatosPedido();
            }
        });

    }

    public void lanzarDatosPedido(){
        Intent i=new Intent(this, DatosPedido.class);
        startActivity(i);
    }
    public void crearCliente(){

    }

}
