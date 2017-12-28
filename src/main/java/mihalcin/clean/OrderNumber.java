package mihalcin.clean;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

import com.google.common.base.MoreObjects;

// value object has to be immutable
public class OrderNumber implements Serializable, Formattable {

    // internal
    private final Integer value;

    private OrderNumber(Integer value) {
        this.value = value;
    }

    // covariance
    // instead of returning OrderNumber, I can return any subclass of OrderNumber
    // this is advantage of factory method
    public static OrderNumber of(Integer value) {
        checkNotNull(value);
        checkArgument(value > 0);
        return new OrderNumber(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderNumber)) return false;
        OrderNumber that = (OrderNumber) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    // for debugging
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", value)
                .toString();
    }

    // when I want to show string to the user
    // it is also locale sensitive
    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("%d", value);
    }

    // or I could change private final Integer value; to final Integer value;
    // and keep this class and attribute converter in the same package
    // instead of calling toInteger(), I could access .value directly

    // another thought:
    // instead of exposing getter for value, I rename it to toInteger(), so that I don't expose type of my internal field
    public Integer toInteger() {
        return value;
    }
}
