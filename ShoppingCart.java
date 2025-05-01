//Author: Lim Wan Yoke, Siow Wern Qin， Lee Meng Yee
//Module: Purchase Management
//System: Online Shopping System
//Group: DFT1G12
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<OrderItem> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {
        // Check if the product already exists in the cart
        for (OrderItem item : items) {
            if (item.getProduct().equals(product)) {
                item.setQuantity(item.getQuantity() + quantity); // Update quantity
                return;
            }
        }
        // If the product is not in the cart, add a new OrderItem
        items.add(new OrderItem(product, quantity));
    }

    public List<OrderItem> getItems() {
        return items;
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

        for (OrderItem item : items) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            double total = product.getPrice() * quantity;
            grandTotal += total;

            System.out.printf("%-20s %-10.2f %-10d %-10.2f\n",
                    product.getProductName(), product.getPrice(), quantity, total);
        }

        double taxAmount = grandTotal * Order.TAX_RATE;
        double totalWithTax = grandTotal + taxAmount;

        System.out.println("--------------------------------------------------");
        System.out.printf("Total Amount: RM %.2f\n", grandTotal);
        System.out.printf("Tax (10%%): RM %.2f\n", taxAmount);
        System.out.printf("Total Amount (with tax): RM %.2f\n", totalWithTax);
    }

    public double getTotalAmountWithTax() {
        double grandTotal = 0.0;

        for (OrderItem item : items) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            grandTotal += product.getPrice() * quantity;
        }

        double taxAmount = grandTotal * Order.TAX_RATE;
        return grandTotal + taxAmount;
    }
}