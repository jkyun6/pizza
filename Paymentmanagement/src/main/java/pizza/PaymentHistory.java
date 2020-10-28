package pizza;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

import java.text.MessageFormat;
import java.time.LocalDateTime;

@Entity
@Table(name="PaymentHistory_table")
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private Long customerId;
    private String state;
    private String date;
    private String creditCardNo;
    private Integer price;
    private String menuOption;
    private String paymentMethod;
    private String address;

    @PostPersist
    public void onPostPersist(){
        if("PLACE".equals(getState())) {
            System.out.println("##### Act PostPersist if Request billing service succeed");
            // if Request billing service succeed
            PaymentApproved paymentApproved = new PaymentApproved();
            paymentApproved.setOrderId(getOrderId());
            paymentApproved.setCustomerId(getCustomerId());
            paymentApproved.setMenuOption(getMenuOption());
            paymentApproved.setAddress(getAddress());
            paymentApproved.setTimestamp(LocalDateTime.now().toString());

            try {
                Thread.currentThread().sleep((long) (1000 + Math.random() * 220));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BeanUtils.copyProperties(this, paymentApproved);
            paymentApproved.publishAfterCommit();
            System.out.println(MessageFormat.format("$$$ JSON Published by paymentHistory /Order:{0}/Customer:{1}/{2}/{3}/"
                    , getOrderId(), getCustomerId(), paymentApproved.getTimestamp()));
        }
        else {
            System.out.println("### Act PostPersist if Request [satisfactionWritten] succeed");
            System.out.println(MessageFormat.format("#### /{0}/{1}/", getId(), getState()));
        }


    }


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
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getMenuOption() {
        return menuOption;
    }

    public void setMenuOption(String menuOption) {
        this.menuOption = menuOption;
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
