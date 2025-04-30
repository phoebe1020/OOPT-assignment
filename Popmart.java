public class Popmart extends Product {
    private String popmartExclusiveFeature;

    public Popmart(String productID, String productName, String description, double price, int stock, String brand, String popmartExclusiveFeature) {
        super(productID, productName, description, price, stock, brand);
        this.popmartExclusiveFeature = popmartExclusiveFeature;
    }

    public String getPopmartExclusiveFeature() {
        return popmartExclusiveFeature;
    }

    public void setPopmartExclusiveFeature(String popmartExclusiveFeature) {
        this.popmartExclusiveFeature = popmartExclusiveFeature;
    }

    public String getType() {
        return "Popmart";
    }
}