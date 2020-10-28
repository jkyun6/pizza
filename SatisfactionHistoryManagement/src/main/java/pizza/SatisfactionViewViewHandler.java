package pizza;

import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import pizza.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class SatisfactionViewViewHandler {


//    @Autowired
//    private SatisfactionViewRepository satisfactionViewRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenSatisfactionWritten_then_CREATE_1 (@Payload SatisfactionWritten satisfactionWritten) {
        try {
            if (satisfactionWritten.isMe()) {
                // view 객체 생성
                System.out.println("@@@@ SatisfactionWritten start");
                SatisfactionView satisfactionView = new SatisfactionView();
                // view 객체에 이벤트의 Value 를 set 함
                BeanUtils.copyProperties(satisfactionWritten, satisfactionView);
                satisfactionView.setOrderId(satisfactionWritten.getId());
                satisfactionView.setIsSatisfactionWritten(1);
                satisfactionView.setEventType(satisfactionWritten.getEventType());
                satisfactionView.setTimekey(satisfactionWritten.getTimestamp());
                // view 레파지 토리에 save
//                satisfactionViewRepository.save(satisfactionView);
                mongoTemplate.save(satisfactionView);
                System.out.println("@@@@ SatisfactionWritten end");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryCompleted_then_CREATE_2 (@Payload DeliveryCompleted deliveryCompleted) {
        try {
            if (deliveryCompleted.isMe()) {
                System.out.println("@@@@ DeliveryCompleted start");
                // view 객체 생성
                SatisfactionView satisfactionView = new SatisfactionView();
                // view 객체에 이벤트의 Value 를 set 함
                BeanUtils.copyProperties(deliveryCompleted, satisfactionView);
                satisfactionView.setOrderId(deliveryCompleted.getId());
                satisfactionView.setIsSatisfactionWritten(0);
                satisfactionView.setEventType(deliveryCompleted.getEventType());
                satisfactionView.setTimekey(deliveryCompleted.getTimestamp());
                // view 레파지 토리에 save
//                satisfactionViewRepository.save(satisfactionView);
                mongoTemplate.save(satisfactionView);
                System.out.println("@@@@ DeliveryCompleted end");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}