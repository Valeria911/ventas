package com.duoc.ventas.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<VentaProducto> obtenerTodasLasVentas() {
        return ventaService.obtenerVentas();
    }

    //ver venta por id
    @GetMapping("/{id}")
    public Optional<VentaProducto> obtenerVentaPorId(@PathVariable Long id){
        return  ventaService.obtenerPorId(id);
    }

    //guardar venta
    @PostMapping
    public VentaProducto guardarVenta(@RequestBody VentaProducto ventaProducto){
        return ventaService.guardarVenta(ventaProducto);
    }

    //modificar
    @PutMapping("/{id}")
    public ResponseEntity<VentaProducto> actualizarVenta(@PathVariable Long id, @RequestBody VentaProducto ventaProducto){
        return ventaService.actualizarVenta(id, ventaProducto);
    }

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
