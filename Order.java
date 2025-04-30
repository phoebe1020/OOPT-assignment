import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public String getCustomerName() {
        return customer.getName();
    }

    public double getTotalPrice() {
        return calculateTotal();
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getOrderDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return orderDate.format(formatter);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Customer: ").append(customer.getName()).append("\n");
        sb.append("Order Date: ").append(getOrderDate()).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Items:\n");
        for (OrderItem item : items) {
            sb.append("  - ").append(item.getProduct().getProductName())
              .append(" (Quantity: ").append(item.getQuantity())
              .append(", Total: $").append(item.getTotal()).append(")\n");
        }
        sb.append("Total Price: $").append(getTotalPrice()).append("\n");
        return sb.toString();
    }

    public String getOrderSummary() {
        return String.format("Order ID: %s | Customer: %s | Total: $%.2f | Status: %s",
                orderId, customer.getName(), getTotalPrice(), status);
    }

    public void completeOrder() {
        if (status != OrderStatus.PENDING) {
            System.out.println("Order cannot be completed. Current status: " + status);
            return;
        }
    
        double total = calculateTotal();
        this.payment = new Payment(this.orderId, total);
    
        if (payment.processPayment()) {
            this.status = OrderStatus.COMPLETED;
            updateInventory();
            System.out.println("Order completed successfully! Order ID: " + orderId);
        } else {
            System.out.println("Payment failed. Order not completed.");
        }
}
}

