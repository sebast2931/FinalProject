package co.edu.ff.orders.products.repositories;

import co.edu.ff.orders.products.domain.ProductOperation;
import co.edu.ff.orders.products.domain.ProductOperationRequest;
import co.edu.ff.orders.products.domain.ProductOperationSuccess;
import co.edu.ff.orders.products.domain.Producto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryProductRepository implements ProductRepository {

    private final Map<Long, Producto> state = new HashMap<>();
    @Override
    public ProductOperation insertOne(ProductOperationRequest data) {
        Long id = state.size() + 1L;
        Producto producto = Producto.of(
                id,
                data.getNombre(),
                data.getDescripcion(),
                data.getPreciobase(),
                data.getTasa_de_impuestos(),
                data.getEstado(),
                data.getInventario()
        );
        state.put(id,producto);
        return ProductOperationSuccess.of(producto);
    }

    @Override
    public ProductOperation findById(Long id) {
        return null;
    }

    @Override
    public List<Producto> findAll() {
        return null;
    }

    @Override
    public ProductOperation updateOne(Long id, ProductOperationRequest operationRequest) {
        return null;
    }

    @Override
    public ProductOperation deleteOne(Long id) { return null; }
}
