package com.alumno.cebancpizza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by adminportatil on 10/12/2015.
 */
public class DatosPedido extends AppCompatActivity {
    private TabHost TbH;
    private Spinner tip,tam;
    private Button sig;
    private TextView preciocarbo,preciobar,precioque,preciove,preciotro;
    private EditText cantCar,cantBar,cantQue,cantVe,cantTro;
    int tampizza=3,tipopizza=5;
    private double[][] precios = new double[tampizza][tipopizza];

    //variables para recoger los datos del cliente
    String n,d,t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedido);

        sig=(Button)findViewById(R.id.btnSiuiente2);
        preciocarbo=(TextView)findViewById(R.id.lblPrecioCarbonara);
        preciobar=(TextView)findViewById(R.id.lblPrecioBarbacoa);
        precioque=(TextView)findViewById(R.id.lblPrecioQuesos);
        preciove=(TextView)findViewById(R.id.lblPrecioVegetal);
        preciotro=(TextView)findViewById(R.id.lblPrecioTropical);

        cantCar=(EditText)findViewById(R.id.txtCantCarbo);
        cantBar=(EditText)findViewById(R.id.txtCantBar);
        cantQue=(EditText)findViewById(R.id.txtCantQuesos);
        cantVe=(EditText)findViewById(R.id.txtCantVegetal);
        cantTro=(EditText)findViewById(R.id.txtCantTropical);


        //recogemos los datos del cliente de la actividad anterior
        n=getIntent().getStringExtra("nombre");
        d=getIntent().getStringExtra("direccion");
        t=getIntent().getStringExtra("telefono");

        rellenarArray(precios);

        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarResumen();
            }
        });

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

                if(position==0){
                    preciocarbo.setText("Precio: "+ Double.parseDouble(cantCar.getText().toString()) * precios[0][0]+" €");
                    preciobar.setText("Precio: "+ Double.parseDouble(cantBar.getText().toString()) * precios[0][1]+" €");
                    precioque.setText("Precio: "+ Double.parseDouble(cantQue.getText().toString()) * precios[0][2]+" €");
                    preciove.setText("Precio: "+ Double.parseDouble(cantVe.getText().toString()) * precios[0][3]+" €");
                    preciotro.setText("Precio: "+ Double.parseDouble(cantTro.getText().toString()) * precios[0][4]+" €");
                }else if(position==1) {
                    preciocarbo.setText("Precio: "+ Double.parseDouble(cantCar.getText().toString()) * precios[1][0]+" €");
                    preciobar.setText("Precio: "+ Double.parseDouble(cantBar.getText().toString()) * precios[1][1]+" €");
                    precioque.setText("Precio: "+ Double.parseDouble(cantQue.getText().toString()) * precios[1][2]+" €");
                    preciove.setText("Precio: "+ Double.parseDouble(cantVe.getText().toString()) * precios[1][3]+" €");
                    preciotro.setText("Precio: "+ Double.parseDouble(cantTro.getText().toString()) * precios[1][4]+" €");
                }else{
                    preciocarbo.setText("Precio: "+ Double.parseDouble(cantCar.getText().toString()) * precios[2][0]+" €");
                    preciobar.setText("Precio: "+ Double.parseDouble(cantBar.getText().toString()) * precios[2][1]+" €");
                    precioque.setText("Precio: "+ Double.parseDouble(cantQue.getText().toString()) * precios[2][2]+" €");
                    preciove.setText("Precio: "+ Double.parseDouble(cantVe.getText().toString()) * precios[2][3]+" €");
                    preciotro.setText("Precio: "+ Double.parseDouble(cantTro.getText().toString()) * precios[2][4]+" €");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Log.e("info", "entra");
    }

    public void lanzarResumen(){
        Intent i=new Intent(this, Resumen.class);
        i.putExtra("nombre", n);
        i.putExtra("direccion", d);
        i.putExtra("telefono", t);
        startActivity(i);
    }

    public void rellenarArray(double[][] array){
        //
        array[0][0]=8.95;
        array[0][1]=9.99;
        array[0][2]=9.5;
        array[0][3]=8.5;
        array[0][4]=10.5;

        array[1][0]=13.95;
        array[1][1]=12.99;
        array[1][2]=13.5;
        array[1][3]=14.5;
        array[1][4]=15.5;

        array[2][0]=16.95;
        array[2][1]=17.99;
        array[2][2]=16.5;
        array[2][3]=17.5;
        array[2][4]=18.5;
    }

}
