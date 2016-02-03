package com.alumno.cebancpizza;

import java.io.Serializable;

/**
 * Created by adminportatil on 01/02/2016.
 */
public class Bebida implements Serializable {
    private String nombre;
    private int cantidad;
    private double precio;

    public Bebida(String n, double p) {
        nombre = n;
        precio = p;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double calculaPrecioTotal(){
        double preciototal=Double.parseDouble(String.valueOf(cantidad))*precio;
        return preciototal;
    }
}
