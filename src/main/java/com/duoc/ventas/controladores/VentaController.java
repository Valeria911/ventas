package com.duoc.ventas.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return ventaService.obtenerTodasLasVentas();
    }

    //ver ganancia del dia
    @GetMapping("/ganancias/dia/{fecha}")
    public double obtenerGananciasDiarias(@PathVariable String fecha) {
        return ventaService.obtenerGananciasDiarias(java.time.LocalDate.parse(fecha));  
    }

    //ver ganancia del mes
    @GetMapping("/ganancias/mes/{mes}/{anio}")
    public double obtenerGananciasMensuales(@PathVariable int mes, int anio) {
        return ventaService.obtenerGananciasMensuales(mes, anio);   
    }

    //ver ganancia del año
    @GetMapping("/ganancias/anio/{anio}")
    public double obtenerGananciasAnuales(@PathVariable int anio) {
        return ventaService.obtenerGananciasAnuales(anio);  
    }
}
