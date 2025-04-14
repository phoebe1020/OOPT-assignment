import java.time.LocalDateTime;
import java.util.*;

public class Order {
    private String orderId;
    private Customer customer;
    private List<OrderItem> items;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private Payment payment;

    public Order(Customer customer, List<OrderItem> items) {
        this.orderId = UUID.randomUUID().toString();
        this.customer = customer;
        this.items = new ArrayList<>(items);
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
    }

    public void checkout() {
        double total = calculateTotal();
        this.payment = new Payment(this.orderId, total);
        processPayment();
    }

    private double calculateTotal() {
        return items.stream()
                .mapToDouble(OrderItem::getTotal)
                .sum();
    }

    private void processPayment() {
        if (payment.processPayment()) {
            this.status = OrderStatus.PROCESSING;
            updateInventory();
        }
    }

    private void updateInventory() {
        for (OrderItem item : items) {
            item.getProduct().updateStock(-item.getQuantity());
        }
    }

    public void returnOrder() {
        if (status == OrderStatus.PROCESSING) {
            this.status = OrderStatus.RETURNED;
            refundPayment();
        }
    }

    public void cancelOrder() {
        if (status == OrderStatus.PROCESSING) {
            this.status = OrderStatus.CANCELLED;
            refundPayment();
        }
    }

    private void refundPayment() {
        this.status = OrderStatus.REFUNDED;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}
