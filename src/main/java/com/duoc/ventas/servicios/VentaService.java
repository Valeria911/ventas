package com.duoc.ventas.servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.duoc.ventas.modelos.VentaProducto;

@Service
public class VentaService {
    private List<VentaProducto> ventas = new ArrayList<>();  
    private Long nextId = 1L;
    
    public VentaService() {
        // Datos de prueba
        ventas.add(new VentaProducto(1L, 101L, 2, 50.0, java.time.LocalDate.of(2024, 6, 1)));
        ventas.add(new VentaProducto(2L, 102L, 1, 30.0, java.time.LocalDate.of(2024, 6, 1)));
        ventas.add(new VentaProducto(3L, 103L, 3, 20.0, java.time.LocalDate.of(2024, 6, 2)));
        ventas.add(new VentaProducto(4L, 104L, 5, 15.0, java.time.LocalDate.of(2024, 5, 15)));
        ventas.add(new VentaProducto(5L, 105L, 4, 25.0, java.time.LocalDate.of(2023, 12, 20)));
        ventas.add(new VentaProducto(6L, 106L, 2, 20.0, java.time.LocalDate.of(2023, 12, 20)));
        ventas.add(new VentaProducto(7L, 107L, 1, 100.0, java.time.LocalDate.of(2024, 1, 10)));
        ventas.add(new VentaProducto(8L, 108L, 6, 10.0, java.time.LocalDate.of(2024, 1, 11)));
    }
    //Registrar venta
    public VentaProducto registrarVenta(Long idProducto, int cantidad, double precioUnitario, LocalDate fechaVenta) {
        if (cantidad <= 0 || precioUnitario < 0) {
            throw new IllegalArgumentException("Cantidad y precio unitario deben ser mayores que cero");
        }
        VentaProducto venta = new VentaProducto(nextId++, idProducto, cantidad, precioUnitario, fechaVenta);
        ventas.add(venta);
        return venta;
    }

    //Obtener todas las ventas
    public List<VentaProducto> obtenerTodasLasVentas() {
        return ventas;  
    }

    //obtener ganancias diarias
    public double obtenerGananciasDiarias(LocalDate fecha) {
        return ventas.stream()
                .filter(v -> v.getFechaVenta().equals(fecha))
                .mapToDouble(v -> v.getCantidad() * v.getPrecioUnitario())
                .sum(); 
    }

    //obtener ganancias mensuales
    public double obtenerGananciasMensuales(int mes, int anio) {
        return ventas.stream()
                .filter(v -> v.getFechaVenta().getMonthValue() == mes && v.getFechaVenta().getYear() == anio)
                .mapToDouble(v -> v.getCantidad() * v.getPrecioUnitario())
                .sum(); 
    }

    //obtener ganancias anuales
    public double obtenerGananciasAnuales(int anio) {
        return ventas.stream()
                .filter(v -> v.getFechaVenta().getYear() == anio)
                .mapToDouble(v -> v.getCantidad() * v.getPrecioUnitario())
                .sum();
    }


}
