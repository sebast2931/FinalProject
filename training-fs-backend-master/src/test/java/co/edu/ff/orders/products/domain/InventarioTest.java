package co.edu.ff.orders.products.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class InventarioTest {

    @Test
    @DisplayName("no deberia crear Invetario")

    void notPassInventario(){

        Integer D2 = -1;

        assertAll(
                ()-> assertThrows(NullPointerException.class,() -> Inventario.of(null)),
                ()-> assertThrows(IllegalArgumentException.class,() -> Inventario.of(D2))
        );
    }

    @TestFactory
    @DisplayName("Deberia crear el inventario")
    Stream<DynamicTest> Validar() {
        return Stream.of(
                1, 2, 3
        )
                .map(data -> {
                    String testName = String.format("es valido para el inventario %s", data);
                    Executable executable = () -> {
                        ThrowingSupplier<Inventario> inventarioThrowingSupplier = () -> Inventario.of(data);
                        assertAll(
                                () -> assertDoesNotThrow(inventarioThrowingSupplier),
                                () -> assertNotNull(inventarioThrowingSupplier.get())
                        );
                    };
                    return DynamicTest.dynamicTest(testName, executable);
                });
    }
}