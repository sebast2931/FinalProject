package co.edu.ff.orders.products.repositories;

import co.edu.ff.orders.products.domain.*;
import co.edu.ff.orders.products.execptions.ProductAlreadyDoesExistsException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.*;

public class SqlProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public SqlProductRepository(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    public final RowMapper<Producto> rowMapper =(resultSet, i)->{
        Long id1 = resultSet.getLong("id");
        Nombre nombre = Nombre.of(resultSet.getString("nombre"));
        Descripcion descripcion = Descripcion.of(resultSet.getString("descripcion"));
        PrecioBase precioBase = PrecioBase.of(resultSet.getBigDecimal("precio_base"));
        TasaImpuestos tasaImpuestos = TasaImpuestos.of(resultSet.getBigDecimal("tasa_de_impuestos"));
        Estado estado = Estado.valueOf(resultSet.getString("estado"));
        Inventario inventario = Inventario.of(resultSet.getInt("inventario"));

        return Producto.of(id1,nombre, descripcion,  precioBase, tasaImpuestos, estado, inventario);
    };

    @Override
    public ProductOperation insertOne(ProductOperationRequest operationRequest) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("nombre", operationRequest.getNombre().getValue());
        parameters.put("descripcion", operationRequest.getDescripcion().getValue());
        parameters.put("precio_base", operationRequest.getPreciobase().getValue());
        parameters.put("tasa_de_impuestos", operationRequest.getTasa_de_impuestos().getValue());
        parameters.put("estado", operationRequest.getEstado());
        parameters.put("inventario", operationRequest.getInventario().getValue());



        Number number = simpleJdbcInsert.executeAndReturnKey(parameters);
        Long id = (number.longValue());
        Producto producto = Producto.of(
                id,
                operationRequest.getNombre(),
                operationRequest.getDescripcion(),
                operationRequest.getPreciobase(),
                operationRequest.getTasa_de_impuestos(),
                operationRequest.getEstado(),
                operationRequest.getInventario()
        );
        return ProductOperationSuccess.of(producto);

    }

    @Override
    public ProductOperation findById(Long id) {
        String SQL = "SELECT  id, nombre, descripcion, precio_base, tasa_de_impuestos, estado, inventario FROM PRODUCTS WHERE id = ?";
        Object[] objects = {id};

        try {
             Producto producto = jdbcTemplate.queryForObject(SQL, objects, rowMapper);
             return ProductOperationSuccess.of(producto);
        }catch (EmptyResultDataAccessException e){
            return ProductOperationFailure.of(ProductAlreadyDoesExistsException.of(id));
        }

    }

    @Override
    public List<Producto> findAll() {
        String SQL = "SELECT id, nombre, descripcion, precio_base, tasa_de_impuestos, estado, inventario FROM PRODUCTS";
       return jdbcTemplate.query(SQL, rowMapper);

    }



    @Override
    public ProductOperation updateOne(Long id, ProductOperationRequest productOperationRequest) {
        String SQL = "UPDATE PRODUCTS SET nombre = ?, descripcion = ?, precio_base = ?, tasa_de_impuestos = ?, estado = ?, inventario = ? WHERE id = ?";
        Object[] objects = {     productOperationRequest.getNombre().getValue(),
                                 productOperationRequest.getDescripcion().getValue(),
                                 productOperationRequest.getPreciobase().getValue(),
                                 productOperationRequest.getTasa_de_impuestos().getValue(),
                                 productOperationRequest.getEstado().toString(),
                                 productOperationRequest.getInventario().getValue(),
                                 id};
        Integer update = jdbcTemplate.update(SQL, objects);
        if (update == 1){
        Producto producto = Producto.of(
                id,
                productOperationRequest.getNombre(),
                productOperationRequest.getDescripcion(),
                productOperationRequest.getPreciobase(),
                productOperationRequest.getTasa_de_impuestos(),
                productOperationRequest.getEstado(),
                productOperationRequest.getInventario());


            //jdbcTemplate.update(SQL, objects);
            return ProductOperationSuccess.of(producto);
        } else  {
            return ProductOperationFailure.of(ProductAlreadyDoesExistsException.of(id));
        }
    }

    @Override
    public ProductOperation deleteOne(Long id) {
        String SQL = "DELETE FROM PRODUCTS WHERE id = ?";
        Object[] objects = {id};
        Producto producto  = findById(id).value();

       Integer delete =  jdbcTemplate.update(SQL, objects);

       if (delete == 1){
           return ProductOperationSuccess.of(producto);
       }else {
           return ProductOperationFailure.of(ProductAlreadyDoesExistsException.of(id));
       }
    }
/*
        String SQL = "DELETE FROM PRODUCTS WHERE ID = ?";
        Object[] objects = {id};
        //Producto producto = findById(id).value();
/*
        Integer getValue = jdbcTemplate.update(SQL, objects);
        if (getValue == 1) {
            return ProductOperationSuccess.of(producto);
        } else {
            return ProductOperationFailure.of(ProductAlreadyDoesExistsException.of(id));
        }*/
/*
        String SQL = "DELETE FROM PRODUCTS WHERE ID = ?";
        Object[] objects = {id};
        jdbcTemplate.update(SQL, objects);
*/

}
