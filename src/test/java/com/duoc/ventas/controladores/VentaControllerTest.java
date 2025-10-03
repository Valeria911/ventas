package com.duoc.ventas.controladores;

import com.duoc.ventas.modelos.VentaProducto;
import com.duoc.ventas.servicios.VentaService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(VentaController.class)
public class VentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VentaService ventaService;

    @Test
    public void obtenerTodasLasVentasTest() throws Exception{
        //Arrange
        VentaProducto venta1 = new VentaProducto();
        venta1.setIdProducto(1L);
        venta1.setFechaVenta(LocalDate.parse("2025-09-19"));
        venta1.setCantidad(2);
        venta1.setPrecioUnitario(5000);
        VentaProducto venta2 = new VentaProducto();
        venta2.setIdProducto(2L);
        venta2.setFechaVenta(LocalDate.parse("2025-03-16"));
        venta2.setCantidad(5);
        venta2.setPrecioUnitario(3500);

        List<VentaProducto> ventas = Arrays.asList(venta1, venta2);
        when(ventaService.obtenerVentas()).thenReturn(ventas);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/ventas"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.aMapWithSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.ventaProductoList[0].cantidad", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.ventaProductoList[1].cantidad", Matchers.is(5)));
    }
}
