package co.edu.ff.orders.products.execptions;

public abstract class ProductException extends RuntimeException {
    public ProductException(String message){
        super(message);
    }
}
