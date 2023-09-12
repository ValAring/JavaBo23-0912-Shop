import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
// ein Kommentar als änderung
public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public ShopService(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);
            if (productToOrder.isEmpty()) {
                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                return null;
            }
            products.add(productToOrder.get());
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), ZonedDateTime.now(), products, OrderStatus.PROCESSING);

        return orderRepo.addOrder(newOrder);
    }
    public String updateOrder(String id, OrderStatus newOrderState) {
        Order order = orderRepo.getOrderById(id);
        if (order==null) return "ID doesn't exist.";

        orderRepo.removeOrder(id);
        orderRepo.addOrder(order.withOrderStatus(newOrderState));
        return "Order State Changed";
    }
    public List<Order> listOrdersWithState(OrderStatus thisState){
        List<Order> allOrders = orderRepo.getOrders();
        return allOrders.stream().filter(order -> order.orderStatus() == thisState).toList();
    }
}
