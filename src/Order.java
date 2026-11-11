
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {

    String orderId;
    String customerId;
    BigDecimal amount;
    LocalDateTime createdAt;

    public Order(String orderId, String customerId,
                 BigDecimal amount, LocalDateTime createdAt) {

        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public String getOrderId() {
        return orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return orderId + " | " + customerId + " | " + amount;
    }
}
