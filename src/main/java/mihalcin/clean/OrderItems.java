package mihalcin.clean;

import static java.math.BigDecimal.ZERO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingList;

// when I want to add behaviour to List
// rich domain model
// single responsibility pattern
public class OrderItems extends ForwardingList<OrderItem> implements Serializable {

    private final List<OrderItem> delegate;

    private OrderItems(List<OrderItem> delegate) {
        this.delegate = delegate;
    }

    public static OrderItems of(List<OrderItem> delegate) {
        Preconditions.checkNotNull(delegate);
        return new OrderItems(delegate);
    }

    @Override
    protected List<OrderItem> delegate() {
        return delegate;
    }


    public Integer getQuantity() {
        return delegate.stream()
                .map(OrderItem::getQuantity)
                .reduce(0, Integer::sum);
    }

    public BigDecimal getValue() {
        return delegate.stream()
                .map(OrderItem::getValue)
                .reduce(ZERO, BigDecimal::add);
    }
}
