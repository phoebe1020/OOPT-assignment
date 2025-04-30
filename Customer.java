import java.util.*;

// Customer class
public class Customer extends User {
    private ShoppingCart cart;
    private List<Order> orderHistory;

    public Customer( String userId, String name, String email, String phone, String address) {
        super(userId, name, email, phone, address);
        this.cart = new ShoppingCart();
        this.orderHistory = new ArrayList<>();
    }

    public void addToCart(Product product, int quantity) {
        cart.addItem(product, quantity);
    }

    // public void placeOrder() {
    //     Order order = new Order(this, cart.getItems());
    //     order.checkout();
    //     orderHistory.add(order);
    //     cart.clear();
    // }

    public void placeOrder() {
        if (cart.getItems().isEmpty()) {
            System.out.println("Your cart is empty. Please add items to your cart before placing an order.");
            return;
        }
        Order order = new Order(this, cart.getItems());
        //order.checkout();
        orderHistory.add(order);
        OnlineShoppingSystem.orderList.add(order); // Add to global order list
        order.completeOrder(); // Complete the order (process payment and update status)
        cart.clear();
        System.out.println("Order placed successfully! Order ID: " + order.getOrderId());
    }

    // Getters

    public ShoppingCart getCart() {
        return cart;
    }
    public List<Order> getOrderHistory() {
        return orderHistory;
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

//     public String toString() {
// 		return super.toString()+String.format("Customer [cart=%s, orderHistory=%s]", cart, orderHistory);
				
// }


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

