package com.alumno.cebancpizza;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by adminportatil on 04/02/2016.
 */
public class MostrarPedidos extends Activity {

    private TextView mostrar;
    private ArrayList<String> clientes=new ArrayList<String>();
    private ArrayList<Integer> idclientes=new ArrayList<Integer>();
    DecimalFormat df = new DecimalFormat("0.00");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_pedidos);

        mostrar=(TextView)findViewById(R.id.txtMostrar);
        consultaclientes();
        consultapedidos();

    }
    public void consultaclientes() {

        DbHelper admin = new DbHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery("select idcliente,nombre,direccion,telefono from clientes", null);
        //Nos aseguramos de que existe al menos un registro
        if (fila.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {

                String cadenacliente= fila.getString(0)+" "+fila.getString(1)+" "+fila.getString(2)+" "+fila.getString(3);
                clientes.add(cadenacliente);
                idclientes.add(fila.getInt(0));


            } while(fila.moveToNext());
        }
        bd.close();
    }

    public void consultapedidos() {
        Log.e("iepa", "entra2");
        String cadenacliente="";
        String cadenatotal="";
        String cadenapedidos="";

        DbHelper admin = new DbHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        for (int i=0;i<=idclientes.size()-1;i++) {
            double preciototal=0;
            cadenacliente=clientes.get(i)+"\n"+"\n";
            Cursor fila = bd.rawQuery("select p.cantidad,p.nombre,tamano,tipo,p.precio,b.cantidad,b.nombre,b.precio from pizzas p,bebidas b,clientes c where c.idcliente=p.cliente and c.idcliente=b.cliente and idcliente="+idclientes.get(i)+"", null);
            //Nos aseguramos de que existe al menos un registro

            if (fila.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    cadenapedidos = cadenapedidos+fila.getString(0) + " " + fila.getString(1) + " " + fila.getString(2) + " " + fila.getString(3)+ " " + fila.getString(4)+" €/unidad"+ "\n"+ fila.getString(5)+ " " + fila.getString(6)+ " " + fila.getString(7)+ " €/unidad"+"\n" ;
                    double precioPizza=fila.getDouble(4),precioBebida=fila.getDouble(7);
                    int cantidadPizza=fila.getInt(0),cantidadbebida=fila.getInt(5);
                    preciototal=preciototal+(precioPizza*cantidadPizza)+(precioBebida*cantidadbebida);
                } while (fila.moveToNext());
            }
            String tp=df.format(preciototal);
            cadenatotal=cadenatotal+cadenacliente+cadenapedidos+"El precio total gastado es: "+tp+" €"+"\n"+"\n";
            cadenapedidos="";
        }
        bd.close();
        mostrar.setText(cadenatotal);
    }
}
