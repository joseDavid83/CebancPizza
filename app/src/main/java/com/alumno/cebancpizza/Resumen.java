package com.alumno.cebancpizza;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Resumen extends AppCompatActivity {
    private TextView nombre2,dire2,telef2,pizzas,bebidas,preciototal;
    ArrayList<String> arrayPizzasResumen = new ArrayList<String>();
    ArrayList<String> arrayBebidasResumen = new ArrayList<String>();
    private String acumulapizzas="",acumulabebidas="";
    private int totalprecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resumen);

        nombre2=(TextView)findViewById(R.id.lblNombre);
        dire2=(TextView)findViewById(R.id.lblDireccion);
        telef2=(TextView)findViewById(R.id.lblTelefono);
        pizzas=(TextView)findViewById(R.id.lblPizzas);
        bebidas=(TextView)findViewById(R.id.lblBebidas);
        preciototal=(TextView)findViewById(R.id.lblTotalPrecio);

        nombre2.setText("Nombre: "+getIntent().getStringExtra("nombre"));
        dire2.setText("Dirección: " + getIntent().getStringExtra("direccion"));
        telef2.setText("Teléfono: "+getIntent().getStringExtra("telefono"));

        arrayPizzasResumen=getIntent().getStringArrayListExtra("ap");
        arrayBebidasResumen=getIntent().getStringArrayListExtra("ab");

        totalprecio=Integer.parseInt(getIntent().getStringExtra("acumprecios"));

        preciototal.setText(totalprecio);

        for(int i=0;i<=arrayPizzasResumen.size()-1;i++){
            acumulapizzas=acumulapizzas + arrayPizzasResumen.get(i)+"\n";
        }
        pizzas.setText(acumulapizzas);

        for(int i=0;i<=arrayBebidasResumen.size()-1;i++){
            acumulabebidas=acumulabebidas + arrayBebidasResumen.get(i)+"\n";
        }
        bebidas.setText(acumulabebidas);

    }

}
