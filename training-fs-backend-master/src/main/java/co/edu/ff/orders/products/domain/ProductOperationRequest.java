package co.edu.ff.orders.products.domain;

import co.edu.ff.orders.common.Preconditions;
import lombok.Value;

@Value(staticConstructor = "of")
public class ProductOperationRequest {

    //Long ID;
    Nombre nombre;
    Descripcion descripcion;
    PrecioBase preciobase;
    TasaImpuestos tasa_de_impuestos;
    Estado estado;
    Inventario inventario;

    public ProductOperationRequest( Nombre nombre, Descripcion descripcion, PrecioBase preciobase, TasaImpuestos tasa_de_impuestos, Estado estado, Inventario inventario) {

        Preconditions.checkNotNull(nombre);
        Preconditions.checkNotNull(descripcion);
        Preconditions.checkNotNull(preciobase);
        Preconditions.checkNotNull(tasa_de_impuestos);
        Preconditions.checkNotNull(estado);
        Preconditions.checkNotNull(inventario);



        //this.ID = ID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.preciobase = preciobase;
        this.tasa_de_impuestos = tasa_de_impuestos;
        this.estado = estado;
        this.inventario = inventario;
    }
}
