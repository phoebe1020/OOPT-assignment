import java.time.LocalDateTime;
import java.util.UUID;

// Payment class
public class Payment {
    private String paymentId;
    private String orderId;
    private double amount;
    private PaymentMethod method; // Represents the payment method (e.g., CREDIT_CARD, PAYPAL)
    private TransactionStatus status;
    private LocalDateTime paymentDate;

    public Payment(String orderId, double amount) {
        this.paymentId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.amount = amount;
        this.status = TransactionStatus.PENDING;
        this.paymentDate = LocalDateTime.now();
    }
    
    // Enum to represent transaction statuses
    enum TransactionStatus {
        PENDING,
        SUCCESS,
        FAILED
    }

    // Enum to represent payment methods
    enum PaymentMethod {
        CREDIT_CARD,
        DEBIT_CARD,
        PAYPAL,
        BANK_TRANSFER
    }

    public boolean processPayment() {
        // Simulate payment processing
        System.out.println("Processing payment of RM " + amount + " for Order ID: " + orderId);
        this.status = TransactionStatus.SUCCESS;
        return true;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
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

    
}