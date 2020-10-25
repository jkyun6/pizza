package pizza;

import pizza.config.kafka.KafkaProcessor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCanceled_PaymentCancel(@Payload OrderCanceled orderCanceled){
        if(orderCanceled.isMe()){
            System.out.println("##### listener PaymentCancel : " + orderCanceled.toJson());
            // do paymentCancel action
            System.out.println(MessageFormat.format("%%% $$$ Request for billing service(External) /OrderId:{0}/CustomerId:{1}/PaymentMethod:{2}/Price:{3}/"
                    , orderCanceled.getId(), orderCanceled.getCustomerId(), orderCanceled.getPaymentMethod(), orderCanceled.getPrice()));
        }
    }

}
