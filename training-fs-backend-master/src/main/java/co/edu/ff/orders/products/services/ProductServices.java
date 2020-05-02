package co.edu.ff.orders.products.services;

import co.edu.ff.orders.products.domain.*;
import co.edu.ff.orders.products.execptions.ProductAlreadyDoesExistsException;
import co.edu.ff.orders.products.execptions.ProductException;
import co.edu.ff.orders.products.repositories.ProductRepository;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServices {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServices(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    public ProductOperation createProduct(ProductOperationRequest productOperationRequest){
        try{
            return productRepository.insertOne(productOperationRequest);
        }catch (ProductException e){
            return ProductOperationFailure.of(e);
        }
    }

    public ProductOperation findById(Long ID){
        //Optional<Producto> producto =
        return productRepository.findById(ID);
        /*if(producto.isPresent()){
            return ProductOperationSuccess.of(producto.get());
        } else {
            ProductAlreadyDoesExistsException productException = ProductAlreadyDoesExistsException.of(producto.get());
            return ProductOperationFailure.of(productException);
        }*/
    }

    public List<Producto> findAll(){
        try {
            return productRepository.findAll();

        }catch (Exception e){
            throw e;
        }
    }

    public ProductOperation updateOne(Long id, ProductOperationRequest productOperationRequest){
        //Optional<Producto> list = productRepository.findById(id);
        return productRepository.updateOne(id, productOperationRequest);
/*
        if (list.isPresent()){

        }else{
            ProductAlreadyDoesExistsException productException = ProductAlreadyDoesExistsException.of(list.get());
            return ProductOperationFailure.of(productException);
        }*/

    }

    public ProductOperation deleteOne(Long ID){
        ProductOperation producto = productRepository.findById(ID);
        productRepository.deleteOne(ID);
        if(producto.isValid()){
            productRepository.deleteOne(ID);
            return ProductOperationSuccess.of(producto.value());
        } else {
            ProductAlreadyDoesExistsException productException = ProductAlreadyDoesExistsException.of(producto.value().getID());
            return ProductOperationFailure.of(productException);
        }

    }
}
