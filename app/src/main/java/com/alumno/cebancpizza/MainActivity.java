package com.alumno.cebancpizza;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button entrar;
    private Button salir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper helper=new DbHelper(this);
        SQLiteDatabase db=helper.getWritableDatabase();

        entrar=(Button)findViewById(R.id.btnEntrar);
        salir=(Button)findViewById(R.id.btnSalir);

        //listener del botón entrar
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarDatosClientes(); //llamada al método para lanzar la siguiente actividad
            }
        });
        //listener del botón salir
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast1 = Toast.makeText(getApplicationContext(), "Saliendo de la aplicación...", Toast.LENGTH_SHORT);
                toast1.show();
                finish();  //método para salir de la aplicación
            }
        });
    }
    //método para lanzar la siguiente actividad
    public void lanzarDatosClientes(){
        Intent i=new Intent(this, DatosCliente.class);
        startActivity(i);
    }
}
