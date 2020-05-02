package co.edu.ff.orders.products.domain;

public interface ProductOperation {
    Producto value();
    String errorMessage();
    Boolean isValid();
}
