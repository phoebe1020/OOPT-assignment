import java.util.*;
import java.util.stream.Collectors;

public class ShoppingCart {
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
    
    public void viewCart() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
    
        System.out.printf("%-20s %-10s %-10s %-10s\n", "Product", "Price", "Qty", "Total");
        double grandTotal = 0.0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double total = product.getPrice() * quantity;
            grandTotal += total;
    
            System.out.printf("%-20s %-10.2f %-10d %-10.2f\n",
                    product.getProductName(), product.getPrice(), quantity, total);
        }
        System.out.println("--------------------------------------------------");
        System.out.printf("Total Amount: RM %.2f\n", grandTotal);
    }
}