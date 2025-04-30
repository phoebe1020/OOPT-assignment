public class Sanrio extends Product {
    private String sanrioSpecialEdition;

    public Sanrio(String productID, String productName, String description, double price, int stock, String brand, String sanrioSpecialEdition) {
        super(productID, productName, description, price, stock, brand);
        this.sanrioSpecialEdition = sanrioSpecialEdition;
    }

    public String getSanrioSpecialEdition() {
        return sanrioSpecialEdition;
    }

    public void setSanrioSpecialEdition(String sanrioSpecialEdition) {
        this.sanrioSpecialEdition = sanrioSpecialEdition;
    }

    public String getType() {
        return "Sanrio";
    }
}