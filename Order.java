//Author: Melody Lee, Lim Wan Yoke
//Module: Order Management
//System: Online Shopping System
//Group: DFT1G12
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Order {
    private String orderId;
    private Customer customer;
    private List<OrderItem> items;
    private LocalDateTime orderDate;
    private String status;
    private Payment payment;
    public static final double TAX_RATE = 0.10;

    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_PROCESSING = "PROCESSING";
    public static final String STATUS_COMPLETED = "COMPLETED";
    public static final String STATUS_CANCELLED = "CANCELLED";
    public static final String STATUS_RETURNED = "RETURNED";
    public static final String STATUS_REFUNDED = "REFUNDED";

    public Order(Customer customer, List<OrderItem> items) {
        this.orderId = UUID.randomUUID().toString();
        this.customer = customer;
        this.items = new ArrayList<>(items);
        this.orderDate = LocalDateTime.now();
        this.status = STATUS_PENDING;
    }

    public void checkout() {
        double total = calculateTotal();
        
        this.payment = new Payment(this.orderId, total);
        processPayment();
    }

    private double calculateTotal() {
        return items.stream().mapToDouble(OrderItem::getTotal).sum();
    }

    private void processPayment() {
        if (payment.processPayment(customer)) {
            this.status = STATUS_PROCESSING;
            updateInventory();
        }
    }

    private void updateInventory() {
        for (OrderItem item : items) {
            item.getProduct().updateStock(-item.getQuantity());
        }
    }

    public void returnOrder() {
        if (status.equals(STATUS_PROCESSING)) {
            this.status = STATUS_RETURNED;
            refundPayment();
        }
    }

    public void cancelOrder() {
        if (status.equals(STATUS_PROCESSING)) {
            this.status = STATUS_CANCELLED;
            refundPayment();
        }
    }

    private void refundPayment() {
        this.status = STATUS_REFUNDED;
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

    public String getStatus() {
        return status;
    }

    public String getOrderDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return orderDate.format(formatter);
    }

    public double getTotalPriceWithTax() {
        return calculateTotal() * (1 + TAX_RATE);
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=======================================================\n");
        sb.append("                     ORDER DETAILS                     \n");
        sb.append("=======================================================\n");
        sb.append(String.format("Order ID      : %s\n", orderId));
        sb.append(String.format("Customer Name : %s\n", customer.getName()));
        sb.append(String.format("Order Date    : %s\n", getOrderDate()));
        sb.append(String.format("Status        : %s\n", status));
        sb.append("-------------------------------------------------------\n");
        sb.append("Items:\n");
        sb.append(String.format("%-20s %-10s %-10s %-10s\n", "Product", "Price", "Qty", "Total"));
        sb.append("-------------------------------------------------------\n");
        for (OrderItem item : items) {
            sb.append(String.format("%-20s RM%-9.2f %-10d RM%-10.2f\n",
                    item.getProduct().getProductName(),
                    item.getProduct().getPrice(),
                    item.getQuantity(),
                    item.getTotal()));
        }
        sb.append("-------------------------------------------------------\n");
        sb.append(String.format("Subtotal      : RM%.2f\n", getTotalPrice()));
        sb.append(String.format("Tax (10%%)     : RM%.2f\n", getTotalPrice() * TAX_RATE));
        sb.append(String.format("Total (w/Tax) : RM%.2f\n", getTotalPriceWithTax()));
        sb.append("=======================================================\n");

        return sb.toString();
    }

    public String getOrderSummary() {
        return String.format("\nOrder ID: %s \nCustomer: %s  \nTotal: $%.2f  \nStatus: %s",
                orderId, customer.getName(), getTotalPrice(), status);
    }

    public String getOrderHistoryDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("=======================================================\n");
        sb.append("                     ORDER HISTORY                     \n");
        sb.append("=======================================================\n");
        sb.append(String.format("Order ID      : %s\n", orderId));
        sb.append(String.format("Customer Name : %s\n", customer.getName()));
        sb.append(String.format("Address       : %s\n", customer.getAddress()));
        sb.append(String.format("Phone         : %s\n", customer.getPhone()));
        sb.append(String.format("Order Date    : %s\n", getOrderDate()));
        sb.append(String.format("Status        : %s\n", status));
        sb.append("-------------------------------------------------------\n");
        sb.append("Items:\n");
        sb.append(String.format("%-20s %-10s %-10s %-10s\n", "Product", "Price", "Qty", "Total"));
        sb.append("-------------------------------------------------------\n");
        for (OrderItem item : items) {
            sb.append(String.format("%-20s RM%-9.2f %-10d RM%-10.2f\n",
                    item.getProduct().getProductName(),
                    item.getProduct().getPrice(),
                    item.getQuantity(),
                    item.getTotal()));
        }
        sb.append("-------------------------------------------------------\n");
        sb.append(String.format("Subtotal      : RM%.2f\n", getTotalPrice()));
        sb.append(String.format("Tax (10%%)     : RM%.2f\n", getTotalPrice() * TAX_RATE));
        sb.append(String.format("Total (w/Tax) : RM%.2f\n", getTotalPriceWithTax()));
        sb.append("=======================================================\n");

        return sb.toString();
    }

    public void completeOrder() {
        if (!status.equals(STATUS_PENDING)) {
            System.out.println("Order cannot be completed. Current status: " + status);
            return;
        }
    
        // Assume admin verified payment is already done
        double total = calculateTotal();
        this.payment = new Payment(this.orderId, total); // optional: record payment for tracking
        this.status = STATUS_COMPLETED;
        updateInventory();
    
        System.out.println("Order completed successfully by admin. Order ID: " + orderId);
    }
    
}
