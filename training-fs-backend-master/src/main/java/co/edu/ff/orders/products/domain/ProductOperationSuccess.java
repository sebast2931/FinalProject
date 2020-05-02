package co.edu.ff.orders.products.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class ProductOperationSuccess implements ProductOperation {

    Producto producto;

    @Override
    public Producto value() {
        return producto;
    }

    @Override
    public String errorMessage() {
        return null;
    }

    @Override
    public Boolean isValid() {
        return true;
    }
}
