package com.duoc.ventas.servicios;

import com.duoc.ventas.modelos.VentaProducto;
import com.duoc.ventas.repositorios.VentaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VentaServiceTest {

    @InjectMocks
    private VentaService ventaService;

    @Mock
    private VentaRepository ventaRepository;

    @Test
    public void guardarVentaTest(){
        //Arrange
        VentaProducto venta = new VentaProducto();
        venta.setIdProducto(1L);
        venta.setCantidad(4);

        when(ventaRepository.save(any())).thenReturn(venta);

        //Act
        VentaProducto resultado = ventaService.guardarVenta(venta);

        //Assert
        assertEquals(1L, resultado.getIdProducto());
        assertEquals(4, resultado.getCantidad());
    }
}
