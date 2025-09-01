package com.duoc.ventas.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.ventas.modelos.Producto;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    
    private List<Producto> productos = new ArrayList<>();   
    

    public ProductoController() {
        //ingreso datos
        productos.add(new Producto("1", "Producto A", "comida", 10.0, 100));
        productos.add(new Producto("2", "Producto B", "higiene", 20.0, 50));
        productos.add(new Producto("3", "Producto C", "accesorios", 15.0, 75));
        productos.add(new Producto("4", "Producto D", "comida", 5.0, 200)); 
        productos.add(new Producto("5", "Producto E", "higiene", 25.0, 30));        
        productos.add(new Producto("6", "Producto F", "accesorios", 30.0, 20)); 
        productos.add(new Producto("7", "Producto G", "comida", 12.0, 150));
        productos.add(new Producto("8", "Producto H", "higiene", 18.0, 60));        
        productos.add(new Producto("9", "Producto I", "comida", 8.0, 80));
    }   

    //Obtener todos los productos
    @GetMapping
    public List<Producto> obtenerTodosLosProductos() {
        return productos;
    }

    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable String id) {
        return productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null); 
    }   
}
