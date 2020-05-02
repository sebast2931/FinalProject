package co.edu.ff.orders.products.repositories;

import co.edu.ff.orders.products.domain.ProductOperation;
import co.edu.ff.orders.products.domain.ProductOperationRequest;
import co.edu.ff.orders.products.domain.Producto;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository {

    ProductOperation insertOne(ProductOperationRequest operationRequest);
    ProductOperation findById(Long id);
    List<Producto> findAll();
    ProductOperation updateOne(Long id,ProductOperationRequest operationRequest);
    ProductOperation deleteOne(Long id);

}
