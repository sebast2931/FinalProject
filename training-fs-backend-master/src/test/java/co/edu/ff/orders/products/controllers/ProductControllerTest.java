package co.edu.ff.orders.products.controllers;

import co.edu.ff.orders.products.domain.*;
import co.edu.ff.orders.products.execptions.ProductAlreadyDoesExistsException;
import co.edu.ff.orders.products.services.ProductServices;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @MockBean
    private ProductServices services;

    @Test
    @DisplayName("estas son las pruebas findById")
    void findById() throws Exception {
        // organizar....
        Producto producto = Producto.of(1L,
                Nombre.of("sadsad"),
                Descripcion.of("asdasd"),
                PrecioBase.of(new BigDecimal(14544)),
                TasaImpuestos.of(new BigDecimal(1)),
                Estado.BORRADO,
                Inventario.of(18));

        when(services.findById((1L)))
                .thenReturn(ProductOperationSuccess.of(producto));

        // act
        // assert
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.get("/api/v1/products/1");
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
    @Test
    @DisplayName("Estas son las pruebas findByIdFail")
    void findByIdFail() throws Exception {
        when(services.findById(any())).thenReturn(ProductOperationFailure.of(ProductAlreadyDoesExistsException.of(null)));
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.post("/api/v1/products/s");

        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("estas son las pruebas findAll")
    void findAll() throws Exception {
        // organizar....
        List<Producto> producto = services.findAll();

        when(services.findAll())
                .thenReturn(producto);

        // act
        // assert
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.get("/api/v1/products/");
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("Estas son las pruebas findAllFail")
    void findAllFail() throws Exception {
        when(services.findAll()).thenReturn(isNull());
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.post("/api/v1/products/");

        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("estas son las pruebas createSuccess")
    void createSuccess() throws Exception {
        // organizar....
        ProductOperationRequest productOperationRequest = ProductOperationRequest.of(
                Nombre.of("sadsad"),
                Descripcion.of("asdasd"),
                PrecioBase.of(new BigDecimal(14544)),
                TasaImpuestos.of(new BigDecimal(1)),
                Estado.BORRADO,
                Inventario.of(18));

        Producto producto = Producto.of(1L,
                Nombre.of("sadsad"),
                Descripcion.of("asdasd"),
                PrecioBase.of(new BigDecimal(14544)),
                TasaImpuestos.of(new BigDecimal(1)),
                Estado.BORRADO,
                Inventario.of(18));

        when(services.createProduct(productOperationRequest))
                .thenReturn(ProductOperationSuccess.of(producto));

        // act
        // assert
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.post("/api/v1/products/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(producto));
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("Estas son las pruebas insertOneFailure")
    void insertOneFailure() throws Exception {
        when(services.createProduct(any())).thenReturn(ProductOperationFailure.of(ProductAlreadyDoesExistsException.of(null)));
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.post("/api/v1/products");

        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("estas son las pruebas updateSuccess")
    void updateSuccess() throws Exception {
        // organizar....
        ProductOperationRequest productOperationRequest = ProductOperationRequest.of(
                Nombre.of("sadsad"),
                Descripcion.of("asdasd"),
                PrecioBase.of(new BigDecimal(14544)),
                TasaImpuestos.of(new BigDecimal(1)),
                Estado.BORRADO,
                Inventario.of(18));

        Producto producto = Producto.of(1L,
                productOperationRequest.getNombre(),
                productOperationRequest.getDescripcion(),
                productOperationRequest.getPreciobase(),
                productOperationRequest.getTasa_de_impuestos(),
                productOperationRequest.getEstado(),
                productOperationRequest.getInventario());

        when(services.updateOne(anyLong(),productOperationRequest))
                .thenReturn(ProductOperationSuccess.of(producto));

        // act
        // assert
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.put("/api/v1/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(producto));
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    @DisplayName("estas son las pruebas updateFail")
    void updateFail() throws Exception {
        // organizar....

        when(services.updateOne(anyLong(),any()))
                .thenReturn(ProductOperationFailure.of(ProductAlreadyDoesExistsException.of(null)));
        // act
        // assert
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.put("/api/v1/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("");
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("estas son las pruebas deleteSuccess")
    void deleteSuccess() throws Exception {
        Producto producto = Producto.of(1L,
                Nombre.of("sadsad"),
                Descripcion.of("asdasd"),
                PrecioBase.of(new BigDecimal(14544)),
                TasaImpuestos.of(new BigDecimal(1)),
                Estado.BORRADO,
                Inventario.of(18));

        when(services.deleteOne(anyLong())).thenReturn(ProductOperationSuccess.of(producto));

        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.delete("/api/v1/products/1");
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("estas son las pruebas deleteFail")
    void deleteFail() throws Exception {
        when(services.deleteOne(anyLong())).thenReturn(ProductOperationFailure.of(ProductAlreadyDoesExistsException.of(1L)));

        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.delete("/api/v1/products/");
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

}