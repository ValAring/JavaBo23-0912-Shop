import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// ein Kommentar als änderung
public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

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

        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.PROCESSING);

        return orderRepo.addOrder(newOrder);
    }

    public List<Order> listOrdersWithState(OrderStatus thisState){
        List<Order> allOrders = orderRepo.getOrders();
        return allOrders.stream().filter(order -> order.orderStatus() == thisState).toList();
    }
}
