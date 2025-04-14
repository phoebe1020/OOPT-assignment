import java.util.*;

public class Inventory {
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
