package co.edu.ff.orders.products.serialization;

import co.edu.ff.orders.products.domain.PrecioBase;
import co.edu.ff.orders.products.domain.TasaImpuestos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BigDecimalAdapterTest {


    static Gson gson;

    @BeforeAll
    static void setUp() {
        gson = new GsonBuilder()
                .registerTypeAdapter(PrecioBase.class, new BigDecimalAdapter<>(PrecioBase::of))
                .registerTypeAdapter(TasaImpuestos.class, new BigDecimalAdapter<>(TasaImpuestos::of))
                .create();
    }

    @Test
    void deserialize() {

        PrecioBase precioBase = PrecioBase.of(new BigDecimal(14));
        TasaImpuestos tasaImpuestos = TasaImpuestos.of(new BigDecimal(1));

        String serializeActual1 = gson.toJson(precioBase);
        String serializeActual2 = gson.toJson(tasaImpuestos);

        PrecioBase deserializeActual1 = gson.fromJson(serializeActual1, PrecioBase.class);
        TasaImpuestos deserializeactual2 = gson.fromJson(serializeActual2, TasaImpuestos.class);

        assertEquals(deserializeActual1.valueOf(), precioBase.valueOf());
        assertEquals(deserializeactual2.valueOf(), tasaImpuestos.valueOf());
    }

    @Test
    void serialize() {
        PrecioBase precioBase = PrecioBase.of(new BigDecimal(14));
        TasaImpuestos tasaImpuestos = TasaImpuestos.of(new BigDecimal(1));

        String actual1 = gson.toJson(precioBase);
        String actual2 = gson.toJson(tasaImpuestos);

        String expected1 = String.format("%s", precioBase.getValue());
        assertEquals(actual1, expected1);
        String expected2 = String.format("%s", tasaImpuestos.getValue());
        assertEquals(actual2, expected2);
    }
}