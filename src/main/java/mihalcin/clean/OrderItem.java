package mihalcin.clean;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Embeddable;

import com.google.common.base.MoreObjects;

// this is value object as well, not an entity!
@Embeddable
public class OrderItem implements Serializable {

    private String product;

    private Integer quantity;

    private BigDecimal value;

    private OrderItem(String product, Integer quantity, BigDecimal value) {
        this.product = product;
        this.quantity = quantity;
        this.value = value;
    }

    public static OrderItem of(String product, Integer quantity, BigDecimal value) {
        checkNotNull(product);
        checkNotNull(quantity);
        checkArgument(quantity > 0);
        checkNotNull(value);
        return new OrderItem(product, quantity, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(product, orderItem.product) &&
                Objects.equals(quantity, orderItem.quantity) &&
                Objects.equals(value, orderItem.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity, value);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("product", product)
                .add("quantity", quantity)
                .add("value", value)
                .toString();
    }

    public String getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getValue() {
        return value;
    }
}
