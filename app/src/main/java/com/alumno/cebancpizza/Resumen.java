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
    private String cantidad,nombrepizza,tamaño,masa,precio;
    private String textoanterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resumen);

        nombre2=(TextView)findViewById(R.id.lblNombre);
        dire2=(TextView)findViewById(R.id.lblDireccion);
        telef2=(TextView)findViewById(R.id.lblTelefono);
        pizzas=(TextView)findViewById(R.id.lblPizzas);

        nombre2.setText(getIntent().getStringExtra("nombre"));
        dire2.setText(getIntent().getStringExtra("direccion"));
        telef2.setText(getIntent().getStringExtra("telefono"));

        arrayPizzasResumen=getIntent().getStringArrayListExtra("ap");

        for(int i=0;i<=arrayPizzasResumen.size()/5;i=i+5){
            //pizzas.setText(arrayPizzasResumen.get(0)+" "+arrayPizzasResumen.get(1)+" "+arrayPizzasResumen.get(2)+" "+arrayPizzasResumen.get(3)+" "+arrayPizzasResumen.get(4));
            cantidad=arrayPizzasResumen.get(0+i);
            nombrepizza=arrayPizzasResumen.get(1+i);
            tamaño=arrayPizzasResumen.get(2+i);
            masa=arrayPizzasResumen.get(3+i);
            precio=arrayPizzasResumen.get(4+i);
            textoanterior=pizzas.getText().toString();
            pizzas.setText(textoanterior+" "+cantidad+" "+nombrepizza+" "+tamaño+" "+masa+" "+precio);
        }
    }
}
