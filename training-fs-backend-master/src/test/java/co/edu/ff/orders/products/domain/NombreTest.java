package co.edu.ff.orders.products.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NombreTest {

    @Test
    @DisplayName("no deberia crear el nombre")

    void notPassNombre(){

        String D1 = "";
        String D2 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        assertAll(
                ()-> assertThrows(NullPointerException.class,() -> Nombre.of(null)),
                ()-> assertThrows(IllegalArgumentException.class,() -> Nombre.of(D1)),
                ()-> assertThrows(IllegalArgumentException.class,() -> Nombre.of(D2))
        );
    }

    @TestFactory
    @DisplayName("Deberia crear el nombre")
    Stream<DynamicTest> Validar() {
        return Stream.of(
                "Sebastian",
                "Julian"
        )
                .map(data -> {
                    String testName = String.format("es valido para el nombre %s", data);
                    Executable executable = () -> {
                        ThrowingSupplier<Nombre> nombreThrowingSupplier = () -> Nombre.of(data);
                        assertAll(
                                () -> assertDoesNotThrow(nombreThrowingSupplier),
                                () -> assertNotNull(nombreThrowingSupplier.get())
                        );
                    };
                    return DynamicTest.dynamicTest(testName, executable);
                });
    }

}