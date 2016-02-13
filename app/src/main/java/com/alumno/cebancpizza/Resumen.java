package com.alumno.cebancpizza;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    ArrayList<Bebida> arrayBebidasResumen = new ArrayList<Bebida>();
    private String acumulapizzas="",acumulabebidas="";
    private double totalprecio;
    private ImageView imagenpeluche;
    private Button botonAceptar,botonCancelar,botonMostrar;
    Cliente cli;
    DecimalFormat df = new DecimalFormat("0.00");
    String nombre,tipo,tamaño;
    int cantidad;
    double precio;
    int idcliente;

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
        botonMostrar=(Button)findViewById(R.id.btnMostrar);

        //variables para recoger los datos mandados
        cli =(Cliente)getIntent().getExtras().getSerializable("cliente");

        nombre2.setText("Nombre: "+ cli.getNombre());
        dire2.setText("Dirección: " + cli.getDireccion());
        telef2.setText("Teléfono: "+ cli.getTelefono());

        arrayPizzasResumen=(ArrayList<Pizza>)getIntent().getExtras().getSerializable("ap");
        arrayBebidasResumen=(ArrayList<Bebida>)getIntent().getExtras().getSerializable("ab");
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
            acumulabebidas=acumulabebidas +arrayBebidasResumen.get(i).getCantidad()+", "+arrayBebidasResumen.get(i).getNombre()+", "+arrayBebidasResumen.get(i).getPrecio()+" €"+"\n";
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
                Toast toast1 = Toast.makeText(getApplicationContext(), "PEDIDO ENVIADO.", Toast.LENGTH_SHORT);
                toast1.show();
                consultaporcodigo(v);
                altaPizzas(v);
                altaBebidas(v);
            }
        });
        //listener del botón cancelar
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alerta();

            }
        });

        botonMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarMostrarPedidos();
            }
        });

    }
    public void altaPizzas(View v) {
        DbHelper admin = new DbHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        for(int i=0;i<=arrayPizzasResumen.size()-1;i++){

            nombre= arrayPizzasResumen.get(i).getNombre();
            tipo = arrayPizzasResumen.get(i).getTipo();
            tamaño = arrayPizzasResumen.get(i).getTamaño();
            cantidad=arrayPizzasResumen.get(i).getCantidad();
            precio=arrayPizzasResumen.get(i).getPrecio();
            ContentValues registro = new ContentValues();

            registro.put("nombre", nombre);
            registro.put("tipo", tipo);
            registro.put("tamano", tamaño);
            registro.put("cantidad", cantidad);
            registro.put("precio", precio);
            registro.put("cliente", idcliente);
            bd.insert("pizzas", null, registro);
        }
        bd.close();
        Toast.makeText(this, "Se cargaron los datos del cliente ", Toast.LENGTH_SHORT).show();
    }
    public void consultaporcodigo(View v) {
        DbHelper admin = new DbHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor fila = bd.rawQuery("select idcliente from Clientes where nombre='"+ cli.getNombre() +"' and direccion='"+ cli.getDireccion() +"' and telefono='"+cli.getTelefono()+"'", null);
        if (fila.moveToFirst()) {
            idcliente=fila.getInt(0);

        }
        bd.close();
    }
    public void altaBebidas(View v) {
        DbHelper admin = new DbHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        for(int i=0;i<=arrayBebidasResumen.size()-1;i++){

            nombre= arrayBebidasResumen.get(i).getNombre();
            cantidad=arrayBebidasResumen.get(i).getCantidad();
            precio=arrayBebidasResumen.get(i).getPrecio();
            ContentValues registro = new ContentValues();

            registro.put("nombre", nombre);
            registro.put("cantidad", cantidad);
            registro.put("precio", precio);
            registro.put("cliente", idcliente);
            bd.insert("bebidas", null, registro);
        }
        bd.close();

    }
    public void lanzarMostrarPedidos(){
        Intent i=new Intent(this, MostrarPedidos.class);
        startActivity(i);
    }

    public void alerta(){
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("¿ Esta seguro de salir de la aplicación?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                aceptar();
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }
    public void aceptar() {
        Toast toast1 = Toast.makeText(getApplicationContext(), "PEDIDO CANCELADO. Saliendo de la aplicación...", Toast.LENGTH_SHORT);
        toast1.show();
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @Override
    protected void onDestroy()
    {
        Log.e("METODO","onDestroy");
        super.onDestroy();

    }
    @Override
    protected void onPause()
    {
        Log.e("METODO","onPause");
        super.onPause();
    }
    @Override
    protected void onRestart()
    {
        Log.e("METODO","onRestart");
        super.onRestart();
    }
    @Override
    protected void onResume()
    {
        Log.e("METODO","onResume");
        super.onResume();
    }
    @Override
    protected void onStart()
    {
        Log.e("METODO","onStart");
        super.onStart();
    }
    @Override
    protected void onStop()
    {
        Log.e("METODO","onStop");
        super.onStop();
    }

}
