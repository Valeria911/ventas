package com.duoc.ventas.repositorios;

import com.duoc.ventas.modelos.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    public void guardarProductoTest(){
        //Arrange
        Producto producto = new Producto();
        producto.setNombre("Detergente");

        //Act
        Producto resultado = productoRepository.save(producto);

        //Assert
        assertNotNull(resultado.getId());
        assertEquals("Detergente", resultado.getNombre());
    }
}
