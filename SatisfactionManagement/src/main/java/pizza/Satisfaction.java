package pizza;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Satisfaction_table")
public class Satisfaction {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long customerId;
    private String menuOption;
    private String satisfactionDate;
    private String satisfactionComment;
    private Integer satisfactionLevel;
    private boolean isCompleted;
    private String address;

    @PostPersist
    public void onPostPersist(){
        SatisfactionWritten satisfactionWritten = new SatisfactionWritten();
        BeanUtils.copyProperties(this, satisfactionWritten);
        satisfactionWritten.publishAfterCommit();


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
    public String getMenuOption() {
        return menuOption;
    }

    public void setMenuOption(String menuOption) {
        this.menuOption = menuOption;
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
    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }




}
