package com.alumno.cebancpizza;

/**
 * Created by adminportatil on 16/12/2015.
 */
public class Cliente {
    private String nombre;
    private String direccion;
    private int numero;

    public Cliente(String n,String d,int num){
        nombre=n;
        direccion=d;
        numero=num;
    }

    public String getNombre(){
        return nombre;
    }
    public String getDireccion(){
        return direccion;
    }
    public int getNumero(){
        return numero;
    }
}
