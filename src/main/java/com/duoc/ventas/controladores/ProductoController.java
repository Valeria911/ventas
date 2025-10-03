package com.duoc.ventas.controladores;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.duoc.ventas.servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    public CollectionModel<EntityModel<Producto>> obtenerTodosLosProductos(){
       List<Producto> productos = productoService.obtenerProductos();

       List<EntityModel<Producto>> productoResources = productos.stream()
               .map(producto -> EntityModel.of(producto,
                       WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obtenerProductoPorId(producto.getId())).withSelfRel()
               ))
               .collect(Collectors.toList());

       WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obtenerTodosLosProductos());
       CollectionModel<EntityModel<Producto>> resources = CollectionModel.of(productoResources, linkTo.withRel("productos"));

       return resources;
    }

   /* @GetMapping
    public List<Producto> obtenerTodosLosProductos() {
        return productoService.obtenerProductos();
    }*/

    //obtener producto por id
    @GetMapping("/{id}")
    public EntityModel<Producto> obtenerProductoPorId(@PathVariable Long id) {
        Optional<Producto> producto = productoService.obtenerProductoPorId(id);

        if (producto.isPresent()) {
            return EntityModel.of(producto.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obtenerProductoPorId(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obtenerTodosLosProductos()).withRel("todos-los-productos"));
        } else {
            throw new ProductoNotFoundException("Producto no encontrado con id: " + id);

        }
    }

  /*  @GetMapping("/{id}")
    public Optional<Producto> obtenerProductoPorId(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id);
    }*/

    //guardar producto
    @PostMapping
    public EntityModel<Producto> guardarProducto(@RequestBody Producto producto){
        Producto productoGuardado = productoService.guardarProducto(producto);
        return EntityModel.of(productoGuardado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obtenerProductoPorId(productoGuardado.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obtenerTodosLosProductos()).withRel("todos-los-productos"));
    }

    /*@PostMapping
    public Producto guardarProducto(@RequestBody Producto producto){
        return productoService.guardarProducto(producto);
    }*/

    //modificar
    @PutMapping("/{id}")
    public EntityModel<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto){
        Producto productoActualizado = productoService.actualizarProducto(id, producto);
        return EntityModel.of(productoActualizado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obtenerProductoPorId(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obtenerTodosLosProductos()).withRel("todos-los-productos"));
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto){
        return productoService.actualizarProducto(id, producto);
    }*/

    //eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        return productoService.eliminarProducto(id);
    }

}
