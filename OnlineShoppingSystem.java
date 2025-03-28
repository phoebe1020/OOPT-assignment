package ass;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

// Customer class
class Customer {
    private String customerId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private ShoppingCart cart;
    private List<Order> orderHistory;

    public Customer(String customerId, String name, String email, String phone, String address) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.cart = new ShoppingCart();
        this.orderHistory = new ArrayList<>();
    }

    public void addToCart(Product product, int quantity) {
        cart.addItem(product, quantity);
    }

    public void placeOrder() {
        Order order = new Order(this, cart.getItems());
        order.checkout();
        orderHistory.add(order);
        cart.clear();
    }

    // Getters
    public String getCustomerId() {
        return customerId;
    }

    public String getAddress() {
        return address;
    }
}

// Product class
class Product {
    private String productId;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String seller;

    public Product(String productId, String name, String description, double price, int stock, String seller) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.seller = seller;
    }

    public void updateStock(int quantity) {
        this.stock += quantity;
    }

    // Getters
    public double getPrice() {
        return price;
    }

    public String getProductId() {
        return productId;
    }

    public int getStock() {
        return stock;
    }
}

// Order Item class
class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.getPrice() * quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}

// Order class
class Order {
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

    // Cancel/refund methods
}

// Payment class
class Payment {
    private String paymentId;
    private String orderId;
    private double amount;
    private PaymentMethod method;
    private TransactionStatus status;
    private LocalDateTime paymentDate;

    public Payment(String orderId, double amount) {
        this.paymentId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.amount = amount;
        this.status = TransactionStatus.PENDING;
        this.paymentDate = LocalDateTime.now();
    }

    public boolean processPayment() {
        // Simulate payment processing
        this.status = TransactionStatus.SUCCESS;
        return true;
    }
}

// Shopping Cart class
class ShoppingCart {
    private Map<Product, Integer> items;

    public ShoppingCart() {
        this.items = new HashMap<>();
    }

    public void addItem(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public List<OrderItem> getItems() {
        return items.entrySet().stream()
                .map(entry -> new OrderItem(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public void clear() {
        items.clear();
    }
}

// Inventory Management
class Inventory {
    private Map<Product, Integer> stock;

    public Inventory() {
        this.stock = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        stock.put(product, quantity);
    }

    public boolean checkStock(Product product, int required) {
        return stock.getOrDefault(product, 0) >= required;
    }
}

// Main class//////////////////////////////////////////////////////////////////////
public class OnlineShoppingSystem {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void logo() {
        System.out.println("    _   _          _  _       _  _   ");
        System.out.println("   | | | (_)_ __  | || |___  | || |  ");
        System.out.println("   | | | | | '_ \\\\| || / __| | || |  ");
        System.out.println("   | |_| | | | | || || \\\\__\\\\|_||_|  ");
        System.out.println("   \\\\___/|_|_| |_||_||_|___/ (_)(_)  ");
    }

    public static void main(String[] args) {
        clearScreen();
        logo();
        // Create products
        Product laptop = new Product("P1", "Laptop", "High-end gaming laptop", 1500.00, 10, "TechCorp");
        Product phone = new Product("P2", "Phone", "Flagship smartphone", 999.99, 20, "MobileInc");

        // Create customer
        Customer customer = new Customer("C1", "John Doe", "john@example.com", "555-1234", "123 Main St");

        // Add items to cart
        customer.addToCart(laptop, 1);
        customer.addToCart(phone, 2);

        // Place order
        customer.placeOrder();
    }

}
