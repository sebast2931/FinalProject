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

class TasaImpuestosTest {
    @Test
    @DisplayName("no deberia crear la tasa de impuestos")

    void notPassTasaImpuestos(){

        BigDecimal D1 = new BigDecimal(10.0);
        BigDecimal D2 = new BigDecimal(-2);

        assertAll(
                ()-> assertThrows(NullPointerException.class,() -> TasaImpuestos.of(null)),
                ()-> assertThrows(IllegalArgumentException.class,() -> TasaImpuestos.of(D1)),
                ()-> assertThrows(IllegalArgumentException.class,() -> TasaImpuestos.of(D2))
        );
    }

    @TestFactory
    @DisplayName("Deberia crear la tasa de impuesto")
    Stream<DynamicTest> Validar() {
        return Stream.of(
               new BigDecimal(0.1)
        )
                .map(data -> {
                    String testName = String.format("es valido para el nombre %s", data);
                    Executable executable = () -> {
                        ThrowingSupplier<TasaImpuestos> tasaImpuestosThrowingSupplier = () -> TasaImpuestos.of(data);
                        assertAll(
                                () -> assertDoesNotThrow(tasaImpuestosThrowingSupplier),
                                () -> assertNotNull(tasaImpuestosThrowingSupplier.get())
                        );
                    };
                    return DynamicTest.dynamicTest(testName, executable);
                });
    }

}