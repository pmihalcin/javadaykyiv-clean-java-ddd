package mihalcin.clean;

import static mihalcin.clean.OrderItemOperations.DISCOUNT;

import org.junit.Test;

public class OrderTest {

    @Test
    public void testOf() {
        Order order = Order.of(OrderNumber.of(1));
        order.getItems().getQuantity();
        order.getDiscountedItems().getQuantity();
        order.apply(DISCOUNT);
    }

}