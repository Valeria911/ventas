package com.duoc.ventas.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String clasificacion; //comida, higiene, accesorios, etc

    @Column
    private double precio;

    @Column
    private int stock;

    public Producto() {
    }

    public Producto(Long id, String nombre, String clasificacion, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.clasificacion = clasificacion;
        this.precio = precio;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
