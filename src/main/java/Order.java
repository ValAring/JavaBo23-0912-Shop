import lombok.With;

import java.util.List;


public record Order(
        String id,
        List<Product> products,
        @With
        OrderStatus orderStatus//PROCESSING, IN_DELIVERY, COMPLETED
) {
    public Order(String id, List<Product> products) {
        this(id, products, OrderStatus.PROCESSING);
    }
}
