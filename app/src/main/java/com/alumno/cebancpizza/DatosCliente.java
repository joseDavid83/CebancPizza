package com.alumno.cebancpizza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DatosCliente extends AppCompatActivity{
    private Button sig,sal;
    private EditText nombre,direccion,telefono;
    private String nomb,direc,tel;
    private boolean comprobar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_cliente);

        sig=(Button)findViewById(R.id.btnSiguiente);
        sal=(Button)findViewById(R.id.btnSalir);
        nombre=(EditText)findViewById(R.id.txtNombre);
        direccion=(EditText)findViewById(R.id.txtDireccion);
        telefono=(EditText)findViewById(R.id.txtTelefono);



        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobar=datos();
                lanzarDatosPedido(comprobar);
            }
        });

        sal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void lanzarDatosPedido(boolean c){
        if(c){
            Intent i=new Intent(this, DatosPedido.class);
            i.putExtra("nombre", nomb);
            i.putExtra("direccion", direc);
            i.putExtra("telefono", tel);
            startActivity(i);
        }
    }

    public boolean datos(){
        boolean lleno;
        if(nombre.getText().toString().equals("") && direccion.getText().toString().equals("") && telefono.getText().toString().equals("")){
            Toast.makeText(this, "Falta algún dato", Toast.LENGTH_SHORT).show();
            lleno=false;
        }else{
            nomb=nombre.getText().toString();
            direc=direccion.getText().toString();
            tel=telefono.getText().toString();
            lleno=true;
        }
        return lleno;
    }




}
