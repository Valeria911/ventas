package com.duoc.ventas.modelos;

public class Producto {
            
    private String id;
    private String nombre;
    private String clasificacion; //comida, higiene, accesorios, etc
    private double precio;
    private int stock;

    public Producto(String id, String nombre, String clasificacion, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.clasificacion = clasificacion;
        this.precio = precio;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getClasificacion() {
        return clasificacion;
    }   

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }   
}
