//Author: Lim Wan Yoke
//Module: Product Management
//System: Online Shopping System
//Group: DFT1G12
import java.util.List;
import java.util.Scanner;

class ManageOrder {
    private List<Order> orders;
    private Scanner scanner;

    public ManageOrder(List<Order> orders) {
        this.orders = orders;
        this.scanner = new Scanner(System.in);
    }

    public void manageOrderMenu() {
        boolean managingOrders = true;

        while (managingOrders) {
            System.out.println("\n--- Manage Orders ---");
            System.out.println("1. View All Orders");
            System.out.println("2. Update Order Status");
            System.out.println("3. Filter Orders by Status");
            System.out.println("4. Back to Admin Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAllOrders();
                    break;
                case 2:
                    updateOrderStatus();
                    break;
                case 3:
                    filterOrdersByStatus();
                    break;
                case 4:
                    managingOrders = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void viewAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders have been placed yet.");
            return;
        }

        System.out.println("\n--- All Orders ---");
        for (Order order : orders) {
            System.out.println(order.getOrderSummary());
            System.out.println("-------------------");
        }
    }

    private void updateOrderStatus() {
        System.out.print("\nEnter Order ID to update: ");
        String orderId = scanner.nextLine();

        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                System.out.println("Order found: " + order.getOrderSummary());
                System.out.println("Current Status: " + order.getStatus());
                System.out.println("1. Mark as Processing");
                System.out.println("2. Mark as Completed");
                System.out.println("3. Mark as Cancelled");
                System.out.println("4. Mark as Returned");
                System.out.print("Choose a new status: ");
                int statusChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (statusChoice) {
                    case 1:
                        order.setStatus(Order.STATUS_PROCESSING);
                        break;
                    case 2:
                        order.setStatus(Order.STATUS_COMPLETED);
                        break;
                    case 3:
                        order.setStatus(Order.STATUS_CANCELLED);
                        break;
                    case 4:
                        order.setStatus(Order.STATUS_RETURNED);
                        break;
                    default:
                        System.out.println("Invalid status choice.");
                        return;
                }
                System.out.println("Order status updated successfully!");
                return;
            }
        }
        System.out.println("Order not found.");
    }

    private void filterOrdersByStatus() {
        System.out.println("\nFilter by Status:");
        System.out.println("1. Pending");
        System.out.println("2. Processing");
        System.out.println("3. Completed");
        System.out.println("4. Cancelled");
        System.out.println("5. Returned");
        System.out.print("Choose a status: ");
        int statusChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String status = null;
        switch (statusChoice) {
            case 1:
                status = Order.STATUS_PENDING;
                break;
            case 2:
                status = Order.STATUS_PROCESSING;
                break;
            case 3:
                status = Order.STATUS_COMPLETED;
                break;
            case 4:
                status = Order.STATUS_CANCELLED;
                break;
            case 5:
                status = Order.STATUS_RETURNED;
                break;
            default:
                System.out.println("Invalid status choice.");
                return;
        }

        System.out.println("\n--- Orders with Status: " + status + " ---");
        for (Order order : orders) {
            if (order.getStatus().equals(status)) {
                System.out.println(order.getOrderSummary());
                System.out.println("-------------------");
            }
        }
    }
}