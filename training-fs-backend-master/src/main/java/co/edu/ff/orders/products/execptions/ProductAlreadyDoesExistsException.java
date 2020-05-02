package co.edu.ff.orders.products.execptions;

import co.edu.ff.orders.products.domain.Producto;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value(staticConstructor = "of")
public class ProductAlreadyDoesExistsException extends ProductException {

   // Producto producto;

    Long id;

    public ProductAlreadyDoesExistsException(Long id) {
        super(String.format("Product %s no exists", id));
        this.id = id;
    }
}
