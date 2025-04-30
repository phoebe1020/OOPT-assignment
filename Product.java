
// Product class definition
public class Product {
    private String productID;
    private String productName;
    private double price;
    private String description;
    private int stock;
    private String brand;
    private String series;

    // Parameterized constructor
    public Product(String productID, String productName, String description, double price, int stock, String brand, String series) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.brand = brand;
        this.series = series;
    }
    
    // Getter and Setter methods
    public String getProductID() {
        return productID;
    }
     
    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getStock() {
        return stock;
    }

    public String getBrand() {
        return brand;
    }

    public String getSeries() {
        return series;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public void updateStock(int quantity) {
        this.stock += quantity;
    }


}
