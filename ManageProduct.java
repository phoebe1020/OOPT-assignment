import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManageProduct {
    
    private List<Product> products;
    private Scanner scanner;
    

    public ManageProduct() {
        products = new ArrayList<>();
        scanner = new Scanner(System.in);
        
    }





















    public void createProduct(){
        System.out.println("Enter product ID: ");
        String productID = scanner.nextLine();
        System.out.println("Enter product name: ");
        String productName = scanner.nextLine();
        System.out.println("Enter product description: ");
        String description = scanner.nextLine();
        System.out.println("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.println("Enter product stock: ");
        int stock = scanner.nextInt();
        System.out.println("Enter seller name: ");
        String seller = scanner.nextLine();

        Product newProduct = new Product(productID, productName, description, price, stock, seller);
    }
}
