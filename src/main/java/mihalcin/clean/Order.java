package mihalcin.clean;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    public OrderItems getItems() {
        return OrderItems.of(items);
    }

    public OrderItems getDiscountedItems() {
        return OrderItems.of(discountedItems);
    }

    public void addItem(OrderItem item) {
        if (!items.contains(item)) {
            items.add(item);
        }
    }

    public void apply(OrderItemOperation operation) {
        items = items.stream()
                .map(operation)
                .collect(toList());
    }
}
