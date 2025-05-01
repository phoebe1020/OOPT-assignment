import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Main class//////////////////////////////////////////////////////////////////////
public class OnlineShoppingSystem {
    public static List<Order> orderList = new ArrayList<>(); 

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Error clearing screen: " + e.getMessage());
        }
    }

    public static void logo() {
        System.out.println("####################################################");
        System.out.println("###   +###- =##   :##### =### :##+  ###   +#+    ###");
        System.out.println("### +#  #  #  #  #. ####  *#:  ##   =## +#  ##  ####");
        System.out.println("###   +## .#  #   :##### =     #+ #: ##    ###  ####");
        System.out.println("### +####  #  #  ####### +* #  #     +# +# *##  ####");
        System.out.println("### *#####= -##  ####### *### .# :##  # *#. ## .####");
        System.out.println("####################################################");
        System.out.println("");
        System.out.println("Welcome to POP MART!");
        System.out.println("");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create customer
        Customer customer = new Customer("C1", "John Doe", "john@example.com", "555-1234", "123 Main St");
        // Create admin users
        Admin[] admins = new Admin[3];
        admins[0] = new Admin("AMD001", "Xavier", "xavier@gmail.com", "011111111", "Meow Street", "Xavier123");
        admins[1] = new Admin("AMD002", "Zayne", "zayne@gmail.com", "022222222", "Lily Street", "Zayne123");
        admins[2] = new Admin("AMD003", "Rafayel", "rafayel@gmail.com", "033333333", "Jellyfish Street", "Rafayel123");

        // Create a shared product list
        List<Product> products = new ArrayList<>();
        products.add(
                new Product("P1", "Crybaby", "Always crying baby", 1500.00, 10, "Popmart", "Crybaby For Love Edition"));
        products.add(
                new Product("P2", "Labubu", "Just a monster..", 999.99, 20, "Popmart", "Labubu Cherry Blossom Series"));
        products.add(new Product("P3", "Cinnamoroll", "Cute little baby", 499.99, 15, "Sanrio",
                "Special Cinnamoroll Plush"));
        products.add(new Product("P4", "Hello Kitty", "A cat-like human", 299.99, 30, "Sanrio",
                "Hello Kitty Anniversary Edition"));

        // Pass the shared product list to ManageProduct
        ManageProduct manageProduct = new ManageProduct(products);
        boolean mainMenuRunning = true;

        while (mainMenuRunning) {
            clearScreen();
            logo();

            System.out.println("1. Login as user");
            System.out.println("2. Login as admin");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int menuChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (menuChoice == 1) {
                System.out.print("Enter username: ");
                String inputUser = scanner.nextLine();

                String inputPass = "";
                while (true) {
                    System.out.print("Enter password (8 characters only): ");
                    inputPass = scanner.nextLine();

                    if (inputPass.length() == 8) {
                        break; // Exit the loop if password is 8 characters long
                    } else {
                        System.out.println("Password must be exactly 8 characters long. Try again.");
                    }
                }
                System.out.println("Login successful! Welcome, " + inputUser + "!\n");
                // Customer menu
                boolean customerRunning = true;
                while (customerRunning) {
                    System.out.println("\n--- POP MART MENU ---");
                    System.out.println("1. Order Products");
                    System.out.println("2. View Cart");
                    System.out.println("3. Logout");
                    System.out.print("Choose an option: ");
                    int mainChoice = scanner.nextInt();

                    switch (mainChoice) {
                        case 1: // === Order Products ===
                            boolean continueShopping = true;
                            while (continueShopping) {
                                System.out.println("\nAvailable Products:");
                                System.out.printf("%-11s %-20s %-15s %-15s %-20s\n", "Product ID", "Name",
                                        "Price (USD)",
                                        "Brand", "Series/Edition");

                                for (Product product : products) {
                                    System.out.printf("%-11s %-20s %-15.2f %-15s %-20s\n",
                                            product.getProductID(),
                                            product.getProductName(),
                                            product.getPrice(),
                                            product.getBrand(),
                                            product.getSeries());
                                }

                                System.out.println("5. Back to Menu");
                                System.out.print("Select a product to add to cart (1-5): ");
                                int choice = scanner.nextInt();

                                switch (choice) {
                                    case 1:
                                        System.out.println("You selected Crybaby.");
                                        System.out.print("Enter quantity: ");
                                        customer.addToCart(products.get(0), scanner.nextInt());
                                        break;
                                    case 2:
                                        System.out.println("You selected Labubu.");
                                        System.out.print("Enter quantity: ");
                                        customer.addToCart(products.get(1), scanner.nextInt());
                                        break;
                                    case 3:
                                        System.out.println("You selected Cinnamoroll.");
                                        System.out.print("Enter quantity: ");
                                        customer.addToCart(products.get(2), scanner.nextInt());
                                        break;
                                    case 4:
                                        System.out.println("You selected Hello Kitty.");
                                        System.out.print("Enter quantity: ");
                                        customer.addToCart(products.get(3), scanner.nextInt());
                                        break;
                                    case 5:
                                        continueShopping = false;
                                        break;
                                    default:
                                        System.out.println("Invalid choice. Please try again.");
                                }

                                if (choice >= 1 && choice <= 4) {
                                    System.out.print("Do you want to continue shopping? (Y/N): ");
                                    scanner.nextLine(); // consume newline
                                    String response = scanner.nextLine();
                                    if (!response.equalsIgnoreCase("yes")) {
                                        continueShopping = false;
                                    }
                                }
                            }
                            break;

                        case 2: // === View Cart ===
                            System.out.println("\n--- Your Cart ---");
                            customer.viewCart(); // You should implement this method
                            System.out.print("Do you want to proceed to payment? (Y/N): ");
                            scanner.nextLine(); // consume newline
                            String confirm = scanner.nextLine();
                            if (confirm.equalsIgnoreCase("yes")) {
                                customer.placeOrder();
                                mainChoice = 0; // Reset mainChoice to avoid going back to the menu
                            }
                            break;

                        case 3: // === Exit ===
                            System.out.println("Thank you for visiting POP MART. Goodbye!");
                            customerRunning = false;
                            break;

                        default:
                            System.out.println("Invalid option. Try again.");
                    }
                }
            } else if (menuChoice == 2) {
                // === Admin Login ===
                System.out.print("Enter admin ID: ");
                String adminID = scanner.nextLine();
                System.out.print("Enter admin password: ");
                String adminPassword = scanner.nextLine();

                boolean loginSuccessful = false;

                for (Admin admin : admins) {
                    if (adminID.equals(admin.getUserId()) && adminPassword.equals(admin.getAdminPassword())) {
                        System.out.println("Admin login successful! Welcome, " + admin.getName() + "!");
                        loginSuccessful = true;
                        // Admin menu
                        boolean adminRunning = true;
                        while (adminRunning) {
                            System.out.println("\n--- Admin Menu ---");
                            System.out.println("1. Manage Products");
                            System.out.println("2. Order list");
                            System.out.println("3. Logout");
                            System.out.print("Choose an option: ");
                            int adminChoice = scanner.nextInt();
                            scanner.nextLine();

                            switch (adminChoice) {
                                case 1:
                                    manageProduct.manageProductMenu();
                                    break;

                                case 2:
                                displayOrderList(); // Call the method to display orders

                                    break;

                                case 3:
                                    System.out.println("Logging out...");
                                    adminRunning = false;
                                    break;

                                default:
                                    System.out.println("Invalid option. Please try again.");
                            }
                        }
                        break;
                    }
                }

                if (!loginSuccessful) {
                    System.out.println("Invalid admin ID or password. Please try again.");
                }
            } else if (menuChoice == 3) {
                System.out.println("Exiting POP MART. Goodbye!");
                mainMenuRunning = false;
            }

            else {
                System.out.println("Invalid option. Try again.\n");
            }
        }

        scanner.close();
        System.out.println("Thank you! Have a nice day!");
    }

    private static void displayOrderList() {
        if (orderList.isEmpty()) {
            System.out.println("No orders have been placed yet.");
            return;
        }
    
        System.out.println("\n--- Order List ---");
        for (Order order : orderList) {
            System.out.println(order.toString()); 
            System.out.println("-------------------");
        }
    }
}
