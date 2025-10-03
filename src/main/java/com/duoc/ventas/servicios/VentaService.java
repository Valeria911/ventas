package com.duoc.ventas.servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.duoc.ventas.repositorios.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.duoc.ventas.modelos.VentaProducto;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventas;

    //obtener todas las ventas
    public List<VentaProducto> obtenerVentas(){
        return ventas.findAll();
    }

    //obtener una venta por id
    public Optional<VentaProducto> obtenerPorId(Long id){
        return ventas.findById(id);
    }

    //crear una venta
    public VentaProducto guardarVenta(VentaProducto ventaProducto){
        return ventas.save(ventaProducto);
    }

    //actualizar una venta
    public VentaProducto actualizarVenta(Long id, VentaProducto ventaProducto){
        return ventas.findById(id).map(ventaProducto1 -> {
            ventaProducto1.setIdProducto(ventaProducto.getIdProducto());
            ventaProducto1.setCantidad(ventaProducto.getCantidad());
            ventaProducto1.setPrecioUnitario(ventaProducto.getPrecioUnitario());
            ventaProducto1.setFechaVenta(ventaProducto.getFechaVenta());
            return ResponseEntity.ok(ventas.save(ventaProducto1));
        }).orElse(ResponseEntity.notFound().build()).getBody();
    }

    //eliminar una venta
    public ResponseEntity<Void> eliminarVenta(Long id){
        if (ventas.existsById(id)){
            ventas.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //obtener ganancias diarias
    public double obtenerGananciasDiarias(LocalDate fecha) {
        List<VentaProducto> ventasDelDia = ventas.findByFechaVenta(fecha);
        return ventasDelDia.stream()
                .mapToDouble(v -> v.getCantidad() * v.getPrecioUnitario())
                .sum();
    }

    //obtener ganancias mensuales
    public double obtenerGananciasPorMes(int anio, int mes) {
        LocalDate inicio = LocalDate.of(anio, mes, 1);
        LocalDate fin = inicio.withDayOfMonth(inicio.lengthOfMonth());
        List<VentaProducto> ventasMes = ventas.findByFechaVentaBetween(inicio, fin);
        return ventasMes.stream()
                .mapToDouble(v -> v.getCantidad() * v.getPrecioUnitario())
                .sum();
    }

    //obtener ganancias anuales
    public double obtenerGananciasPorAnio(int anio) {
        LocalDate inicio = LocalDate.of(anio, 1, 1);
        LocalDate fin = LocalDate.of(anio, 12, 31);
        List<VentaProducto> ventasAnio = ventas.findByFechaVentaBetween(inicio, fin);
        return ventasAnio.stream()
                .mapToDouble(v -> v.getCantidad() * v.getPrecioUnitario())
                .sum();
    }
}
