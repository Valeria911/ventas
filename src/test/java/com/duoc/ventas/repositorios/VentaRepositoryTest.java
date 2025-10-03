package com.duoc.ventas.repositorios;

import com.duoc.ventas.modelos.VentaProducto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VentaRepositoryTest {

    @Autowired
    private VentaRepository ventaRepository;

    @Test
    public void guardarVentaTest(){
        //Arrange
        VentaProducto venta = new VentaProducto();
        venta.setIdProducto(1L);
        venta.setPrecioUnitario(5000);

        //Act
        VentaProducto resultado = ventaRepository.save(venta);

        //Assert
        assertNotNull(resultado.getId());
        assertEquals(5000, resultado.getPrecioUnitario());
    }
}
