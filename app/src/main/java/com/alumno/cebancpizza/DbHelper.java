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
    String sqlCreate="create table clientes(idcliente integer primary key, nombre text, direccion text, telefono integer)";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}