
package pizza;

public class OrderPlaced extends AbstractEvent {

    private Long id;
    private Long customerId;
    private String state;
    private Integer price;
    private String paymentMethod;
    private String menuOption;
    private String notificationType;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getMenuOption() {
        return menuOption;
    }
    public void setMenuOption(String menuOption) {
        this.menuOption = menuOption;
    }

    public String getNotificationType() { return notificationType; }
    public void setNotificationType(String notificationType) { this.notificationType = notificationType; }
}
