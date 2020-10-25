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
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderPlaced_OrderDeliveryNotice(@Payload OrderPlaced orderPlaced){

        if(orderPlaced.isMe()){
            System.out.println("##### listener OrderDeliveryNotice : " + orderPlaced.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCanceled_OrderDeliveryNotice(@Payload OrderCanceled orderCanceled){

        if(orderCanceled.isMe()){
            System.out.println("##### listener OrderDeliveryNotice : " + orderCanceled.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPizzaProductionStarted_OrderDeliveryNotice(@Payload PizzaProductionStarted pizzaProductionStarted){

        if(pizzaProductionStarted.isMe()){
            System.out.println("##### listener OrderDeliveryNotice : " + pizzaProductionStarted.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDeliveryStarted_OrderDeliveryNotice(@Payload DeliveryStarted deliveryStarted){

        if(deliveryStarted.isMe()){
            System.out.println("##### listener OrderDeliveryNotice : " + deliveryStarted.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDeliveryCompleted_OrderDeliveryNotice(@Payload DeliveryCompleted deliveryCompleted){

        if(deliveryCompleted.isMe()){
            System.out.println("##### listener OrderDeliveryNotice : " + deliveryCompleted.toJson());
        }
    }

}
