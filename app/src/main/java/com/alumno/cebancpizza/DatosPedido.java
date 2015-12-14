package com.alumno.cebancpizza;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;

/**
 * Created by adminportatil on 10/12/2015.
 */
public class DatosPedido extends AppCompatActivity {
    TabHost TbH;
    private Spinner tip,tam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedido);

        TbH = (TabHost) findViewById(R.id.tabHost); //llamamos al Tabhost
        TbH.setup();                                                         //lo activamos

        TabHost.TabSpec tab1 = TbH.newTabSpec("tab1");  //aspectos de cada Tab (pestaña)
        TabHost.TabSpec tab2 = TbH.newTabSpec("tab2");

        tab1.setIndicator("Pizzas");    //qué queremos que aparezca en las pestañas
        tab1.setContent(R.id.tab1); //definimos el id de cada Tab (pestaña)

        tab2.setIndicator("Bebidas");
        tab2.setContent(R.id.tab2);

        TbH.addTab(tab1); //añadimos los tabs ya programados
        TbH.addTab(tab2);

        tip=(Spinner)findViewById(R.id.cmbTipo);
        tam=(Spinner)findViewById(R.id.cmbTamaño);

        ArrayAdapter<CharSequence> adaptadortip = ArrayAdapter.createFromResource(this,R.array.tipo_pizza, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adaptadortam = ArrayAdapter.createFromResource(this,R.array.tamaño_pizza, android.R.layout.simple_spinner_item);

        adaptadortip.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adaptadortam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tip.setAdapter(adaptadortip);
        tam.setAdapter(adaptadortam);

        tip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //lblMensaje.setText("Seleccionado:" +parent.getItemAtPosition(position));
                //Podemos recuperar el ítem seleccionado usando
                //parent.getItemAtPosition(position)
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //lblMensaje.setText("Seleccionado:" +parent.getItemAtPosition(position));
                //Podemos recuperar el ítem seleccionado usando
                //parent.getItemAtPosition(position)
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
