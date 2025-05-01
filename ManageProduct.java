//Author: Lim Wan Yoke
//Module: Product Management
//System: Online Shopping System
//Group: DFT1G12
import java.util.List;
import java.util.Scanner;

class ManageProduct {
    private List<Product> products;
    private Scanner scanner;

    public ManageProduct(List<Product> products) {
        this.products = products;
        scanner = new Scanner(System.in);
    }

    public void manageProductMenu() {
        boolean managingProducts = true;

        while (managingProducts) {
            System.out.println("\n--- Manage Products ---");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. View Products");
            System.out.println("5. Back to Admin Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    viewProducts();
                    break;
                case 5:
                    managingProducts = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addProduct() {
        System.out.println("\n--- Add Product ---");
        System.out.print("Enter Product ID: ");
        String productID = scanner.nextLine();
        // Check if the productID already exists
        for (Product product : products) {
            if (product.getProductID().equals(productID)) {
                System.out.println("Error: Product ID already exists. Please use a unique Product ID.");
                addProduct();
                return;
            }
        }
        System.out.print("Enter Product Name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Brand (Popmart/Sanrio): ");
        String brand = scanner.nextLine();
        System.out.print("Enter Series/Edition: ");
        String series = scanner.nextLine();

        Product product = new Product(productID, productName, description, price, stock, brand, series);
        products.add(product);
        System.out.println("Product added successfully!");
    }

    private void updateProduct() {
        System.out.println("\n--- Update Product ---");
        System.out.print("Enter Product ID to update: ");
        String productID = scanner.nextLine();

        for (Product product : products) {
            if (product.getProductID().equals(productID)) {
                System.out.println("Product found: " + product.getProductName());
                System.out.println("1. Update Name");
                System.out.println("2. Update Description");
                System.out.println("3. Update Price");
                System.out.println("4. Update Stock");
                System.out.println("5. Update Brand");
                System.out.println("6. Update Series/Edition");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter new name: ");
                        product.setProductName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.print("Enter new description: ");
                        product.setDescription(scanner.nextLine());
                        break;
                    case 3:
                        System.out.print("Enter new price: ");
                        product.setPrice(scanner.nextDouble());
                        break;
                    case 4:
                        System.out.print("Enter new stock: ");
                        product.setStock(scanner.nextInt());
                        break;
                    case 5:
                        System.out.print("Enter new brand: ");
                        product.setBrand(scanner.nextLine());
                        break;
                    case 6:
                        System.out.print("Enter new series/edition: ");
                        product.setSeries(scanner.nextLine());
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
                System.out.println("Product updated successfully!");
                return;
            }
        }
        System.out.println("Product not found.");
    }

    private void deleteProduct() {
        System.out.println("\n--- Delete Product ---");
        System.out.print("Enter Product ID to delete: ");
        String productID = scanner.nextLine();

        for (Product product : products) {
            if (product.getProductID().equals(productID)) {
                products.remove(product);
                System.out.println("Product deleted successfully!");
                return;
            }
        }
        System.out.println("Product not found.");
    }

    private void viewProducts() {
        System.out.println("\n--- Product List ---");
        System.out.printf("%-11s %-20s %-15s %-15s %-15s %-20s\n", "Product ID", "Name", "Price (USD)", "Stock",
                "Brand", "Series/Edition");

        for (Product product : products) {
            System.out.printf("%-11s %-20s %-15.2f %-15d %-15s %-20s\n",
                    product.getProductID(),
                    product.getProductName(),
                    product.getPrice(),
                    product.getStock(),
                    product.getBrand(),
                    product.getSeries());
        }
    }
}