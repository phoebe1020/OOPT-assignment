//Author: Siow Wern Qin, Melody Lee
//Module: User Management
//System: Online Shopping System
//Group: DFT1G12
import java.util.*;

// Customer class
public class Customer extends User {
    private String password;
    private ShoppingCart cart;
    private List<Order> orderHistory;

<<<<<<< HEAD
    public Customer(String password, String userId, String name, String email, String phone, String address) {
=======
    public Customer(String userId, String name, String email, String phone, String address) {
>>>>>>> 8fd5ec4e624c6d6d9edf848b2de33c2ccd0a460d
        super(userId, name, email, phone, address);
        this.password = password;
        this.cart = new ShoppingCart();
        this.orderHistory = new ArrayList<>();
    }

    public void addToCart(Product product, int quantity) {
        cart.addItem(product, quantity);
    }

    public void placeOrder() {
        if (cart.getItems().isEmpty()) {
            System.out.println("Your cart is empty. Please add items to your cart before placing an order.");
            return;
        }

        Order order = new Order(this, cart.getItems());
        orderHistory.add(order);
        OnlineShoppingSystem.orderList.add(order);
        cart.clear();
        System.out.println("Order placed successfully! Order ID: " + order.getOrderId());
    }

    public void viewOrderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("No orders found in your history.");
            return;
        }

        System.out.println("\n--- Order History ---");
        for (Order order : orderHistory) {
            System.out.println(order.getOrderSummary());
        }
    }

    // Getters
    public String getPassword() {
        return password;
    }
    
    public ShoppingCart getCart() {
        return cart;
    }
<<<<<<< HEAD
    
=======

>>>>>>> 8fd5ec4e624c6d6d9edf848b2de33c2ccd0a460d
    public List<Order> getOrderHistory() {
        return orderHistory;
    }

<<<<<<< HEAD
    public void setPassword(String password) {
        this.password = password;
    }
   
=======
>>>>>>> 8fd5ec4e624c6d6d9edf848b2de33c2ccd0a460d
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
        super.setUserId(customerId);
    }

    public void updateName(String name) {
        super.setName(name);
    }

    public void updateEmail(String email) {
        super.setEmail(email);
    }

    public void updatePhone(String phone) {
        super.setPhone(phone);
    }

    public void updateAddress(String address) {
        super.setAddress(address);
    }

    public void viewCart() {
        cart.viewCart();
    }

    public void clearCart() {
        cart.clear();
    }

    public ShoppingCart getShoppingCart() {
        return this.cart;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n--- Order History ---\n");
        if (orderHistory.isEmpty()) {
            sb.append("No orders found.\n");
        } else {
            for (Order order : orderHistory) {
                sb.append(order.getOrderSummary()).append("\n");
            }
        }
        return sb.toString();
    }

    
}
