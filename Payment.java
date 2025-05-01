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

    public void selectPaymentMethod(PaymentMethod method) { // Method to select payment method
        if (method == null) {
            System.out.println("Invalid payment method selected.");
            return;
        }
        this.method = method;
        System.out.println("Payment method selected: " + method);
    }

    public boolean processPayment() {
        if (this.method == null) {
            System.out.println("Payment method not selected.");
            this.status = TransactionStatus.FAILED;
            return false;
        }

        Scanner scanner = new Scanner(System.in);

        switch (method) {
            case CREDIT_CARD:
            case DEBIT_CARD:
                System.out.print("Enter 16-digit Card Number: ");
                String cardNumber = scanner.nextLine();
                if (!cardNumber.matches("\\d{16}")) {
                    System.out.println("Invalid card number.");
                    this.status = TransactionStatus.FAILED;
                    return false;
                }

                System.out.println("Processing payment via " + method + "...");
                break;

            case PAYPAL:
                System.out.print("Enter your PayPal email: ");
                String paypalEmail = scanner.nextLine();
                if (!paypalEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                    System.out.println("Invalid PayPal email.");
                    this.status = TransactionStatus.FAILED;
                    return false;
                }

                System.out.println("Processing payment via PayPal...");
                break;

            case BANK_TRANSFER:
                System.out.print("Enter your Bank Account Number: ");
                String accountNumber = scanner.nextLine();
                if (!accountNumber.matches("\\d{8,20}")) {
                    System.out.println("Invalid bank account number.");
                    this.status = TransactionStatus.FAILED;
                    return false;
                }

                System.out.println("Processing payment via Bank Transfer...");
                break;

            default:
                System.out.println("Unknown payment method.");
                this.status = TransactionStatus.FAILED;
                return false;
        }

        this.status = TransactionStatus.PENDING;
        try {
            System.out.println("Payment is being processed. Please wait...");
            Thread.sleep(3000); // Simulate a delay for payment processing
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (Math.random() > 0.8) {
            this.status = TransactionStatus.FAILED;
            System.out.println("Payment failed due to a processing error.");
            return false;
        }

        this.status = TransactionStatus.SUCCESS;
        System.out.println("Payment processed successfully.");
        return true;
    }

    // ...existing code...
    public boolean retryPayment() { 
        if (this.status != TransactionStatus.FAILED) {
            System.out.println("No need to retry. Current status: " + status);
            return false;
        }
        if (this.method == null) {
            System.out.println("Cannot retry. Payment method not set.");
            return false;
        }
        return processPayment();
    }

    public enum TransactionStatus { 
        PENDING,
        SUCCESS,
        FAILED,
        REFUNDED
    }

    public boolean requestRefund() { 
        if (status == TransactionStatus.SUCCESS) {
            this.status = TransactionStatus.REFUNDED;
            System.out.println("Refund processed.");
            return true;
        }
        System.out.println("Refund failed. Payment was not successful.");
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("              PAYMENT RECEIPT\n");
        sb.append("========================================\n");
        sb.append(String.format("Payment ID    : %s\n", paymentId));
        sb.append(String.format("Order ID      : %s\n", orderId));
        sb.append(String.format("Customer Name : %s\n", getName()));
        sb.append(String.format("Email         : %s\n", getEmail()));
        sb.append(String.format("Phone         : %s\n", getPhone()));
        sb.append(String.format("Address       : %s\n", getAddress()));
        sb.append("----------------------------------------\n");
        sb.append(String.format("Amount Paid   : $%.2f\n", amount));
        sb.append(String.format("Payment Method: %s\n", method != null ? method : "Not set"));
        sb.append(String.format("Status        : %s\n", status));
        sb.append(String.format("Payment Date  : %s\n", paymentDate));
        sb.append("========================================\n");
        sb.append("        THANK YOU FOR YOUR PAYMENT!\n");
        sb.append("========================================");
        return sb.toString();
    }

    public String getDetails() {
        return super.toString() + ", Paid $" + amount + " on " + paymentDate;
    }
}
