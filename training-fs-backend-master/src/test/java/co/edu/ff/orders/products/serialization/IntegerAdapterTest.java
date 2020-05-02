package co.edu.ff.orders.products.serialization;

import co.edu.ff.orders.products.domain.Inventario;
import co.edu.ff.orders.products.domain.PrecioBase;
import co.edu.ff.orders.products.domain.TasaImpuestos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IntegerAdapterTest {

    static Gson gson;

    @BeforeAll
    static void setUp() {
        gson = new GsonBuilder()
                .registerTypeAdapter(Inventario.class, new IntegerAdapter<>(Inventario::of))
                .create();
    }

    @Test
    void deserialize() {

        Inventario inventario = Inventario.of(3);


        String serializeActual = gson.toJson(inventario);


        Inventario deserializeActual = gson.fromJson(serializeActual, Inventario.class);


        assertEquals(deserializeActual.valueOf(), inventario.valueOf());

    }

    @Test
    void serialize() {
        Inventario inventario = Inventario.of(3);

        String actual = gson.toJson(inventario);

        String expected = String.format("%s", inventario.getValue());
        assertEquals(actual, expected);

    }

}