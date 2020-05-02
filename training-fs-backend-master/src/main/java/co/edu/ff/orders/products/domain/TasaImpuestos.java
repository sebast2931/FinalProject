package co.edu.ff.orders.products.domain;

import co.edu.ff.orders.common.Preconditions;
import co.edu.ff.orders.products.serialization.BigDecimalSerializable;
import lombok.Value;

import java.math.BigDecimal;

@Value(staticConstructor = "of")
public class TasaImpuestos implements BigDecimalSerializable {

    private final BigDecimal value;

    public TasaImpuestos(BigDecimal value) {
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value.intValue()<=1 && value.intValue()>=0);
        this.value = value;
    }

    @Override
    public BigDecimal valueOf() {
        return value;
    }
}
