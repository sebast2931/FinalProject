package co.edu.ff.orders.products.controllers;


import co.edu.ff.orders.products.domain.ProductOperation;
import co.edu.ff.orders.products.domain.ProductOperationRequest;
import co.edu.ff.orders.products.domain.Producto;
import co.edu.ff.orders.products.services.ProductServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServices services;

    @PostMapping

    public ResponseEntity<ProductOperation> create(@RequestBody ProductOperationRequest product){
        System.out.println("PRUEBA"+ product);
        ProductOperationRequest producto = ProductOperationRequest.of(

                product.getNombre(),
                product.getDescripcion(),
                product.getPreciobase(),
                product.getTasa_de_impuestos(),
                product.getEstado(),
                product.getInventario());

        ProductOperation productOperation = services.createProduct(producto);
        if(productOperation.isValid()) {
            return ResponseEntity.ok(productOperation);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productOperation);
        }


        /*
        ProductOperation productOperation = services.createProduct(product);
        if(productOperation.isValid()){
            return ResponseEntity.ok(productOperation);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productOperation);
        }*/
    }


    @GetMapping
    public List<Producto> findAll(){
        return services.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductId(@PathVariable Long id){

        ProductOperation producto = services.findById(id);
        if (producto.isValid()){
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){

        ProductOperation producto = services.deleteOne(id);
        if (producto.isValid()){
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductOperationRequest productOperationRequest) {
        ProductOperation producto = services.updateOne(id, productOperationRequest);

        if (producto.isValid()){
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(producto);
    }

}
