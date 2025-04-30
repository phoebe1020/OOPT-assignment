import java.util.Scanner;

// Main class//////////////////////////////////////////////////////////////////////
public class OnlineShoppingSystem {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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
        // Create admin users
        Admin[] admins = new Admin[3];
        admins[0] = new Admin("AMD001", "Xavier", "xavier@gmail.com", "011111111", "Meow Street", "Xavier123");
        admins[1] = new Admin("AMD002", "Zayne", "zayne@gmail.com", "022222222", "Lily Street", "Zayne123");
        admins[2] = new Admin("AMD003", "Rafayel", "rafayel@gmail.com", "033333333", "Jellyfish Street", "Rafayel123");
        
        clearScreen();
        logo();

        while (true) {
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
                break;
            } else if (menuChoice == 2) {
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
                                    System.out.println("Managing products...");
                                 
                                    break;

                                case 2:
                                    System.out.println("Viewing orders...");
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

                scanner.close();
                return;

            } else if (menuChoice == 3) {
                System.out.println("Exiting POP MART. Goodbye!");
                scanner.close();
                return;

            }

            else {
                System.out.println("Invalid option. Try again.\n");
            }
        }

        // Create products
Popmart crybaby = new Popmart("P1", "Crybaby", "Always crying baby", 1500.00, 10, "Popmart", "Exclusive Crybaby Edition");
Popmart labubu = new Popmart("P2", "Labubu", "Just a monster..", 999.99, 20, "Popmart", "Limited Labubu Series");
Sanrio cinnamoroll = new Sanrio("P3", "Cinnamoroll", "Cute little baby", 499.99, 15, "Sanrio", "Special Cinnamoroll Plush");
Sanrio hellokitty = new Sanrio("P4", "Hello Kitty", "A cat-like human", 299.99, 30, "Sanrio", "Hello Kitty Anniversary Edition");

        // Create customer
        Customer customer = new Customer("C1", "John Doe", "john@example.com", "555-1234", "123 Main St");
        boolean running = true;

        while (running) {
            System.out.println("\n--- POP MART MENU ---");
            System.out.println("1. Order Products");
            System.out.println("2. View Cart");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int mainChoice = scanner.nextInt();

            switch (mainChoice) {
                case 1: // === Order Products ===
                    boolean continueShopping = true;
                    while (continueShopping) {
                        System.out.println("\nAvailable Products:");
                        System.out.printf("%-10s %-20s %-10s\n", "Product ID", "Name", "Price (USD)");
                        System.out.printf("%-10s %-20s %-10.2f\n", crybaby.getProductID(), crybaby.getProductName(),
                                crybaby.getPrice());
                        System.out.printf("%-10s %-20s %-10.2f\n", labubu.getProductID(), labubu.getProductName(),
                                labubu.getPrice());
                        System.out.printf("%-10s %-20s %-10.2f\n", cinnamoroll.getProductID(),
                                cinnamoroll.getProductName(), cinnamoroll.getPrice());
                        System.out.printf("%-10s %-20s %-10.2f\n", hellokitty.getProductID(),
                                hellokitty.getProductName(), hellokitty.getPrice());
                        System.out.println("5. Back to Menu");

                        System.out.print("Select a product to add to cart (1-5): ");
                        int choice = scanner.nextInt();

                        switch (choice) {
                            case 1:
                                System.out.println("You selected Crybaby.");
                                System.out.print("Enter quantity: ");
                                customer.addToCart(crybaby, scanner.nextInt());
                                break;
                            case 2:
                                System.out.println("You selected Labubu.");
                                System.out.print("Enter quantity: ");
                                customer.addToCart(labubu, scanner.nextInt());
                                break;
                            case 3:
                                System.out.println("You selected Cinnamoroll.");
                                System.out.print("Enter quantity: ");
                                customer.addToCart(cinnamoroll, scanner.nextInt());
                                break;
                            case 4:
                                System.out.println("You selected Hello Kitty.");
                                System.out.print("Enter quantity: ");
                                customer.addToCart(hellokitty, scanner.nextInt());
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
                        running = false;
                    }
                    break;

                case 3: // === Exit ===
                    System.out.println("Thank you for visiting POP MART. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
        System.out.println("Thank you! Have a nice day!");
    }
}
