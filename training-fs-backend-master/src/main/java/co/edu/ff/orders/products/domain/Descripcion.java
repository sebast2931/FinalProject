package co.edu.ff.orders.products.domain;

import co.edu.ff.orders.common.Preconditions;
import co.edu.ff.orders.products.serialization.StringSerializable;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;


@Value(staticConstructor = "of")
public class Descripcion implements StringSerializable {

    private final String value;

    public Descripcion(String value){
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(StringUtils.isNoneBlank(value));
        Preconditions.checkArgument(value.length() <= 280);
        this.value = value;
    }

    @Override
    public String valueOf() {
        return value;
    }
}
