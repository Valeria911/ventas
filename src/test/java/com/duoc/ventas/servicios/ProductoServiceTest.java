package com.duoc.ventas.servicios;

import com.duoc.ventas.modelos.Producto;
import com.duoc.ventas.repositorios.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @InjectMocks
    private ProductoService productoService;

    @Mock
    private ProductoRepository productoRepository;

    @Test
    public void guardarProductoTest(){
        //Arrange
        Producto producto = new Producto();
        producto.setNombre("Libreta");

        when(productoRepository.save(any())).thenReturn(producto);

        //Act
        Producto resultado = productoService.guardarProducto(producto);

        //Assert
        assertEquals("Libreta", resultado.getNombre());
    }
}
