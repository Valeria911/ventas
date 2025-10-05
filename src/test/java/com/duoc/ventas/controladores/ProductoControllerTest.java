package com.duoc.ventas.controladores;

import com.duoc.ventas.modelos.Producto;
import com.duoc.ventas.servicios.ProductoService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoService productoService;

    @Test
    public void obtenerTodosLosProductosTest() throws Exception{
        //Arrange
        //Creación de productos
        Producto producto1 = new Producto();
        producto1.setNombre("Detergente");
        producto1.setId(1L);
        Producto producto2 = new Producto();
        producto2.setNombre("Libreta");
        producto2.setId(2L);

        List<Producto> productos = Arrays.asList(producto1, producto2);
        when(productoService.obtenerProductos()).thenReturn(productos);

        //Act y Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/productos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.aMapWithSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.productoList[0].nombre", Matchers.is("Detergente")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.productoList[1].nombre", Matchers.is("Libreta")));
    }
}
