package com.alumno.cebancpizza;

import android.app.AlertDialog;
import android.app.Application;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DatosCliente extends AppCompatActivity{
    private Button sig,sal,buscar;
    private EditText nombre,direccion,telefono;
    private String nomb,direc,tel;
    private boolean comprobar,existecliente=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_cliente);

        sig=(Button)findViewById(R.id.btnSiguiente);
        sal=(Button)findViewById(R.id.btnSalir);
        buscar=(Button)findViewById(R.id.btnBuscarCliente);
        nombre=(EditText)findViewById(R.id.txtNombre);
        direccion=(EditText)findViewById(R.id.txtDireccion);
        telefono=(EditText)findViewById(R.id.txtTelefono);

        //listener del botón siguiente
        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobar=datos();
                lanzarDatosPedido(comprobar);
                alta(v);
            }
        });
        //listener del botón salir
        sal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alerta();
            }
        });

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultaporcodigo(v);
            }
        });
    }
    //método para lanzar la siguiente actividad y mandarle los datos
    public void lanzarDatosPedido(boolean c){
        if(c){

            Intent i=new Intent(this, DatosPedido.class);
            Cliente cli=new Cliente(nomb,direc,tel);
            i.putExtra("cliente", cli);
            startActivity(i);
        }
    }
    //método para comprobar que los editText tengan algo escrito y recoger los datos del cliente
    public boolean datos(){
        boolean lleno=false;

        if(nombre.getText().toString().equals("") && direccion.getText().toString().equals("") && telefono.getText().toString().equals("")){
            Toast.makeText(this, "Falta introducir los datos", Toast.LENGTH_SHORT).show();
            lleno=false;
        }else if(nombre.getText().toString().equals("") || direccion.getText().toString().equals("") || telefono.getText().toString().equals("")) {
            if (nombre.getText().toString().equals("")) {
                Toast.makeText(this, "Falta introducir el nombre", Toast.LENGTH_SHORT).show();
                lleno = false;
            }
            if (direccion.getText().toString().equals("")) {
                Toast.makeText(this, "Falta introducir la dirección", Toast.LENGTH_SHORT).show();
                lleno = false;
            }
            if (telefono.getText().toString().equals("")) {
                Toast.makeText(this, "Falta introducir el número de teléfono", Toast.LENGTH_SHORT).show();
                lleno = false;
            }
        }else{
            nomb=nombre.getText().toString();
            direc=direccion.getText().toString();
            tel=telefono.getText().toString();
            lleno=true;
        }
        return lleno;
    }

    public void consultaporcodigo(View v) {
        existecliente=false;
        DbHelper admin = new DbHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        nomb = nombre.getText().toString();
        Cursor fila = bd.rawQuery("select direccion, telefono from Clientes where nombre='"+ nomb +"'", null);
        if (fila.moveToFirst()) {
            direccion.setText(fila.getString(0));
            telefono.setText(fila.getString(1));
            existecliente=true;
        } else {
            Toast.makeText(this, "No existe el cliente. Rellene todos los datos y al darle a siguiente quedara registrado", Toast.LENGTH_SHORT).show();
            direccion.setText("");
            telefono.setText("");
        }
        bd.close();
    }

    public void alta(View v) {
        if (existecliente==false){
            DbHelper admin = new DbHelper(this);
            SQLiteDatabase bd = admin.getWritableDatabase();
            nomb= nombre.getText().toString();
            direc = direccion.getText().toString();
            tel = telefono.getText().toString();
            ContentValues registro = new ContentValues();
            registro.put("nombre", nomb);
            registro.put("direccion", direc);
            registro.put("telefono", tel);
            bd.insert("clientes", null, registro);
            bd.close();
            nombre.setText("");
            direccion.setText("");
            telefono.setText("");
            Toast.makeText(this, "Se cargaron los datos del cliente", Toast.LENGTH_SHORT).show();
        }

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

}
