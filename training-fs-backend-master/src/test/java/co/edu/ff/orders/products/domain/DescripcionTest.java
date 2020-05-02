package co.edu.ff.orders.products.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import org.junit.jupiter.api.function.ThrowingSupplier;

import org.junit.jupiter.api.function.Executable;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DescripcionTest {

    @Test
    @DisplayName("no deberia crear una descripcion")

    void notPass(){
        String D1 = null;
        String D2 = "";
        String D3 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        assertAll(
                ()-> assertThrows(NullPointerException.class,() -> Descripcion.of(D1)),
                ()-> assertThrows(IllegalArgumentException.class,() -> Descripcion.of(D2)),
                ()-> assertThrows(IllegalArgumentException.class,() -> Descripcion.of(D3))
        );
    }

    @TestFactory
    @DisplayName("Deberia crear la descripcion")
    Stream<DynamicTest> Validar(){
        return Stream.of(
                "Descripcion",
                "esto es una descripcion"
        )
                .map(descripcion ->{
                    String testName = String.format("es valido para descripcion %s", descripcion);
                    Executable executable = () -> {
                        ThrowingSupplier<Descripcion> descripcionThrowingSupplier = () -> Descripcion.of(descripcion);
                        assertAll(
                                () -> assertDoesNotThrow(descripcionThrowingSupplier),
                                () -> assertNotNull(descripcionThrowingSupplier.get())
                        );
                    };
                    return DynamicTest.dynamicTest(testName, executable);
                });
    }


}