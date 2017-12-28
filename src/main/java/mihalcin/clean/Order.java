package mihalcin.clean;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Order implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private OrderNumber number;

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
}
