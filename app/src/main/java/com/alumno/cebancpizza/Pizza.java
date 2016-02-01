package com.alumno.cebancpizza;

import java.io.Serializable;

/**
 * Created by adminportatil on 27/01/2016.
 */
public class Pizza implements Serializable {

    private String nombre;
    private String tipo;
    private String tamaño;
    private int cantidad;
    private double precio;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTamaño() {
        return tamaño;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public double calculaPrecioTotal(){
        double precioTotal=Double.parseDouble(String.valueOf(cantidad))*precio;
        return precioTotal;
    }
}
