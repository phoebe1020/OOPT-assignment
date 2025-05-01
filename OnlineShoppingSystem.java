import java.util.ArrayList;
import java.util.InputMismatchException;
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
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("C1", "Siow", "siow@gmail.com", "012345678", "123 Home"));
        customers.add(new Customer("C2", "Melody", "melodiddy@gmail.com", "013456789", "123 Klang"));
        customers.add(new Customer("C3", "Phoebe", "fakeh@gmail.com", "014567890", "123 Tarumt"));
        customers.add(new Customer("C4", "Lee", "lee@gmail.com", "015678901", "Lee Jeans"));
        // Create admin users
        Admin[] admins = new Admin[3];
        admins[0] = new Admin("AMD001", "Xavier", "xavier@gmail.com", "011111111", "Meow Street", "Xavier123");
        admins[1] = new Admin("AMD002", "Zayne", "zayne@gmail.com", "022222222", "Lily Street", "Zayne123");
        admins[2] = new Admin("AMD003", "Rafayel", "rafayel@gmail.com", "033333333", "Jellyfish Street", "Rafayel123");

        // Create a shared product list
        List<Product> products = new ArrayList<>();
        products.add(
                new Product("P1", "Crybaby", "Always crying baby", 1500.00, 10, "Popmart",
                        "Crybaby For Love Edition"));
        products.add(
                new Product("P2", "Labubu", "Just a monster..", 999.99, 20, "Popmart",
                        "Labubu Cherry Blossom Series"));
        products.add(
                new Product("P3", "Zimomo", "Literally a labubu with a tail", 999.99, 7, "Popmart",
                        "The Zimomo Collection"));
        products.add(
                new Product("P4", "Dino", "A dinosaur", 799.99, 5, "Popmart",
                        "The Dino Collection"));
        products.add(
                new Product("S1", "Cinnamoroll", "Cute little baby", 499.99, 15, "Sanrio",
                        "Special Cinnamoroll Plush"));
        products.add(
                new Product("S2", "Hello Kitty", "A cat-like human", 299.99, 30, "Sanrio",
                        "Hello Kitty Anniversary Edition"));
        products.add(
                new Product("S3", "Pompompurin", "A dog made of pudding", 249.99, 12, "Sanrio",
                        "Pompompurin Bakery Series"));
        products.add(
                new Product("S4", "Melody", "Not the one in our group", 349.99, 18, "Sanrio",
                        "My Melody Sweet Series"));
        products.add(
                new Product("PK1", "Pikachu", "Electric mouse", 199.99, 25, "Pokemon",
                        "Pikachu Special Edition"));
        products.add(
                new Product("PK2", "Eevee", "A cute little fox (Phoebe's fav)", 299.99, 22, "Pokemon",
                        "Eevee Special Edition"));
        products.add(
                new Product("PK3", "Jigglypuff", "A dedicated singer", 599.99, 6, "Pokemon",
                        "Jigglypuff Special Edition"));
        products.add(
                new Product("PK4", "Squirtle", "A squirting turtle", 399.99, 10, "Pokemon",
                        "Squirtle Special Edition"));
        products.add(
                new Product("T1", "Totoro", "A forest spirit", 399.99, 5, "Studio Ghibli", "Totoro Forest Edition"));
        products.add(
                new Product("T2", "Spirited Away", "A spirit world", 399.99, 8, "Studio Ghibli",
                        "Spirited Away Collector's Edition"));
        products.add(
                new Product("T3", "Kiki", "A witch in training", 499.99, 4, "Studio Ghibli",
                        "Kiki's Delivery Service Edition"));
        products.add(
                new Product("T4", "Howl", "A wizard with a heart", 499.99, 3, "Studio Ghibli",
                        "Howl's Moving Castle Edition"));
        
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
                boolean loginSuccessful = false;
                Customer loggedInCustomer = null;

                while (!loginSuccessful) {
                    System.out.print("Enter customer username: ");
                    String inputUser = scanner.nextLine();

                    System.out.print("Enter password (8 characters only): ");
                    String inputPass = scanner.nextLine();

                    for (Customer c : customers) {
                        if (inputUser.equals(c.getName()) && inputPass.length() == 8) {
                            loggedInCustomer = c;
                            loginSuccessful = true;
                            break;
                        }
                    }

                    if (loginSuccessful) {
                        System.out.println("Login successful! Welcome, " + loggedInCustomer.getName() + "!");
                    } else {
                        System.out.println("Invalid username or password. Please try again.");
                    }
                }

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
                            boolean shopping = true;
                            while (shopping) {
                                // Step 1: Show unique categories (brands)
                                List<String> categories = new ArrayList<>();
                                for (Product p : products) {
                                    if (!categories.contains(p.getBrand())) {
                                        categories.add(p.getBrand());
                                    }
                                }

                                System.out.println("\nAvailable Categories:");
                                for (int i = 0; i < categories.size(); i++) {
                                    System.out.println((i + 1) + ". " + categories.get(i));
                                }
                                System.out.println((categories.size() + 1) + ". Back to Menu"); // Back as a number
                                System.out.print("Choose a category: ");

                                int categoryChoice;
                                try {
                                    categoryChoice = scanner.nextInt();
                                    scanner.nextLine(); // consume newline
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a number.");
                                    scanner.nextLine(); // clear invalid input
                                    continue;
                                }

                                if (categoryChoice == categories.size() + 1) {
                                    shopping = false; // Exit shopping loop and go back to menu
                                    break;
                                }

                                if (categoryChoice < 1 || categoryChoice > categories.size()) {
                                    System.out.println("Invalid category. Please try again.");
                                    continue;
                                }

                                String selectedCategory = categories.get(categoryChoice - 1);

                                // Step 2: Show products in that category
                                List<Product> categoryProducts = new ArrayList<>();
                                for (Product p : products) {
                                    if (p.getBrand().equals(selectedCategory)) {
                                        categoryProducts.add(p);
                                    }
                                }

                                boolean shoppingCategory = true;
                                while (shoppingCategory) {
                                    System.out.println("\nProducts in " + selectedCategory + ":");
                                    for (int i = 0; i < categoryProducts.size(); i++) {
                                        Product p = categoryProducts.get(i);
                                        System.out.printf("%-10s %-20s $%-10.2f [%s]\n", p.getProductID(),
                                                p.getProductName(), p.getPrice(), p.getDescription());
                                    }
                                    System.out.println((categoryProducts.size() + 1) + ". Back to Categories"); // Back
                                                                                                                // as a
                                                                                                                // number
                                    System.out.print("Select a product by number: ");

                                    String input = scanner.nextLine();

                                    int productChoice;
                                    try {
                                        productChoice = Integer.parseInt(input);
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input. Please enter a number.");
                                        continue;
                                    }

                                    if (productChoice == categoryProducts.size() + 1) {
                                        shoppingCategory = false; // Back to categories
                                        continue;
                                    }

                                    if (productChoice < 1 || productChoice > categoryProducts.size()) {
                                        System.out.println("Invalid choice. Please try again.");
                                        continue;
                                    }

                                    Product selectedProduct = categoryProducts.get(productChoice - 1);
                                    System.out.println("You selected " + selectedProduct.getProductName() + ".");

                                    System.out.print("Enter quantity: ");
                                    int quantity;
                                    try {
                                        quantity = scanner.nextInt();
                                        scanner.nextLine(); // consume newline
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalid quantity. Please enter a valid number.");
                                        scanner.nextLine(); // clear invalid input
                                        continue;
                                    }

                                    loggedInCustomer.addToCart(selectedProduct, quantity);

                                    System.out.print("Do you want to continue shopping in this category? (Y/N): ");
                                    String cont = scanner.nextLine();
                                    if (!cont.equalsIgnoreCase("Y")) {
                                        shoppingCategory = false;
                                    }
                                }
                            }
                            break;

                        case 2: // === View Cart ===
                            System.out.println("\n--- Your Cart ---");
                            loggedInCustomer.viewCart(); // You should implement this method
                            System.out.print("Do you want to proceed to payment? (Y/N): ");
                            scanner.nextLine(); // consume newline
                            String confirm = scanner.nextLine();
                            if (confirm.equalsIgnoreCase("yes")) {
                                loggedInCustomer.placeOrder();
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
