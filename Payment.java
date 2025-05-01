//Author: Lee Meng Yee
//Module: Order Management
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
    private PaymentMethod method; // Represents the payment method (e.g., CREDIT_CARD, PAYPAL)
    private TransactionStatus status;
    private LocalDateTime paymentDate;

    public Payment(String orderId, double amount, String customerId, String name, String email, String phone,
            String address) {
        super(customerId, name, email, phone, address);
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero.");// > RM 0;
        }
        this.paymentId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.amount = amount;
        this.status = TransactionStatus.PENDING;
        this.paymentDate = LocalDateTime.now();
    }

    // New constructor to match the usage in Customer.java
    public Payment(String paymentId, double amount) {
        super("CustomerId", "Name", "Email", "Phone", "Address");
        this.paymentId = paymentId;
        this.amount = amount;
    }

    // Enum to represent payment methods
    public enum PaymentMethod {
        CREDIT_CARD,
        DEBIT_CARD,
        PAYPAL,
        BANK_TRANSFER
    }

    public enum TransactionStatus {
        PENDING,
        SUCCESS,
        FAILED,
        REFUNDED
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
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

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void selectPaymentMethod(PaymentMethod method) {
        if (method == null) {
            System.out.println("Invalid method.");
            return;
        }
        this.method = method;
        System.out.println("Selected: " + method);
    }

    public boolean processPayment(Customer customers) {
        if (method == null) {
            System.out.println("No payment method selected.");
            status = TransactionStatus.FAILED;
            return false;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            switch (method) {
                case CREDIT_CARD:
                case DEBIT_CARD:
                    System.out.print("Enter card number (16 digits): ");
                    String cardInput = scanner.nextLine();
                    if (cardInput.length() != 16) {
                        System.out.println("Invalid card number. Please try again.");
                        continue;
                    }
                    break;
                case PAYPAL:
                    System.out.print("Enter PayPal email: ");
                    String email = scanner.nextLine();
                    if (customers.getEmail().equals(email)) {
                        System.out.println("PayPal email verified.");
                    } else {
                        System.out.println("Invalid PayPal email. Please try again.");
                        continue;
                    }
                    break;
                case BANK_TRANSFER:
                    System.out.println("Processing bank transfer...");
                    break;
                default:
                    System.out.println("Invalid payment method.");
                    return false;
            }
            break;
        }

        status = TransactionStatus.SUCCESS;
        return true;
    }

    public boolean retryPayment(Customer customers) {
        if (status != TransactionStatus.FAILED) {
            System.out.println("Retry not needed.");
            return false;
        }
        return processPayment(customers);
    }

    public boolean requestRefund() {
        if (status == TransactionStatus.SUCCESS) {
            status = TransactionStatus.REFUNDED;
            System.out.println("Refunded.");
            return true;
        }
        System.out.println("Refund failed.");
        return false;
    }

    @Override
    public String toString() {
        return "=============================\n" +
                "        PAYMENT RECEIPT\n" +
                "=============================\n" +
                "Payment ID   : " + paymentId + "\n" +
                "Order ID     : " + orderId + "\n" +
                "Name         : " + getName() + "\n" +
                "Email        : " + getEmail() + "\n" +
                "Phone        : " + getPhone() + "\n" +
                "Address      : " + getAddress() + "\n" +
                "-------------------------------\n" +
                "Amount Paid  : " + amount + "\n" +
                "Method       : " + method + "\n" +
                "Status       : " + status + "\n" +
                "Payment Date : " + paymentDate + "\n" +
                "=============================\n" +
                " THANK YOU FOR YOUR PAYMENT!" +
                "\n=============================";
    }

    public String getDetails() {
        return super.toString() + ", Paid $" + amount + " on " + paymentDate;
    }
}
