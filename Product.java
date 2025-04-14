
// Product class definition
public class Product {
    private String productID;
    private String productName;
    private double price;
    private String description;
    private int stock;
    private String seller;

    // Parameterized constructor
    public Product(String productID, String productName, String description, double price, int stock, String seller) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;

    }
    
    // Getter and Setter methods
     public double getPrice() {
        return price;
    }

    public String getProductID() {
        return productID;
    }

     public int getStock() {
        return stock;
    }

    public String getProductName() {
        return productName;
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

    public void setProductID(String productID) {
        this.productID = productID;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public void updateStock(int quantity) {
        this.stock += quantity;
    }


}
