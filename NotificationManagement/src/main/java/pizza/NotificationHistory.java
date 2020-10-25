package pizza;

import javax.persistence.*;

@Entity
@Table(name="NotificationHistory_table")
public class NotificationHistory {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private Long customerId;
    private String paymentDate;
    private String eventDate;
    private String notificationType;
    private String orderState;
    private String menuOption;
    private String address;


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

    public String getEventDate() {
        return eventDate;
    }
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getNotificationType() {
        return notificationType;
    }
    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

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


}
