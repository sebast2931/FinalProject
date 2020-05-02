package co.edu.ff.orders.products.domain;

import co.edu.ff.orders.common.Preconditions;
import co.edu.ff.orders.products.serialization.IntegerSerializable;
import lombok.Value;


@Value(staticConstructor = "of")
public class Inventario implements IntegerSerializable {

    private final Integer value;


    public Inventario(Integer value) {
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value > 0);
        this.value = value;
    }

    @Override
    public Integer valueOf() {
        return value;
    }
}
