package co.edu.ff.orders.products.serialization;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.function.Function;

public class BigDecimalAdapter<IN extends BigDecimalSerializable> implements GsonAdapter<IN>{

    private final Function<BigDecimal, IN> factory;

    public BigDecimalAdapter(Function<BigDecimal, IN> factory) {
        this.factory = factory;
    }


    @Override
    public IN deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        BigDecimal value = jsonElement.getAsBigDecimal();
        return factory.apply(value);
    }

    @Override
    public JsonElement serialize(IN t, Type type, JsonSerializationContext jsonSerializationContext) {
        BigDecimal value = t.valueOf();
        return new JsonPrimitive(value);
    }
}
