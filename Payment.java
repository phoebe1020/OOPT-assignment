//Author: Lee Meng Yee
//Module: Payment Management
//System: Online Shopping System
//Group: DFT1G12
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

// Payment class
public class Payment extends User {
    private String paymentId;
    private String orderId;
    private double amount;
    private String method; // Represents the payment method (e.g., CREDIT_CARD, PAYPAL)
    private String status;
    private LocalDateTime paymentDate;
    private ShoppingCart cart;
    private Customer customer;

    // Replace PaymentMethod enum with String constants
    public static final String PAYMENT_METHOD_CREDIT_CARD = "CREDIT_CARD";
    public static final String PAYMENT_METHOD_DEBIT_CARD = "DEBIT_CARD";
    public static final String PAYMENT_METHOD_PAYPAL = "PAYPAL";
    public static final String PAYMENT_METHOD_BANK_TRANSFER = "BANK_TRANSFER";

    // Replace TransactionStatus enum with String constants
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_SUCCESS = "SUCCESS";
    public static final String STATUS_FAILED = "FAILED";
    public static final String STATUS_REFUNDED = "REFUNDED";

    public Payment(String orderId, double amount, String customerId, String name, String email, String phone,
            String address) {
        super(customerId, name, email, phone, address);
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero.");
        }
        this.paymentId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.amount = amount;
        this.status = STATUS_PENDING;
        this.paymentDate = LocalDateTime.now();
    }

    // New constructor to match the usage in Customer.java
    public Payment(String paymentId, double amount, Customer customer) {
        super(customer.getUserId(), customer.getName(), customer.getEmail(), customer.getPhone(), customer.getAddress());
        this.paymentId = paymentId;
        this.amount = amount;
        this.customer = customer; 
    }

    public Payment(String orderId, double amount) {
        super("defaultId", "defaultName", "defaultEmail", "defaultPhone", "defaultAddress");
        this.orderId = orderId;
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        if (!method.equals(PAYMENT_METHOD_CREDIT_CARD) &&
                !method.equals(PAYMENT_METHOD_DEBIT_CARD) &&
                !method.equals(PAYMENT_METHOD_PAYPAL) &&
                !method.equals(PAYMENT_METHOD_BANK_TRANSFER)) {
            System.out.println("Invalid method.");
            return;
        }
        this.method = method;
        System.out.println("Selected: " + method);
    }

    // ...existing code...

    public String getPaymentId() {
        return paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void selectPaymentMethod(String method) {
        if (!method.equals(PAYMENT_METHOD_CREDIT_CARD) &&
                !method.equals(PAYMENT_METHOD_DEBIT_CARD) &&
                !method.equals(PAYMENT_METHOD_PAYPAL) &&
                !method.equals(PAYMENT_METHOD_BANK_TRANSFER)) {
            System.out.println("Invalid method.");
            return;
        }
        this.method = method;
        System.out.println("Selected: " + method);
    }

    public boolean processPayment(Customer customers) {
        if (method == null) {
            System.out.println("No payment method selected.");
            status = STATUS_FAILED;
            return false;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            switch (method) {
                case PAYMENT_METHOD_CREDIT_CARD:
                case PAYMENT_METHOD_DEBIT_CARD:
                    System.out.print("Enter card number (16 digits): ");
                    String cardInput = scanner.nextLine();
                    if (cardInput.length() != 16) {
                        System.out.println("Invalid card number. Please try again.");
                        continue;
                    }
                    break;
                case PAYMENT_METHOD_PAYPAL:
                    System.out.print("Enter PayPal email: ");
                    String email = scanner.nextLine();
                    if (customers.getEmail().equals(email)) {
                        System.out.println("PayPal email verified.");
                    } else {
                        System.out.println("Invalid PayPal email. Please try again.");
                        continue;
                    }
                    break;
                case PAYMENT_METHOD_BANK_TRANSFER:
                    System.out.println("Processing bank transfer...");
                    break;
                default:
                    System.out.println("Invalid payment method.");
                    return false;
            }
            break;
        }

        // Simulate payment
        System.out.println("Processing...");
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Math.random() > 0.8) {
            status = STATUS_FAILED;
            System.out.println("Payment failed.");
            return false;
        }

        status = STATUS_SUCCESS;
        System.out.println("Payment successful.");
        return true;
    }

    public boolean retryPayment(Customer customers) {
        if (!status.equals(STATUS_FAILED)) {
            System.out.println("Retry not needed.");
            return false;
        }
        return processPayment(customers);
    }

    public boolean requestRefund() {
        if (status.equals(STATUS_SUCCESS)) {
            status = STATUS_REFUNDED;
            System.out.println("Refunded.");
            return true;
        }
        System.out.println("Refund failed.");
        return false;
    }

    @Override
    public String toString() {
        String receipt = "===============================================================\n" +
                "                      PAYMENT RECEIPT\n" +
                "===============================================================\n" +
                "Payment ID   : " + paymentId + "\n" +
                "Order ID     : " + orderId + "\n";

        if (customer != null) {
            receipt += "Name         : " + customer.getName() + "\n" +
                    "Email        : " + customer.getEmail() + "\n" +
                    "Phone        : " + customer.getPhone() + "\n" +
                    "Address      : " + customer.getAddress() + "\n";
        } else {
            receipt += "Customer information is not available.\n";
        }

        receipt += "-----------------------------------------------------------------\n";
        receipt += String.format("Amount Paid : %.2f\n", amount);
        receipt += String.format("Method      : %s\n", method);
        receipt += String.format("Status      : %s\n", status);
        receipt += "Payment Date : " + paymentDate + "\n";
        receipt += "===============================================================\n" +
                " THANK YOU FOR YOUR PAYMENT!";

        return receipt;
    }

    public String getDetails() {
        return super.toString() + ", Paid $" + amount + " on " + paymentDate;
    }
}
