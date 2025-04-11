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
    public String getname() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getSeller() {
        return seller;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setSeller(String seller) {
        this.seller = seller;
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
        // Simulate refund process
        this.status = OrderStatus.REFUNDED;
    }

    // Getters
    public String getOrderId() {
        return orderId;
    }

    public List<OrderItem> getItems() {
        return items;
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
        Scanner scanner = new Scanner(System.in);
        clearScreen();
        logo();

        // Create products
        Product crybaby = new Product("P1", "Crybaby", "Always fucking cry's baby", 1500.00, 10, "Popmart");
        Product labubu = new Product("P2", "Labubu", "Just a moster..", 999.99, 20, "Popmart");
        Product cinnamoroll = new Product("P3", "Cinnamoroll", "Cute little baby", 499.99, 15, "Sanrio");
        Product hellokitty = new Product("P4", "Hello Kitty", "A cat like human", 299.99, 30, "Sanrio");

        System.out.println("Available Products:");
        System.out.printf("%-10s %-20s %-10s\n", "Product ID", "Name", "Price (USD)");
        System.out.printf("%-10s %-20s %-10s\n", crybaby.getProductId(), crybaby.getname(), crybaby.getPrice());
        System.out.printf("%-10s %-20s %-10s\n", labubu.getProductId(), labubu.getname(), labubu.getPrice());
        System.out.printf("%-10s %-20s %-10s\n", cinnamoroll.getProductId(), cinnamoroll.getname(), cinnamoroll.getPrice());
        System.out.printf("%-10s %-20s %-10s\n", hellokitty.getProductId(), hellokitty.getname(), hellokitty.getPrice());
        System.out.println("5. Exit");
        System.out.print("Select a product to add to cart (1-5): ");
        int choice = scanner.nextInt();
        while (choice != 5) {
            switch (choice) {
                case 1:
                    System.out.println("You selected Crybaby.");
                    break;
                case 2:
                    System.out.println("You selected Labubu.");
                    break;
                case 3:
                    System.out.println("You selected Cinnamoroll.");
                    break;
                case 4:
                    System.out.println("You selected Hello Kitty.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.print("Select a product to add to cart (1-5): ");
            choice = scanner.nextInt();
        }
        System.out.println("Exiting the product selection.");


        // Create customer
        Customer customer = new Customer("C1", "John Doe", "john@example.com", "555-1234", "123 Main St");

        // Add items to cart
        System.out.println("Adding items to cart...");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter quantity for Crybaby: ");
        int crybabyQuantity = input.nextInt();
        customer.addToCart(crybaby, crybabyQuantity);
        System.out.print("Enter quantity for Labubu: ");
        int labubuQuantity = input.nextInt();
        customer.addToCart(labubu, labubuQuantity);

        // Place order
        customer.placeOrder();
    }

}
