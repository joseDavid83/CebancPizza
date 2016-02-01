package com.alumno.cebancpizza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Resumen extends AppCompatActivity {
    private TextView nombre2,dire2,telef2,pizzas,bebidas,preciototal,peluche,vale;
    ArrayList<Pizza> arrayPizzasResumen = new ArrayList<Pizza>();
    ArrayList<String> arrayBebidasResumen = new ArrayList<String>();
    private String acumulapizzas="",acumulabebidas="";
    private double totalprecio;
    private ImageView imagenpeluche;
    private Button botonAceptar,botonCancelar;
    Cliente cli;
    DecimalFormat df = new DecimalFormat("0.00");

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

        //variables para recoger los datos mandados
        cli =(Cliente)getIntent().getExtras().getSerializable("cliente");

        nombre2.setText("Nombre: "+ cli.getNombre());
        dire2.setText("Dirección: " + cli.getDireccion());
        telef2.setText("Teléfono: "+ cli.getTelefono());
        arrayPizzasResumen=(ArrayList<Pizza>)getIntent().getExtras().getSerializable("ap");
        arrayBebidasResumen=getIntent().getStringArrayListExtra("ab");
        totalprecio=getIntent().getDoubleExtra("acumprecios",totalprecio);
        String tp=df.format(totalprecio);

        preciototal.setText("El precio total es: "+tp+" €");
        //estructura repetitiva para organizar los datos recibidos de las pizzas
        for(int i=0;i<=arrayPizzasResumen.size()-1;i++){
            acumulapizzas=acumulapizzas + arrayPizzasResumen.get(i).getCantidad()+", "+arrayPizzasResumen.get(i).getNombre()+", "+arrayPizzasResumen.get(i).getTipo()+", "+arrayPizzasResumen.get(i).getTamaño()+", "+arrayPizzasResumen.get(i).calculaPrecioTotal()+" €"+"\n";
        }
        pizzas.setText(acumulapizzas);
        //estructura repetitiva para organizar los datos recibidos de las bebidas
        for(int i=0;i<=arrayBebidasResumen.size()-1;i++){
            acumulabebidas=acumulabebidas + arrayBebidasResumen.get(i)+"\n";
        }
        bebidas.setText(acumulabebidas);

        //instrucciones para mostrar los premios conseguidos según el precio del pedido
        imagenpeluche.setVisibility(View.INVISIBLE);

        if (totalprecio>=20) {
            peluche.setText("Has ganado un peluche por realizar un pedido superior a 20 €.");
            imagenpeluche.setVisibility(View.VISIBLE);
        }
        if(totalprecio>=33) {
            vale.setText("Has ganado un vale para comer en el comedor de Cebanc por realizar un pedido superior a 33€.");

        }
        //listener del botón aceptar
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast1 = Toast.makeText(getApplicationContext(), "PEDIDO ENVIADO. Saliendo de la aplicación...", Toast.LENGTH_SHORT);
                toast1.show();
                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        //listener del botón cancelar
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast1 = Toast.makeText(getApplicationContext(), "PEDIDO CANCELADO. Saliendo de la aplicación...", Toast.LENGTH_SHORT);
                toast1.show();
                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

}
