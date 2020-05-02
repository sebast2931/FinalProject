package co.edu.ff.orders.products.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class EstadoTest {
    @Test
    @DisplayName("no deberia crear el estado")

    void notPassEstado(){

        String D1 = "";
        String D2 = "Muestra";

        assertAll(
                ()-> assertThrows(NullPointerException.class,() -> Estado.valueOf(null)),
                ()-> assertThrows(IllegalArgumentException.class,() -> Estado.valueOf(D1))
        );
    }

    @TestFactory
    @DisplayName("Deberia crear el nombre")
    Stream<DynamicTest> Validar() {
        return Stream.of(
                "PUBLICADO",
                "BORRADO"
        )
                .map(data -> {
                    String testName = String.format("es valido para el nombre %s", data);
                    Executable executable = () -> {
                        ThrowingSupplier<Estado> estadoThrowingSupplier = () -> Estado.valueOf(data);
                        assertAll(
                                () -> assertDoesNotThrow(estadoThrowingSupplier),
                                () -> assertNotNull(estadoThrowingSupplier.get())
                        );
                    };
                    return DynamicTest.dynamicTest(testName, executable);
                });
    }

}