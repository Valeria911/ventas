package com.duoc.ventas.servicios;

import com.duoc.ventas.modelos.Producto;
import com.duoc.ventas.repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    //obtener todos los productos
    public List<Producto> obtenerProductos(){
        return productoRepository.findAll();
    }

    //obtener producto por id
    public Optional<Producto> obtenerProductoPorId(Long id){
        return productoRepository.findById(id);
    }

    //guardar producto
    public Producto guardarProducto(Producto producto){
        return  productoRepository.save(producto);
    }

    //modificar producto
    public Producto actualizarProducto(Long id, Producto producto){
        return productoRepository.findById(id).map(producto1 -> {
            producto1.setNombre(producto.getNombre());
            producto1.setClasificacion(producto.getClasificacion());
            producto1.setPrecio(producto.getPrecio());
            producto1.setStock(producto.getStock());
            return ResponseEntity.ok(productoRepository.save(producto1));
        }).orElse(ResponseEntity.notFound().build()).getBody();
    }

    //eliminar producto
    public ResponseEntity<Void> eliminarProducto(Long id){
        if (productoRepository.existsById(id)){
            productoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
