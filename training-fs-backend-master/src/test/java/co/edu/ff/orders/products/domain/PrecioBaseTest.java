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

class PrecioBaseTest {


    @Test
    @DisplayName("no deberia crear una precio base")

    void notPassBasePrice(){

        BigDecimal D2 = new BigDecimal(-1);

        assertAll(
                ()-> assertThrows(NullPointerException.class,() -> PrecioBase.of(null)),
                ()-> assertThrows(IllegalArgumentException.class,() -> PrecioBase.of(D2))
        );
    }

    @TestFactory
    @DisplayName("Deberia crear el precio base")
    Stream<DynamicTest> Validar(){
        return Stream.of(
                new BigDecimal( 1)
        )
                .map(preciobase ->{
                    String testName = String.format("es valido para precio base %s", preciobase);
                    Executable executable = () -> {
                        ThrowingSupplier<PrecioBase> precioBaseThrowingSupplier = () -> PrecioBase.of(preciobase);
                        assertAll(
                                () -> assertDoesNotThrow(precioBaseThrowingSupplier),
                                () -> assertNotNull(precioBaseThrowingSupplier.get())
                        );
                    };
                    return DynamicTest.dynamicTest(testName, executable);
                });
    }


}