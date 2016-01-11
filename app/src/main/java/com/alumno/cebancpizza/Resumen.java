package com.alumno.cebancpizza;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Resumen extends AppCompatActivity {
    private TextView nombre2,dire2,telef2,pizzas,bebidas,preciototal,peluche,vale;
    ArrayList<String> arrayPizzasResumen = new ArrayList<String>();
    ArrayList<String> arrayBebidasResumen = new ArrayList<String>();
    private String acumulapizzas="",acumulabebidas="";
    private double totalprecio;
    private ImageView imagenpeluche;
    private Button botonAceptar,botonCancelar;

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
        peluche=(TextView)findViewById(R.id.lblPeluche);
        vale=(TextView)findViewById(R.id.lblVale);
        imagenpeluche=(ImageView)findViewById(R.id.imgPeluche);
        botonAceptar=(Button)findViewById(R.id.btnAceptar);
        botonCancelar=(Button)findViewById(R.id.btnCancelar);

        nombre2.setText("Nombre: "+getIntent().getStringExtra("nombre"));
        dire2.setText("Dirección: " + getIntent().getStringExtra("direccion"));
        telef2.setText("Teléfono: "+getIntent().getStringExtra("telefono"));

        arrayPizzasResumen=getIntent().getStringArrayListExtra("ap");
        arrayBebidasResumen=getIntent().getStringArrayListExtra("ab");

        totalprecio=getIntent().getDoubleExtra("acumprecios",totalprecio);

        preciototal.setText("El precio total es: "+totalprecio);

        for(int i=0;i<=arrayPizzasResumen.size()-1;i++){
            acumulapizzas=acumulapizzas + arrayPizzasResumen.get(i)+"\n";
        }
        pizzas.setText(acumulapizzas);

        for(int i=0;i<=arrayBebidasResumen.size()-1;i++){
            acumulabebidas=acumulabebidas + arrayBebidasResumen.get(i)+"\n";
        }
        bebidas.setText(acumulabebidas);

        if (totalprecio>=20) {
            peluche.setText("Has ganado un peluche por realizar un pedido superior a 20€.");
        }
        if(totalprecio>=33) {
            vale.setText("Has ganado un vale para comer en el comedor de Cebanc por realizar un pedido superior a 33€.");
            imagenpeluche.setVisibility(View.VISIBLE);
        }

        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast1 = Toast.makeText(getApplicationContext(), "PEDIDO ENVIADO. Saliendo de la aplicación...", Toast.LENGTH_SHORT);
                toast1.show();
                finish();
            }
        });

        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast1 = Toast.makeText(getApplicationContext(), "PEDIDO CANCELADO. Saliendo de la aplicación...", Toast.LENGTH_SHORT);
                toast1.show();
                finish();
            }
        });
    }

}
