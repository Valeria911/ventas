package com.duoc.ventas.controladores;

import java.util.List;
import java.util.Optional;

import com.duoc.ventas.servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.duoc.ventas.modelos.Producto;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    //Obtener todos los productos
    @GetMapping
    public List<Producto> obtenerTodosLosProductos() {
        return productoService.obtenerProductos();
    }

    //obtener producto por id
    @GetMapping("/{id}")
    public Optional<Producto> obtenerProductoPorId(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id);
    }

    //guardar producto
    @PostMapping
    public Producto guardarProducto(@RequestBody Producto producto){
        return productoService.guardarProducto(producto);
    }

    //modificar
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto){
        return productoService.actualizarProducto(id, producto);
    }

    //eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        return productoService.eliminarProducto(id);
    }

}
