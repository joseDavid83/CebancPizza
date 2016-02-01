package com.alumno.cebancpizza;

import java.io.Serializable;

/**
 * Created by adminportatil on 25/01/2016.
 */
public class Cliente implements Serializable {
    private String nombre;
    private String direccion;
    private String telefono;

    public Cliente(String n,String d, String t){
        nombre=n;
        direccion=d;
        telefono=t;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }
}
