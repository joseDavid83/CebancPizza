package com.alumno.cebancpizza;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DatosPedido extends AppCompatActivity {
    private TabHost TbH;
    private Spinner tip,tam;
    private Button sig,sal;
    private TextView preciocarbo,preciobar,precioque,preciove,preciotro,preciococacola,preciolimon,precionaranja,precionestea,preciocerveza,precioagua;
    private EditText cantCar,cantBar,cantQue,cantVe,cantTro,cantCo,cantLi,cantNa,cantNes,cantCer,cantAgua;
    int tampizza=3,tipopizza=5;
    private double[][] preciospizza = new double[tampizza][tipopizza];
    private int posispinner=0;
    private Button botonCarbonara,botonBarbacoa,boton4Quesos,botonVegetal, botonTropical,botonCocaCola,botonLimon,botonNaranja,botonNestea,botonCerveza,botonAgua;
    private TextView textoCarbonara,textoBarbacoa,texto4Quesos,textoVegetal,textoTropical;
    ArrayList<Pizza> arrayPizzas = new ArrayList<Pizza> ();
    private String tamañoPizza,tipoMasa;
    ArrayList<Bebida> arrayBebidas = new ArrayList<Bebida> ();
    double acumulaprecios=0;
    Cliente cli;
    Pizza carbo = new Pizza();
    Pizza barba = new Pizza();
    Pizza quesos = new Pizza();
    Pizza vege = new Pizza();
    Pizza tropi = new Pizza();

    Bebida coca=new Bebida("Coca-cola",1.99);
    Bebida limon=new Bebida("Limon",1);
    Bebida nara=new Bebida("Naranja",1.5);
    Bebida nes=new Bebida("Nestea",1.5);
    Bebida cerve=new Bebida("Cerveza",1);
    Bebida agua=new Bebida("Agua",1.25);
    DecimalFormat df = new DecimalFormat("0.00");

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


        tip=(Spinner)findViewById(R.id.cmbTipo);
        tam=(Spinner)findViewById(R.id.cmbTamaño);

        //recogemos los datos del cliente de la actividad anterior
        cli = (Cliente)getIntent().getExtras().getSerializable("cliente");


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
                alerta();
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
                posispinner = position;
                if (position == 0) {
                    cantidadPerciosPizzaIndividual();
                    imprimirPrecio();
                    tamañoPizza = "Individual";
                } else if (position == 1) {
                    cantidadPerciosPizzaMediana();
                    imprimirPrecio();
                    tamañoPizza = "Mediana";
                } else {
                    cantidadPerciosPizzaFamiliar();
                    imprimirPrecio();
                    tamañoPizza = "Familiar";
                }


                cantidadesBebidas();

                preciococacola.setText("" + coca.calculaPrecioTotal());
                preciolimon.setText("" + limon.calculaPrecioTotal());
                precionaranja.setText("" + nara.calculaPrecioTotal());
                precionestea.setText("" + nes.calculaPrecioTotal());
                preciocerveza.setText("" + cerve.calculaPrecioTotal());
                precioagua.setText("" + agua.calculaPrecioTotal());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //añadir a un arraylist las pizzas que elija el usuario

        botonCarbonara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carbo.setNombre(textoCarbonara.getText().toString());
                carbo.setTipo(tipoMasa);
                carbo.setTamaño(tamañoPizza);
                arrayPizzas.add(carbo);
                acumulaprecios=acumulaprecios+carbo.calculaPrecioTotal();
                Toast toast1 = Toast.makeText(getApplicationContext(), "Pizza añadida ", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        botonBarbacoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barba.setNombre(textoBarbacoa.getText().toString());
                barba.setTipo(tipoMasa);
                barba.setTamaño(tamañoPizza);
                arrayPizzas.add(barba);
                acumulaprecios = acumulaprecios + barba.calculaPrecioTotal();
                Toast toast1 = Toast.makeText(getApplicationContext(), "Pizza añadida", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        boton4Quesos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quesos.setNombre(texto4Quesos.getText().toString());
                quesos.setTipo(tipoMasa);
                quesos.setTamaño(tamañoPizza);
                arrayPizzas.add(quesos);
                acumulaprecios = acumulaprecios + quesos.calculaPrecioTotal();
                Toast toast1 = Toast.makeText(getApplicationContext(), "Pizza añadida", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        botonVegetal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vege.setNombre(textoVegetal.getText().toString());
                vege.setTipo(tipoMasa);
                vege.setTamaño(tamañoPizza);
                arrayPizzas.add(vege);
                acumulaprecios = acumulaprecios + vege.calculaPrecioTotal();
                Toast toast1 = Toast.makeText(getApplicationContext(), "Pizza añadida", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        botonTropical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tropi.setNombre(textoTropical.getText().toString());
                tropi.setTipo(tipoMasa);
                tropi.setTamaño(tamañoPizza);
                arrayPizzas.add(tropi);
                acumulaprecios = acumulaprecios + tropi.calculaPrecioTotal();
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
                        cantidadPerciosPizzaIndividual();
                        imprimirPrecio();
                    }else if(posispinner==1){
                        cantidadPerciosPizzaMediana();
                        imprimirPrecio();
                    }else{
                        cantidadPerciosPizzaFamiliar();
                        imprimirPrecio();
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
                        cantidadPerciosPizzaIndividual();
                        imprimirPrecio();
                    } else if (posispinner == 1) {
                        cantidadPerciosPizzaIndividual();
                        imprimirPrecio();
                    } else {
                        cantidadPerciosPizzaIndividual();
                        imprimirPrecio();
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
                        cantidadPerciosPizzaIndividual();
                        imprimirPrecio();
                    } else if (posispinner == 1) {
                        cantidadPerciosPizzaIndividual();
                        imprimirPrecio();
                    } else {
                        cantidadPerciosPizzaIndividual();
                        imprimirPrecio();
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
                        cantidadPerciosPizzaIndividual();
                        imprimirPrecio();
                    } else if (posispinner == 1) {
                        cantidadPerciosPizzaIndividual();
                        imprimirPrecio();
                    } else {
                        cantidadPerciosPizzaIndividual();
                        imprimirPrecio();
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
                        cantidadPerciosPizzaIndividual();
                        imprimirPrecio();
                    } else if (posispinner == 1) {
                        cantidadPerciosPizzaIndividual();
                        imprimirPrecio();
                    } else {
                        cantidadPerciosPizzaIndividual();
                        imprimirPrecio();
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
                arrayBebidas.add(coca);
                acumulaprecios=acumulaprecios+Double.parseDouble(preciococacola.getText().toString());
                Toast toast1 = Toast.makeText(getApplicationContext(), "Bebida añadida", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        botonLimon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayBebidas.add(limon);
                acumulaprecios=acumulaprecios+Double.parseDouble(preciolimon.getText().toString());
                Toast toast1 = Toast.makeText(getApplicationContext(), "Bebida añadida", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        botonNaranja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayBebidas.add(nara);
                acumulaprecios=acumulaprecios+Double.parseDouble(precionaranja.getText().toString());
                Toast toast1 = Toast.makeText(getApplicationContext(), "Bebida añadida", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        botonNestea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayBebidas.add(nes);
                acumulaprecios=acumulaprecios+Double.parseDouble(precionestea.getText().toString());
                Toast toast1 = Toast.makeText(getApplicationContext(), "Bebida añadida", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        botonCerveza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayBebidas.add(cerve);
                acumulaprecios=acumulaprecios+Double.parseDouble(preciocerveza.getText().toString());
                Toast toast1 = Toast.makeText(getApplicationContext(), "Bebida añadida", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        botonAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayBebidas.add(agua);
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
                    cantidadesBebidas();
                    preciococacola.setText("" + coca.calculaPrecioTotal());
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
                    cantidadesBebidas();
                    preciolimon.setText("" + limon.calculaPrecioTotal());
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
                    cantidadesBebidas();
                    precionaranja.setText("" + nara.calculaPrecioTotal());
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
                    cantidadesBebidas();
                    precionestea.setText("" + nes.calculaPrecioTotal());
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
                    cantidadesBebidas();
                    preciocerveza.setText("" + cerve.calculaPrecioTotal());
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
                    cantidadesBebidas();
                    precioagua.setText("" + agua.calculaPrecioTotal());
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
        i.putExtra("cliente", cli);
        i.putExtra("ap",arrayPizzas);
        i.putExtra("ab",arrayBebidas);
        i.putExtra("acumprecios",acumulaprecios);
        startActivity(i);
    }

    public void cantidadPerciosPizzaIndividual(){
        carbo.setCantidad(Integer.parseInt(cantCar.getText().toString()));
        barba.setCantidad(Integer.parseInt(cantBar.getText().toString()));
        quesos.setCantidad(Integer.parseInt(cantQue.getText().toString()));
        vege.setCantidad(Integer.parseInt(cantVe.getText().toString()));
        tropi.setCantidad(Integer.parseInt(cantTro.getText().toString()));

        carbo.setPrecio(8.5);
        barba.setPrecio(9.99);
        quesos.setPrecio(9.5);
        vege.setPrecio(8.5);
        tropi.setPrecio(10.5);
    }

    public void cantidadPerciosPizzaMediana(){
        carbo.setCantidad(Integer.parseInt(cantCar.getText().toString()));
        barba.setCantidad(Integer.parseInt(cantBar.getText().toString()));
        quesos.setCantidad(Integer.parseInt(cantQue.getText().toString()));
        vege.setCantidad(Integer.parseInt(cantVe.getText().toString()));
        tropi.setCantidad(Integer.parseInt(cantTro.getText().toString()));

        carbo.setPrecio(13.5);
        barba.setPrecio(12.99);
        quesos.setPrecio(13.5);
        vege.setPrecio(14.5);
        tropi.setPrecio(15.5);
    }

    public void cantidadPerciosPizzaFamiliar(){
        carbo.setCantidad(Integer.parseInt(cantCar.getText().toString()));
        barba.setCantidad(Integer.parseInt(cantBar.getText().toString()));
        quesos.setCantidad(Integer.parseInt(cantQue.getText().toString()));
        vege.setCantidad(Integer.parseInt(cantVe.getText().toString()));
        tropi.setCantidad(Integer.parseInt(cantTro.getText().toString()));

        carbo.setPrecio(16.5);
        barba.setPrecio(18.99);
        quesos.setPrecio(16.5);
        vege.setPrecio(17.5);
        tropi.setPrecio(18.5);

    }

    public void imprimirPrecio(){
        String carboDosdecimales=df.format(carbo.calculaPrecioTotal());
        preciocarbo.setText("" + carboDosdecimales);

        String barbaDosdecimales=df.format(barba.calculaPrecioTotal());
        preciobar.setText("" + barbaDosdecimales);

        String quesosDosdecimales=df.format(quesos.calculaPrecioTotal());
        precioque.setText("" + quesosDosdecimales);

        String vegeDosdecimales=df.format(vege.calculaPrecioTotal());
        preciove.setText("" + vegeDosdecimales);

        String tropiDosdecimales=df.format(tropi.calculaPrecioTotal());
        preciotro.setText("" + tropiDosdecimales);
    }

    public void cantidadesBebidas(){
        coca.setCantidad(Integer.parseInt(cantCo.getText().toString()));
        limon.setCantidad(Integer.parseInt(cantLi.getText().toString()));
        nara.setCantidad(Integer.parseInt(cantNa.getText().toString()));
        nes.setCantidad(Integer.parseInt(cantNes.getText().toString()));
        cerve.setCantidad(Integer.parseInt(cantCer.getText().toString()));
        agua.setCantidad(Integer.parseInt(cantAgua.getText().toString()));
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
