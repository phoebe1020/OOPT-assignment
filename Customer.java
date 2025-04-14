import java.util.*;

// Customer class
public class Customer {
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
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public ShoppingCart getCart() {
        return cart;
    }
    public List<Order> getOrderHistory() {
        return orderHistory;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }
    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }
    public void updateCart(ShoppingCart cart) {
        this.cart = cart;
    }
    public void updateOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }
    public void updateCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public void updateName(String name) {
        this.name = name;
    }
    public void updateEmail(String email) {
        this.email = email;
    }
    public void updatePhone(String phone) {
        this.phone = phone;
    }
    public void updateAddress(String address) {
        this.address = address;
    }

    public void viewCart() {
        cart.viewCart();
    }

    public void clearCart() {
        cart.clear();
    }
}