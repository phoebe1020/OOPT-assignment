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
        clearScreen();
        logo();
        
        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Login");
            System.out.println("2. Exit");
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
                System.out.println("Exiting POP MART. Goodbye!");
                scanner.close();
                return;
            } else {
                System.out.println("Invalid option. Try again.\n");
            }
        }
        

        // Create products
        Product crybaby = new Product("P1", "Crybaby", "Always fucking cry's baby", 1500.00, 10, "Popmart");
        Product labubu = new Product("P2", "Labubu", "Just a monster..", 999.99, 20, "Popmart");
        Product cinnamoroll = new Product("P3", "Cinnamoroll", "Cute little baby", 499.99, 15, "Sanrio");
        Product hellokitty = new Product("P4", "Hello Kitty", "A cat like human", 299.99, 30, "Sanrio");
        
        // Create customer
        Customer customer = new Customer("C1", "John Doe", "john@example.com", "555-1234", "123 Main St");
        
        // Product selection loop
        boolean continueShopping = true;
        while (continueShopping) {
            System.out.println("\nAvailable Products:");
            System.out.printf("%-10s %-20s %-10s\n", "Product ID", "Name", "Price (USD)");
            System.out.printf("%-10s %-20s %-10.2f\n", crybaby.getProductID(), crybaby.getProductName(), crybaby.getPrice());
            System.out.printf("%-10s %-20s %-10.2f\n", labubu.getProductID(), labubu.getProductName(), labubu.getPrice());
            System.out.printf("%-10s %-20s %-10.2f\n", cinnamoroll.getProductID(), cinnamoroll.getProductName(), cinnamoroll.getPrice());
            System.out.printf("%-10s %-20s %-10.2f\n", hellokitty.getProductID(), hellokitty.getProductName(), hellokitty.getPrice());
            System.out.println("5. Exit");
        
            System.out.print("Select a product to add to cart (1-5): ");
            int choice = scanner.nextInt();
        
            switch (choice) {
                case 1:
                    System.out.print("Enter quantity for Crybaby: ");
                    int crybabyQty = scanner.nextInt();
                    customer.addToCart(crybaby, crybabyQty);
                    break;
                case 2:
                    System.out.print("Enter quantity for Labubu: ");
                    int labubuQty = scanner.nextInt();
                    customer.addToCart(labubu, labubuQty);
                    break;
                case 3:
                    System.out.print("Enter quantity for Cinnamoroll: ");
                    int cinnaQty = scanner.nextInt();
                    customer.addToCart(cinnamoroll, cinnaQty);
                    break;
                case 4:
                    System.out.print("Enter quantity for Hello Kitty: ");
                    int kittyQty = scanner.nextInt();
                    customer.addToCart(hellokitty, kittyQty);
                    break;
                case 5:
                    continueShopping = false;
                    continue;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }
        
            System.out.print("Do you want to continue shopping? (Y/N): ");
            String response = scanner.next().toLowerCase();
            if (!response.equals("yes")) {
                continueShopping = false;
            }
        }
        
        // Place order
        System.out.println("\nPlacing your order...");
        customer.placeOrder();
        
        scanner.close();
        System.out.println("Thank you! Have a nice day!");
    }
}
