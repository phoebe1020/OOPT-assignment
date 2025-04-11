// Payment class
public class Payment {
    private String paymentId;
    private String orderId;
    private double amount;
    private PaymentMethod method;
    private TransactionStatus status;
    private LocalDateTime paymentDate;

    public Payment(String orderId, double amount) {
        this.paymentId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.amount = amount;
        this.status = TransactionStatus.PENDING;
        this.paymentDate = LocalDateTime.now();
    }

    public boolean processPayment() {
        // Simulate payment processing
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