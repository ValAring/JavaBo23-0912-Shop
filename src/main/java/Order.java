import lombok.With;

import java.time.ZonedDateTime;
import java.util.List;


public record Order(
        String id,
        ZonedDateTime orderTime,
        List<Product> products,
        @With
        OrderStatus orderStatus //PROCESSING, IN_DELIVERY, COMPLETED
) {
    public Order(String id, List<Product> products) {

        this(id, ZonedDateTime.now(), products, OrderStatus.PROCESSING);
    }
}
