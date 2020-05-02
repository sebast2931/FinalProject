package co.edu.ff.orders.products.domain;

import co.edu.ff.orders.products.execptions.ProductException;
import lombok.Value;

@Value(staticConstructor = "of")
public class ProductOperationFailure implements ProductOperation {

    ProductException exception;

    @Override
    public Producto value() {
        return null;
    }

    @Override
    public String errorMessage() {
        return exception.getMessage();
    }

    @Override
    public Boolean isValid() {
        return false;
    }
}
