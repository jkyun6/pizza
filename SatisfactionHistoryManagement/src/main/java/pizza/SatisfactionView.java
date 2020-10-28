package pizza;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

//@Entity
//@Table(name="SatisfactionView_table")
@Document(collection = "messages")
public class SatisfactionView {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Long orderId;
        private Long customerId;
        private Integer satisfactionLevel;
        private String satisfactionComment;
        private Integer isSatisfactionWritten;
        private String timekey;
        private String menuOption;
        private String address;
        private String eventType;


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
        public Integer getSatisfactionLevel() {
            return satisfactionLevel;
        }

        public void setSatisfactionLevel(Integer satisfactionLevel) {
            this.satisfactionLevel = satisfactionLevel;
        }
        public String getSatisfactionComment() {
            return satisfactionComment;
        }

        public void setSatisfactionComment(String satisfactionComment) {
            this.satisfactionComment = satisfactionComment;
        }
        public Integer getIsSatisfactionWritten() {
            return isSatisfactionWritten;
        }

        public void setIsSatisfactionWritten(Integer isSatisfactionWritten) {
            this.isSatisfactionWritten = isSatisfactionWritten;
        }
        public String getTimekey() {
            return timekey;
        }

        public void setTimekey(String timekey) {
            this.timekey = timekey;
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
        public String getEventType() {
            return eventType;
        }

        public void setEventType(String eventType) {
            this.eventType = eventType;
        }

}
