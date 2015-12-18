package com.alumno.cebancpizza;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by adminportatil on 10/12/2015.
 */
public class Resumen extends AppCompatActivity {
    private TextView nombre2,dire2,telef2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resumen);

        nombre2=(TextView)findViewById(R.id.lblNombre);
        dire2=(TextView)findViewById(R.id.lblDireccion);
        telef2=(TextView)findViewById(R.id.lblTelefono);

        nombre2.setText(getIntent().getStringExtra("nombre"));
        dire2.setText(getIntent().getStringExtra("direccion"));
        telef2.setText(getIntent().getStringExtra("telefono"));
    }
}
