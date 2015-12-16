package com.alumno.cebancpizza;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by adminportatil on 10/12/2015.
 */
public class Resumen extends AppCompatActivity{
    private TextView nombre,dire,telef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resumen);

        nombre=(TextView)findViewById(R.id.lblNombre);
        dire=(TextView)findViewById(R.id.lblDireccion);
        telef=(TextView)findViewById(R.id.lblTelefono);


    }
}
