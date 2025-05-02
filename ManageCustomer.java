//Author: Melody Lee Xin Yue
//Module: Product Management
//System: Online Shopping System
//Group: DFT1G12
import java.util.List;
import java.util.Scanner;

class ManageCustomer {
    private List<Customer> customers;
    private Scanner scanner;

    public ManageCustomer(List<Customer> customers) {
        this.customers = customers;
        scanner = new Scanner(System.in);
    }

    public void manageCustomerMenu() {
        boolean managingCustomers = true;

        while (managingCustomers) {
            System.out.println("\n--- Manage Customers ---");
            System.out.println("1. Add Customer");
            System.out.println("2. Update Customer Information");
            System.out.println("3. Delete Customer");
            System.out.println("4. View Customers");
            System.out.println("5. Back to Admin Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    updateCustomer();
                    break;
                case 3:
                    deleteCustomer();
                    break;
                case 4:
                    System.out.println("Customer List:");
                    for (Customer c : customers) {
                        System.out.println(c.toString());
                    }
                    System.out.println("Total Customers: " + customers.size());
                    System.out.println("-------------------");
                    break;

                case 5:
                    managingCustomers = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addCustomer() {
        System.out.println("\n--- Add Customer ---");
        System.out.print("Enter  ID: ");
        String userId = scanner.nextLine();
        // Check if the productID already exists
        for (Customer customer : customers) {
            if (customer.getUserId().equals(userId)) {
                System.out.println("Error: Customer ID already exists. Please use a unique Customer ID.");
                addCustomer();
                return;
            }
        }
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        customers.add(new Customer(userId, name, email, phone, address));
        // Add the new customer to the list
        System.out.println("New customer created !");
    }

    private void updateCustomer() {
        System.out.println("\n--- Update Customer info ---");
        System.out.print("Enter Customer ID to update: ");
        String userId = scanner.nextLine();

        for (Customer customer : customers) {
            if (customer.getUserId().equals(userId)) {
                System.out.println("Product found: " + customer.getName());
                System.out.println("1. Update Name");
                System.out.println("2. Update Email");
                System.out.println("3. Update Phone");
                System.out.println("4. Update Address");

                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter new name: ");
                        customer.setName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.print("Enter new Email: ");
                        customer.setEmail(scanner.nextLine());
                        break;
                    case 3:
                        System.out.print("Enter new phone: ");
                        customer.setPhone(scanner.nextLine());
                        break;
                    case 4:
                        System.out.print("Enter new Address: ");
                        customer.setAddress(scanner.nextLine());
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
                System.out.println("Customer information updated successfully!");
                return;
            }
        }
        System.out.println("Customer not found.");
    }

    private void deleteCustomer() {
        System.out.println("\n--- Delete Customer ---");
        System.out.print("Enter Customer ID to delete: ");
        String userId = scanner.nextLine();

        for (Customer customer : customers) {
            if (customer.getUserId().equals(userId)) {
                customers.remove(customer);
                System.out.println("Customer deleted successfully!");
                return;
            }
        }
        System.out.println("Customer not found.");
    }

}