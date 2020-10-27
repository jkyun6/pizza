package pizza;

import javax.persistence.*;

@Entity
@Table(name="NotificationHistory_table")
public class NotificationHistory {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String timestamp;
    private String eventType;
    private Long orderId;
    private Long customerId;
    private String paymentState;
    private String orderState;
    private String menuOption;
    private String address;
    private Integer price;
    private String notificationType;
    private String paymentMethod;
    private String paymentDate;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getNotificationType() {
        return notificationType;
    }
    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getPaymentState() { return paymentState; }
    public void setPaymentState(String paymentState) { this.paymentState = paymentState; }

    public String getOrderState() {
        return orderState;
    }
    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getMenuOption() {
        return menuOption;
    }
    public void setMenuOption(String menuOption) {
        this.menuOption = menuOption;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
}
