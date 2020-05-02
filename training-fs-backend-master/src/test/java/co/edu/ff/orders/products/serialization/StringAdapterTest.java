package co.edu.ff.orders.products.serialization;

import co.edu.ff.orders.products.domain.Descripcion;
import co.edu.ff.orders.products.domain.Nombre;
import co.edu.ff.orders.products.domain.PrecioBase;
import co.edu.ff.orders.products.domain.TasaImpuestos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringAdapterTest {

   /* @Test
    void deserialize() {
    }

    @Test
    void serialize() {
    }*/

    static Gson gson;

    @BeforeAll
    static void setUp() {
        gson = new GsonBuilder()
                .registerTypeAdapter(Nombre.class, new StringAdapter<>(Nombre::of))
                .registerTypeAdapter(Descripcion.class, new StringAdapter<>(Descripcion::of))
                .create();
    }

    @Test
    void deserialize() {

        Nombre nombre = Nombre.of("Sebastian");
        Descripcion descripcion = Descripcion.of("Descripcion");

        String serializeActual1 = gson.toJson(nombre);
        String serializeActual2 = gson.toJson(descripcion);

        Nombre deserializeActual1 = gson.fromJson(serializeActual1, Nombre.class);
        Descripcion deserializeactual2 = gson.fromJson(serializeActual2, Descripcion.class);

        assertEquals(deserializeActual1.valueOf(), nombre.valueOf());
        assertEquals(deserializeactual2.valueOf(), descripcion.valueOf());
    }

    @Test
    void serialize() {
        Nombre nombre = Nombre.of("Sebastian");
        Descripcion descripcion = Descripcion.of("Descripcion");

        String actual1 = gson.toJson(nombre);
        String actual2 = gson.toJson(descripcion);

        String expected1 = String.format("\"%s\"", nombre.getValue());
        assertEquals(actual1, expected1);
        String expected2 = String.format("\"%s\"", descripcion.getValue());
        assertEquals(actual2, expected2);
    }
}