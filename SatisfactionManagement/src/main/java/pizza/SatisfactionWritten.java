
package pizza;

public class SatisfactionWritten extends AbstractEvent {

    private Long orderId;
    private Long customerId;
    private String menuOption;
    private String address;
    private String satisfactionDate;
    private String satisfactionComment;
    private Integer satisfactionLevel;

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
    public String getSatisfactionDate() {
        return satisfactionDate;
    }

    public void setSatisfactionDate(String satisfactionDate) {
        this.satisfactionDate = satisfactionDate;
    }
    public String getSatisfactionComment() {
        return satisfactionComment;
    }

    public void setSatisfactionComment(String satisfactionComment) {
        this.satisfactionComment = satisfactionComment;
    }
    public Integer getSatisfactionLevel() {
        return satisfactionLevel;
    }

    public void setSatisfactionLevel(Integer satisfactionLevel) {
        this.satisfactionLevel = satisfactionLevel;
    }
}
