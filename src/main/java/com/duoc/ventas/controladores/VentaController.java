package com.duoc.ventas.controladores;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.duoc.ventas.modelos.VentaProducto;
import com.duoc.ventas.servicios.VentaService;


@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    //ver todas las ventas
    @GetMapping
    public CollectionModel<EntityModel<VentaProducto>> obtenerTodasLasVentas(){
        List<VentaProducto> ventas = ventaService.obtenerVentas();

        List<EntityModel<VentaProducto>> ventaResources = ventas.stream()
                .map(ventaProducto -> EntityModel.of(ventaProducto,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obtenerVentaPorId(ventaProducto.getId())).withSelfRel()
                )).collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obtenerTodasLasVentas());
        CollectionModel<EntityModel<VentaProducto>> resources = CollectionModel.of(ventaResources, linkTo.withRel("ventas"));

        return resources;
    }
    /*@GetMapping
    public List<VentaProducto> obtenerTodasLasVentas() {
        return ventaService.obtenerVentas();
    }*/

    //ver venta por id
    @GetMapping("/{id}")
    public EntityModel<VentaProducto> obtenerVentaPorId(@PathVariable Long id){
        Optional<VentaProducto> venta = ventaService.obtenerPorId(id);

        if (venta.isPresent()) {
            return EntityModel.of(venta.get(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obtenerVentaPorId(id)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obtenerTodasLasVentas()).withRel("todas-las-ventas"));
        } else {
            throw new VentaNotFoundException("Venta no encontrada con id: " + id);
        }
    }

   /* @GetMapping("/{id}")
    public Optional<VentaProducto> obtenerVentaPorId(@PathVariable Long id){
        return  ventaService.obtenerPorId(id);
    }*/

    //guardar venta
    @PostMapping
    public EntityModel<VentaProducto> guardarVenta(@RequestBody VentaProducto venta) {
        VentaProducto ventaGuardada = ventaService.guardarVenta(venta);
        return EntityModel.of(ventaGuardada,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obtenerVentaPorId(ventaGuardada.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obtenerTodasLasVentas()).withRel("todas-las-ventas"));
    }

    /*public VentaProducto guardarVenta(@RequestBody VentaProducto ventaProducto){
        return ventaService.guardarVenta(ventaProducto);
    }*/

    //modificar
    @PutMapping("/{id}")
    public EntityModel<VentaProducto> actualizarVenta(@PathVariable Long id, @RequestBody VentaProducto venta) {
        VentaProducto ventaActualizada = ventaService.actualizarVenta(id, venta);
        return EntityModel.of(ventaActualizada,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obtenerVentaPorId(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obtenerTodasLasVentas()).withRel("todas-las-ventas"));

    }

   /* public ResponseEntity<VentaProducto> actualizarVenta(@PathVariable Long id, @RequestBody VentaProducto ventaProducto){
        return ventaService.actualizarVenta(id, ventaProducto);
    }*/

    //eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id){
        return ventaService.eliminarVenta(id);
    }

    //ver ganancia del dia
    @GetMapping("/ganancias/dia/{fecha}")
    public double obtenerGananciasDiarias(@PathVariable String fecha) {
        return ventaService.obtenerGananciasDiarias(java.time.LocalDate.parse(fecha));  
    }

    //ver ganancia del mes
    @GetMapping("/ganancias/mes/{mes}/{anio}")
    public double obtenerGananciasMensuales(@PathVariable int mes, @PathVariable int anio) {
        return ventaService.obtenerGananciasPorMes(mes, anio);
    }

    //ver ganancia del año
    @GetMapping("/ganancias/anio/{anio}")
    public double obtenerGananciasAnuales(@PathVariable int anio) {
        return ventaService.obtenerGananciasPorAnio(anio);
    }
}
