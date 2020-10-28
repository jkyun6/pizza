package pizza;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.BeanUtils;

import java.text.MessageFormat;
import java.time.LocalDateTime;

@Entity
@Table(name="PizzaOrder_table")
public class PizzaOrder {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String date;
    private Long customerId;
    private String state;
    private String menuOption;
    private Integer price;
    private String paymentMethod;
    private String address;

    @PostUpdate
    @PostPersist
    public void onPostPersist(){
        System.out.println(MessageFormat.format("@#$ Called by Pizza Order on PostPersist /id:{0}/customer:{1}/state:{2}/date:{3}/", getId(), getCustomerId(), getState(), getDate()));

        // after action by this.state
        if("PLACE".equals(getState().toUpperCase())) {
            OrderPlaced orderPlaced = new OrderPlaced();
            BeanUtils.copyProperties(this, orderPlaced);
            if(this.menuOption.length() <= 0) {
                orderPlaced.setMenuOption("{\n" +
                        "  \"menu\": [\n" +
                        "    {\n" +
                        "      \"menuId\": \"Potato Pizza\",\n" +
                        "      \"qty\": \"1\",\n" +
                        "      \"price\": 10000\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"menuId\": \"garlic dipping source\",\n" +
                        "      \"qty\": 2,\n" +
                        "      \"price\": 400\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"totalPrice\": 10800\n" +
                        "}");
            }
            orderPlaced.setNotificationType("Notification");

            BeanUtils.copyProperties(this, orderPlaced);
            orderPlaced.publishAfterCommit();
            System.out.println(MessageFormat.format("$$$ JSON Published by pizzaOrder:PLACE /{0}/{1}/{2}/{3}/", getId(), getCustomerId(), getState(), getDate()));
            System.out.println(getMenuOption());

            //Following code causes dependency to pizza.external APIs
            // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

            System.out.println(MessageFormat.format("$$$ Request from pizzaOrder:PLACE /{0}/{1}/{2}/{3}/", getId(), getCustomerId(), getState(), getDate()));
            pizza.external.PaymentHistory paymentHistory = new pizza.external.PaymentHistory();
            BeanUtils.copyProperties(this, paymentHistory);
            paymentHistory.setOrderId(getId());
            paymentHistory.setCreditCardNo("0000-0000-0000-0000");
            // mappings goes here
            PizzaOrderManagementApplication.applicationContext.getBean(pizza.external.PaymentHistoryService.class)
                    .payment(paymentHistory);
            System.out.println("$$$ Request completed...");

        }
        else if("CANCEL".equals(getState().toUpperCase())) {
            OrderCanceled orderCanceled = new OrderCanceled();
            BeanUtils.copyProperties(this, orderCanceled);
            orderCanceled.setNotificationType("Notification");

            orderCanceled.publishAfterCommit();
            System.out.println(MessageFormat.format("$$$ JSON Published by pizzaOrder:CANCEL /{0}/{1}/{2}/{3}/", getId(), getCustomerId(), getState(), getDate()));
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getMenuOption() {
        return menuOption;
    }

    public void setMenuOption(String menuOption) {
        this.menuOption = menuOption;
    }
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
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }




}
