package com.alumno.cebancpizza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DatosPedido extends AppCompatActivity {
    private TabHost TbH;
    private Spinner tip,tam;
    private Button sig,sal;
    private TextView preciocarbo,preciobar,precioque,preciove,preciotro,preciococacola,preciolimon,precionaranja,precionestea,preciocerveza,precioagua;
    private EditText cantCar,cantBar,cantQue,cantVe,cantTro,cantCo,cantLi,cantNa,cantNes,cantCer,cantAgua;
    int tampizza=3,tipopizza=5;
    private double[][] preciospizza = new double[tampizza][tipopizza];
    private double[] preciosbebida= {1.99,1,1.5,1.5,1,1.25}; //array con los precios de las bebidas
    private int posispinner=0;
    private Button botonCarbonara,botonBarbacoa,boton4Quesos,botonVegetal, botonTropical,botonCocaCola,botonLimon,botonNaranja,botonNestea,botonCerveza,botonAgua;
    private TextView textoCarbonara,textoBarbacoa,texto4Quesos,textoVegetal,textoTropical;
    ArrayList<String> arrayPizzas = new ArrayList<String> ();
    private String tamañoPizza,tipoMasa;
    private TextView textoCocaCola,textoLimon,textoNestea,textoNaranja,textoCerveza,textoAgua;
    ArrayList<String> arrayBebidas = new ArrayList<String> ();
    double acumulaprecios=0;
    //variables para recoger los datos del cliente
    String n,d,t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedido);

        sig=(Button)findViewById(R.id.btnSiguiente2);
        sal=(Button)findViewById(R.id.btnSalir2);
        botonCarbonara=(Button)findViewById(R.id.btnCarbonara);
        botonBarbacoa=(Button)findViewById(R.id.btnBarbacoa);
        boton4Quesos=(Button)findViewById(R.id.btn4Quesos);
        botonVegetal=(Button)findViewById(R.id.btnVegetal);
        botonTropical=(Button)findViewById(R.id.btnTropical);
        botonCocaCola=(Button)findViewById(R.id.btnCocaCola);
        botonLimon=(Button)findViewById(R.id.btnLimon);
        botonNaranja=(Button)findViewById(R.id.btnNaranja);
        botonNestea=(Button)findViewById(R.id.btnNestea);
        botonCerveza=(Button)findViewById(R.id.btnCerveza);
        botonAgua=(Button)findViewById(R.id.btnAgua);

        preciocarbo=(TextView)findViewById(R.id.lblPrecioCarbonara);
        preciobar=(TextView)findViewById(R.id.lblPrecioBarbacoa);
        precioque=(TextView)findViewById(R.id.lblPrecioQuesos);
        preciove=(TextView)findViewById(R.id.lblPrecioVegetal);
        preciotro=(TextView)findViewById(R.id.lblPrecioTropical);
        preciococacola=(TextView)findViewById(R.id.lblPrecioCocaCola);
        preciolimon=(TextView)findViewById(R.id.lblPrecioLimon);
        precionaranja=(TextView)findViewById(R.id.lblPrecioNaranja);
        precionestea=(TextView)findViewById(R.id.lblPrecioNestea);
        preciocerveza=(TextView)findViewById(R.id.lblPrecioCerveza);
        precioagua=(TextView)findViewById(R.id.lblPrecioAgua);

        cantCar=(EditText)findViewById(R.id.txtCantCarbo);
        cantBar=(EditText)findViewById(R.id.txtCantBar);
        cantQue=(EditText)findViewById(R.id.txtCantQuesos);
        cantVe=(EditText)findViewById(R.id.txtCantVegetal);
        cantTro=(EditText)findViewById(R.id.txtCantTropical);
        cantCo=(EditText)findViewById(R.id.txtCantCocaCola);
        cantLi=(EditText)findViewById(R.id.txtCantLimon);
        cantNa=(EditText)findViewById(R.id.txtCantNaranja);
        cantNes=(EditText)findViewById(R.id.txtCantNestea);
        cantCer=(EditText)findViewById(R.id.txtCantCerveza);
        cantAgua=(EditText)findViewById(R.id.txtCantAgua);

        textoCarbonara=(TextView)findViewById(R.id.txtCarbonara);
        textoBarbacoa=(TextView)findViewById(R.id.txtBarbacoa);
        texto4Quesos=(TextView)findViewById(R.id.txt4Quesos);
        textoVegetal=(TextView)findViewById(R.id.txtVegetal);
        textoTropical=(TextView)findViewById(R.id.txtTropical);
        textoCocaCola=(TextView)findViewById(R.id.txtCocaCola);
        textoLimon=(TextView)findViewById(R.id.txtLimon);
        textoNaranja=(TextView)findViewById(R.id.txtNaranja);
        textoNestea=(TextView)findViewById(R.id.txtNestea);
        textoCerveza=(TextView)findViewById(R.id.txtCerveza);
        textoAgua=(TextView)findViewById(R.id.txtAgua);

        //recogemos los datos del cliente de la actividad anterior
        n=getIntent().getStringExtra("nombre");
        d=getIntent().getStringExtra("direccion");
        t=getIntent().getStringExtra("telefono");
        //llamada al método para meter los precios de las pizzas escogidas en el array
        rellenarArray(preciospizza);
        //listener del botón siguiente
        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarResumen();
            }
        });
        //listener del botón salir
        sal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();    //instrucciones para salir de la aplicación
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        //instrucciones para añadir pestañas al layout
        TbH = (TabHost) findViewById(R.id.tabHost); //llamamos al Tabhost
        TbH.setup();                 //lo activamos

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
        //adaptador de los spinners
        ArrayAdapter<CharSequence> adaptadortip = ArrayAdapter.createFromResource(this,R.array.tipo_pizza, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adaptadortam = ArrayAdapter.createFromResource(this,R.array.tamaño_pizza, android.R.layout.simple_spinner_item);

        adaptadortip.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adaptadortam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tip.setAdapter(adaptadortip);
        tam.setAdapter(adaptadortam);

        //Esto es el spinner del tipo de masa
        tip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) { //variables para recoger el tipo de masa escogida
                    tipoMasa = "Masa fina";

                } else if (position == 1) {
                    tipoMasa = "Masa normal";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Este es el spinner del tamaño de la pizza
        //Según el tamaño cambia el precio
        tam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posispinner=position;
                if(position==0){
                    preciocarbo.setText(""+Double.parseDouble(cantCar.getText().toString()) * preciospizza[0][0]);
                    preciobar.setText(""+ Double.parseDouble(cantBar.getText().toString()) * preciospizza[0][1]);
                    precioque.setText(""+ Double.parseDouble(cantQue.getText().toString()) * preciospizza[0][2]);
                    preciove.setText(""+ Double.parseDouble(cantVe.getText().toString()) * preciospizza[0][3]);
                    preciotro.setText(""+ Double.parseDouble(cantTro.getText().toString()) * preciospizza[0][4]);
                    tamañoPizza="Individual";
                }else if(position==1) {
                    preciocarbo.setText(""+ Double.parseDouble(cantCar.getText().toString()) * preciospizza[1][0]);
                    preciobar.setText(""+ Double.parseDouble(cantBar.getText().toString()) * preciospizza[1][1]);
                    precioque.setText(""+ Double.parseDouble(cantQue.getText().toString()) * preciospizza[1][2]);
                    preciove.setText(""+ Double.parseDouble(cantVe.getText().toString()) * preciospizza[1][3]);
                    preciotro.setText(""+ Double.parseDouble(cantTro.getText().toString()) * preciospizza[1][4]);
                    tamañoPizza="Mediana";
                }else{
                    preciocarbo.setText(""+ Double.parseDouble(cantCar.getText().toString()) * preciospizza[2][0]);
                    preciobar.setText(""+ Double.parseDouble(cantBar.getText().toString()) * preciospizza[2][1]);
                    precioque.setText(""+ Double.parseDouble(cantQue.getText().toString()) * preciospizza[2][2]);
                    preciove.setText(""+ Double.parseDouble(cantVe.getText().toString()) * preciospizza[2][3]);
                    preciotro.setText(""+ Double.parseDouble(cantTro.getText().toString()) * preciospizza[2][4]);
                    tamañoPizza="Familiar";
                }
                preciococacola.setText("" + Double.parseDouble(cantCo.getText().toString()) * preciosbebida[0] );
                preciolimon.setText("" + Double.parseDouble(cantLi.getText().toString()) * preciosbebida[1] );
                precionaranja.setText(""+ Double.parseDouble(cantNa.getText().toString()) * preciosbebida[2]);
                precionestea.setText(""+ Double.parseDouble(cantNes.getText().toString()) * preciosbebida[3]);
                preciocerveza.setText("" + Double.parseDouble(cantCer.getText().toString()) * preciosbebida[4]);
                precioagua.setText("" + Double.parseDouble(cantAgua.getText().toString()) * preciosbebida[5]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Log.e("info", "entra");

        //añadir a un arraylist las pizzas que elija el usuario

        botonCarbonara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayPizzas.add(cantCar.getText().toString()+", "+textoCarbonara.getText().toString()+", "+tamañoPizza.toString()+", "+tipoMasa.toString()+", "+preciocarbo.getText().toString());
                acumulaprecios=acumulaprecios+Double.parseDouble(preciocarbo.getText().toString());

                Toast toast1 = Toast.makeText(getApplicationContext(), "Pizza añadida", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        botonBarbacoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayPizzas.add(cantBar.getText().toString() + ", " + textoBarbacoa.getText().toString() + ", " + tamañoPizza.toString() + ", " + tipoMasa.toString() + ", " + preciobar.getText().toString());
                acumulaprecios = acumulaprecios + Double.parseDouble(preciobar.getText().toString());
                Toast toast1 = Toast.makeText(getApplicationContext(), "Pizza añadida", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        boton4Quesos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayPizzas.add(cantQue.getText().toString() + ", " + texto4Quesos.getText().toString() + ", " + tamañoPizza.toString() + ", " + tipoMasa.toString() + ", " + precioque.getText().toString());
                acumulaprecios = acumulaprecios + Double.parseDouble(precioque.getText().toString());
                Toast toast1 = Toast.makeText(getApplicationContext(), "Pizza añadida", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        botonVegetal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayPizzas.add(cantVe.getText().toString() + ", " + textoVegetal.getText().toString() + ", " + tamañoPizza.toString() + ", " + tipoMasa.toString() + ", " + preciove.getText().toString());
                acumulaprecios = acumulaprecios + Double.parseDouble(preciove.getText().toString());
                Toast toast1 = Toast.makeText(getApplicationContext(), "Pizza añadida", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        botonTropical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayPizzas.add(cantTro.getText().toString() + ", " + textoTropical.getText().toString() + ", " + tamañoPizza.toString() + ", " + tipoMasa.toString() + ", " + preciotro.getText().toString());
                acumulaprecios = acumulaprecios + Double.parseDouble(preciotro.getText().toString());
                Toast toast1 = Toast.makeText(getApplicationContext(), "Pizza añadida", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        //Ahora cuando cambia la cantidad cambia el precio

        cantCar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!cantCar.getText().toString().equals("")) {
                    if (posispinner==0){
                        preciocarbo.setText(""+ Double.parseDouble(cantCar.getText().toString()) * preciospizza[0][0]);
                    }else if(posispinner==1){
                        preciocarbo.setText(""+ Double.parseDouble(cantCar.getText().toString()) * preciospizza[1][0]);
                    }else{
                        preciocarbo.setText(""+ Double.parseDouble(cantCar.getText().toString()) * preciospizza[2][0]);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cantBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!cantBar.getText().toString().equals("")) {
                    if (posispinner == 0) {
                        preciobar.setText("" + Double.parseDouble(cantBar.getText().toString()) * preciospizza[0][1]);
                    } else if (posispinner == 1) {
                        preciobar.setText("" + Double.parseDouble(cantBar.getText().toString()) * preciospizza[1][1]);
                    } else {
                        preciobar.setText("" + Double.parseDouble(cantBar.getText().toString()) * preciospizza[2][1]);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cantQue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!cantQue.getText().toString().equals("")) {
                    if (posispinner == 0) {
                        precioque.setText("" + Double.parseDouble(cantQue.getText().toString()) * preciospizza[0][2]);
                    } else if (posispinner == 1) {
                        precioque.setText("" + Double.parseDouble(cantQue.getText().toString()) * preciospizza[1][2]);
                    } else {
                        precioque.setText("" + Double.parseDouble(cantQue.getText().toString()) * preciospizza[2][2]);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cantVe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!cantVe.getText().toString().equals("")) {
                    if (posispinner == 0) {
                        preciove.setText("" + Double.parseDouble(cantVe.getText().toString()) * preciospizza[0][3]);
                    } else if (posispinner == 1) {
                        preciove.setText("" + Double.parseDouble(cantVe.getText().toString()) * preciospizza[1][3]);
                    } else {
                        preciove.setText("" + Double.parseDouble(cantVe.getText().toString()) * preciospizza[2][3]);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cantTro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!cantTro.getText().toString().equals("")) {
                    if (posispinner == 0) {
                        preciotro.setText("" + Double.parseDouble(cantTro.getText().toString()) * preciospizza[0][4]);
                    } else if (posispinner == 1) {
                        preciotro.setText("" + Double.parseDouble(cantTro.getText().toString()) * preciospizza[1][4]);
                    } else {
                        preciotro.setText("" + Double.parseDouble(cantTro.getText().toString()) * preciospizza[2][4]);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //añadir las bebidas a otro arraylist

        botonCocaCola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayBebidas.add(cantCo.getText().toString()+", "+textoCocaCola.getText().toString()+", "+preciococacola.getText().toString());
                acumulaprecios=acumulaprecios+Double.parseDouble(preciococacola.getText().toString());
                Toast toast1 = Toast.makeText(getApplicationContext(), "Bebida añadida", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        botonLimon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayBebidas.add(cantLi.getText().toString()+", "+textoLimon.getText().toString()+", "+preciolimon.getText().toString());
                acumulaprecios=acumulaprecios+Double.parseDouble(preciolimon.getText().toString());
                Toast toast1 = Toast.makeText(getApplicationContext(), "Bebida añadida", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        botonNaranja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayBebidas.add(cantNa.getText().toString()+", "+textoNaranja.getText().toString()+", "+precionaranja.getText().toString());
                acumulaprecios=acumulaprecios+Double.parseDouble(precionaranja.getText().toString());
                Toast toast1 = Toast.makeText(getApplicationContext(), "Bebida añadida", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        botonNestea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayBebidas.add(cantNes.getText().toString()+", "+textoNestea.getText().toString()+", "+precionestea.getText().toString());
                acumulaprecios=acumulaprecios+Double.parseDouble(precionestea.getText().toString());
                Toast toast1 = Toast.makeText(getApplicationContext(), "Bebida añadida", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        botonCerveza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayBebidas.add(cantCer.getText().toString()+", "+textoCerveza.getText().toString()+", "+preciocerveza.getText().toString());
                acumulaprecios=acumulaprecios+Double.parseDouble(preciocerveza.getText().toString());
                Toast toast1 = Toast.makeText(getApplicationContext(), "Bebida añadida", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        botonAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayBebidas.add(cantAgua.getText().toString() + ", " + textoAgua.getText().toString() + ", " + precioagua.getText().toString());
                acumulaprecios = acumulaprecios + Double.parseDouble(precioagua.getText().toString());
                Toast toast1 = Toast.makeText(getApplicationContext(), "Bebida añadida", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        cantCo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!cantCo.getText().toString().equals("")) {
                    preciococacola.setText("" + Double.parseDouble(cantCo.getText().toString()) * preciosbebida[0]);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cantLi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!cantLi.getText().toString().equals("")) {
                    preciolimon.setText("" + Double.parseDouble(cantLi.getText().toString()) * preciosbebida[1]);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cantNa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!cantNa.getText().toString().equals("")) {
                    precionaranja.setText("" + Double.parseDouble(cantNa.getText().toString()) * preciosbebida[2]);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cantNes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!cantNes.getText().toString().equals("")) {
                    precionestea.setText("" + Double.parseDouble(cantNes.getText().toString()) * preciosbebida[3]);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cantCer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!cantCer.getText().toString().equals("")) {
                    preciocerveza.setText("" + Double.parseDouble(cantCer.getText().toString()) * preciosbebida[4]);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cantAgua.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!cantAgua.getText().toString().equals("")) {
                    precioagua.setText("" + Double.parseDouble(cantAgua.getText().toString()) * preciosbebida[5]);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    //método para lanzar la siguiente actividad y mandarle los datos
    public void lanzarResumen(){
        Intent i=new Intent(this, Resumen.class);
        i.putExtra("nombre", n);
        i.putExtra("direccion", d);
        i.putExtra("telefono", t);
        i.putExtra("ap",arrayPizzas);
        i.putExtra("ab",arrayBebidas);
        i.putExtra("acumprecios",acumulaprecios);
        startActivity(i);
    }
    //método para meter en un array los precios de las pizzas
    public void rellenarArray(double[][] array){
        //
        array[0][0]=8.5;
        array[0][1]=9.99;
        array[0][2]=9.5;
        array[0][3]=8.5;
        array[0][4]=10.5;

        array[1][0]=13.5;
        array[1][1]=12.99;
        array[1][2]=13.5;
        array[1][3]=14.5;
        array[1][4]=15.5;

        array[2][0]=16.5;
        array[2][1]=17.99;
        array[2][2]=16.5;
        array[2][3]=17.5;
        array[2][4]=18.5;
    }

}
