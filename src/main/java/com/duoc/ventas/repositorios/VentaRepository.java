package com.duoc.ventas.repositorios;

import com.duoc.ventas.modelos.VentaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VentaRepository extends JpaRepository<VentaProducto, Long> {

    List<VentaProducto> findByFechaVenta(LocalDate fechaVenta);

    List<VentaProducto> findByFechaVentaBetween(LocalDate inicio, LocalDate fin);

}
