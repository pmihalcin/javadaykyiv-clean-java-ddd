package mihalcin.clean;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.math.BigDecimal.ZERO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

@Entity
public class Order implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private OrderNumber number;

    // never allow collections to be null
    @ElementCollection
    private List<OrderItem> items = Lists.newLinkedList();

    @ElementCollection
    private List<OrderItem> discountedItems = Lists.newLinkedList();

    private Order(OrderNumber number) {
        this.number = number;
    }

    // the advantage is that factory code is polymorphic, constructor code is not
    public static Order of(OrderNumber number) {
        checkNotNull(number);
        return new Order(number);
    }

    // the goal is to minimize mutability
    public Long getId() {
        return id;
    }

    public OrderNumber getNumber() {
        return number;
    }

    // be pragmatic instead of being dogmatic!
    // don't fight the framework

    // we have to return immutable list!
    public List<OrderItem> getItems() {
        return ImmutableList.copyOf(items);
    }

    public void addItem(OrderItem item) {
        if (!items.contains(item)) {
            items.add(item);
        }
    }

    public Integer getTotalQuantity() {
        return items.stream()
                .map(OrderItem::getQuantity)
                .reduce(0, Integer::sum);
    }

    public BigDecimal getTotalValue() {
        return items.stream()
                .map(OrderItem::getValue)
                .reduce(ZERO, BigDecimal::add);
    }


    public Integer getTotalDiscountedQuantity() {
        return discountedItems.stream()
                .map(OrderItem::getQuantity)
                .reduce(0, Integer::sum);
    }

    public BigDecimal getDiscountedTotalValue() {
        return discountedItems.stream()
                .map(OrderItem::getValue)
                .reduce(ZERO, BigDecimal::add);
    }
}
