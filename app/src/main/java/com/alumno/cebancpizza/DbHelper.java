package com.alumno.cebancpizza;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by adminportatil on 25/01/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    private  static final String DB_NAME="CebancPizza.s3db";
    private  static final int DB_SCHEME_VERSION=1;
    String sqlCreateClientes="create table clientes(idcliente integer primary key, nombre text, direccion text, telefono integer)";
    String sqlCreatePizzas="create table pizzas(idpizza integer primary key, nombre text, tipo text, tamano text, cantidad integer, precio double,cliente integer,FOREIGN KEY (cliente) REFERENCES clientes(idcliente))";
    String sqlCreateBebidas="create table bebidas(idbebida integer primary key, nombre text, cantidad integer, precio double,cliente integer, FOREIGN KEY(cliente) REFERENCES clientes(idcliente))";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateClientes);
        db.execSQL(sqlCreatePizzas);
        db.execSQL(sqlCreateBebidas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       /* db.execSQL("DROP TABLE IF EXISTS clientes");
        db.execSQL("DROP TABLE IF EXISTS pizzas");
        db.execSQL("DROP TABLE IF EXISTS bebidas");

        db.execSQL(sqlCreateClientes);
        db.execSQL(sqlCreatePizzas);
        db.execSQL(sqlCreateBebidas);*/
    }
}
