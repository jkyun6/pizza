package pizza;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Entity
@Table(name="OrderDelivery_table")
public class OrderDelivery {

    @Id
//    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long customerId;
    /**
     * state : OrderPlaced/PizzaProductionStarted/DeliveryStarted/DeliveryCompleted
     */
    private String state;
    private String paymentDate;
    private String lastEventDate;
    private String menuOption;
    private String address;

    @PrePersist
    public void onPrePersist(){
        System.out.println(MessageFormat.format("$$$ onPrePersist activated /{0}/{1}/"
                , getId(), getState()));
        if (Arrays.asList(new String[] {"PizzaProductionStarted", "DeliveryStarted", "DeliveryCompleted"})
                .contains(getState())) {
            this.setLastEventDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(){
        System.out.println("$$$ onPostPersist activated");
        if("PizzaProductionReady".equals(getState())) {
            // 주문은 들어왔지만 제작하기 전
        }
        else if("PizzaProductionStarted".equals(getState())) {
            // 제작 수락한 경우
            System.out.println("$$$ before PizzaProductionStarted");
            PizzaProductionStarted pizzaProductionStarted = new PizzaProductionStarted();
            BeanUtils.copyProperties(this, pizzaProductionStarted);
            pizzaProductionStarted.setNotificationType("Notification");
            pizzaProductionStarted.setPaymentDate(getPaymentDate());
            pizzaProductionStarted.setDate(getLastEventDate());

            pizzaProductionStarted.publishAfterCommit();
            System.out.println(MessageFormat.format("$$$ JSON Published by OrderDelivery:PizzaProductionStarted /{0}/{1}/{2}/{3}/"
                    , getId(), getCustomerId(), getAddress(), pizzaProductionStarted.getNotificationType()));
        }
        else if ("DeliveryStarted".equals(getState())) {
            System.out.println("$$$ before DeliveryStarted");
            DeliveryStarted deliveryStarted = new DeliveryStarted();
            BeanUtils.copyProperties(this, deliveryStarted);
            deliveryStarted.setNotificationType("Notification");
            deliveryStarted.setDate(getLastEventDate());

            BeanUtils.copyProperties(this, deliveryStarted);
            deliveryStarted.publishAfterCommit();
            System.out.println(MessageFormat.format("$$$ JSON Published by OrderDelivery:DeliveryStarted /{0}/{1}/{2}/"
                    , getId(), getCustomerId(), deliveryStarted.getNotificationType()));
        }
        else if ("DeliveryCompleted".equals(getState())) {
            System.out.println("$$$ before DeliveryCompleted");
            DeliveryCompleted deliveryCompleted = new DeliveryCompleted();
            BeanUtils.copyProperties(this, deliveryCompleted);
            deliveryCompleted.setNotificationType("None");
            deliveryCompleted.setDate(getLastEventDate());

            BeanUtils.copyProperties(this, deliveryCompleted);
            deliveryCompleted.publishAfterCommit();
            System.out.println(MessageFormat.format("$$$ JSON Published by OrderDelivery:DeliveryCompleted /{0}/{1}/{2}/"
                    , getId(), getCustomerId(), deliveryCompleted.getNotificationType()));
        }
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getLastEventDate() {
        return lastEventDate;
    }
    public void setLastEventDate(String lastEventDate) {
        this.lastEventDate = lastEventDate;
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




}
