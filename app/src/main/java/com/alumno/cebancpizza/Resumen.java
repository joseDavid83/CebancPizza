package com.alumno.cebancpizza;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Resumen extends AppCompatActivity {
    private TextView nombre2,dire2,telef2,pizzas;
    ArrayList<String> arrayPizzasResumen = new ArrayList<String>();
    private String acumulapedido="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resumen);

        nombre2=(TextView)findViewById(R.id.lblNombre);
        dire2=(TextView)findViewById(R.id.lblDireccion);
        telef2=(TextView)findViewById(R.id.lblTelefono);
        pizzas=(TextView)findViewById(R.id.lblPizzas);

        nombre2.setText("Nombre: "+getIntent().getStringExtra("nombre"));
        dire2.setText("Dirección: " + getIntent().getStringExtra("direccion"));
        telef2.setText("Teléfono: "+getIntent().getStringExtra("telefono"));

        arrayPizzasResumen=getIntent().getStringArrayListExtra("ap");

        for(int i=0;i<=arrayPizzasResumen.size()-1;i++){
            acumulapedido=acumulapedido + arrayPizzasResumen.get(i)+"\n";
        }


        pizzas.setText(acumulapedido);
    }

}
