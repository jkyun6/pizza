package pizza;

import pizza.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired
    private NotificationHistoryRepository notificationHistoryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderPlaced_OrderDeliveryNotice(@Payload OrderPlaced orderPlaced){

        if(orderPlaced.isMe()){
            System.out.println("##### listener OrderDeliveryNotice : " + orderPlaced.toJson());
            // view 객체 생성
            NotificationHistory notificationHistory = new NotificationHistory();
            // view 객체에 이벤트의 Value 를 set 함
            notificationHistory.setTimestamp(orderPlaced.getTimestamp());
            notificationHistory.setEventType(orderPlaced.getEventType());
            notificationHistory.setOrderId(orderPlaced.getId());
            notificationHistory.setCustomerId(orderPlaced.getCustomerId());
            notificationHistory.setPaymentState(orderPlaced.getState());
            notificationHistory.setPrice(orderPlaced.getPrice());
            notificationHistory.setPaymentMethod(orderPlaced.getPaymentMethod());
            notificationHistory.setMenuOption(orderPlaced.getMenuOption());
            notificationHistory.setNotificationType(orderPlaced.getNotificationType());
            // view 레파지 토리에 save
            notificationHistoryRepository.save(notificationHistory);
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCanceled_OrderDeliveryNotice(@Payload OrderCanceled orderCanceled){

        if(orderCanceled.isMe()){
            System.out.println("##### listener OrderDeliveryNotice : " + orderCanceled.toJson());
            // view 객체 생성
            NotificationHistory notificationHistory = new NotificationHistory();
            // view 객체에 이벤트의 Value 를 set 함
            notificationHistory.setTimestamp(orderCanceled.getTimestamp());
            notificationHistory.setEventType(orderCanceled.getEventType());
            notificationHistory.setOrderId(orderCanceled.getId());
            notificationHistory.setCustomerId(orderCanceled.getCustomerId());
            notificationHistory.setPaymentState(orderCanceled.getState());
            notificationHistory.setPrice(orderCanceled.getPrice());
            notificationHistory.setPaymentMethod(orderCanceled.getPaymentMethod());
            notificationHistory.setNotificationType(orderCanceled.getNotificationType());
            // view 레파지 토리에 save
            notificationHistoryRepository.save(notificationHistory);
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPizzaProductionStarted_OrderDeliveryNotice(@Payload PizzaProductionStarted pizzaProductionStarted){

        if(pizzaProductionStarted.isMe()){
            System.out.println("##### listener OrderDeliveryNotice : " + pizzaProductionStarted.toJson());
            // view 객체 생성
            NotificationHistory notificationHistory = new NotificationHistory();
            // view 객체에 이벤트의 Value 를 set 함
            notificationHistory.setTimestamp(pizzaProductionStarted.getTimestamp());
            notificationHistory.setEventType(pizzaProductionStarted.getEventType());
            notificationHistory.setOrderId(pizzaProductionStarted.getId());
            notificationHistory.setCustomerId(pizzaProductionStarted.getCustomerId());
            notificationHistory.setOrderState(pizzaProductionStarted.getState());
            notificationHistory.setMenuOption(pizzaProductionStarted.getMenuOption());
            notificationHistory.setAddress(pizzaProductionStarted.getAddress());
            notificationHistory.setNotificationType(pizzaProductionStarted.getNotificationType());
            // view 레파지 토리에 save
            notificationHistoryRepository.save(notificationHistory);
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDeliveryStarted_OrderDeliveryNotice(@Payload DeliveryStarted deliveryStarted){

        if(deliveryStarted.isMe()){
            System.out.println("##### listener OrderDeliveryNotice : " + deliveryStarted.toJson());
            // view 객체 생성
            NotificationHistory notificationHistory = new NotificationHistory();
            // view 객체에 이벤트의 Value 를 set 함
            notificationHistory.setTimestamp(deliveryStarted.getTimestamp());
            notificationHistory.setEventType(deliveryStarted.getEventType());
            notificationHistory.setOrderId(deliveryStarted.getId());
            notificationHistory.setCustomerId(deliveryStarted.getCustomerId());
            notificationHistory.setOrderState(deliveryStarted.getState());
            notificationHistory.setMenuOption(deliveryStarted.getMenuOption());
            notificationHistory.setAddress(deliveryStarted.getAddress());
            notificationHistory.setNotificationType(deliveryStarted.getNotificationType());
            // view 레파지 토리에 save
            notificationHistoryRepository.save(notificationHistory);
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDeliveryCompleted_OrderDeliveryNotice(@Payload DeliveryCompleted deliveryCompleted){

        if(deliveryCompleted.isMe()){
            System.out.println("##### listener OrderDeliveryNotice : " + deliveryCompleted.toJson());
            // view 객체 생성
            NotificationHistory notificationHistory = new NotificationHistory();
            // view 객체에 이벤트의 Value 를 set 함
            notificationHistory.setTimestamp(deliveryCompleted.getTimestamp());
            notificationHistory.setEventType(deliveryCompleted.getEventType());
            notificationHistory.setOrderId(deliveryCompleted.getId());
            notificationHistory.setCustomerId(deliveryCompleted.getCustomerId());
            notificationHistory.setOrderState(deliveryCompleted.getState());
            notificationHistory.setMenuOption(deliveryCompleted.getMenuOption());
            notificationHistory.setAddress(deliveryCompleted.getAddress());
            notificationHistory.setNotificationType(deliveryCompleted.getNotificationType());
            // view 레파지 토리에 save
            notificationHistoryRepository.save(notificationHistory);
        }
    }

}
