![image](https://user-images.githubusercontent.com/34112237/97147961-ae8a1c80-17ad-11eb-80da-93f2f07108d4.png)
# pizza 

# 서비스 시나리오

## 기능적 요구사항
1. 고객이 메뉴를 주문한다
1. 고객이 결제한다
1. 주문이 되면 주문/배송 시스템에 전달된다.
1. 판매자가 확인하여 제작을 시작한다.
1. 배송자가 확인하여 배송을 시작한다.
1. 배송자가 배송 완료 후 완료를 입력한다.
1. 주문이 취소되면 결제가 취소된다
1. 주문 상태가 변경될 때마다 일림 관리자에 기록한다.

## 비기능적 요구사항
1. 트랜잭션
    1. 결제가 되지 않은 주문건은 아예 거래가 성립되지 않아야 한다  Sync 호출 
1. 장애격리
    1. 주문/배송 기능이 수행되지 않더라도 주문은 365일 24시간 받을 수 있어야 한다  Async (event-driven), Eventual Consistency
1. 성능
    1. 주문과 조회를 분리하여 시스템 성능을 향상시킨다.  (CQRS)
    


# 분석 설계

## DDD의 적용
핵심도메인, 서브도메인, 지원도메인으로 나누어 서비스를 구현함
- 핵심도메인 : 피자주문, 주문결제, 주문배달
- 서브도메인 : 알림관리, 마케팅관리
- 지원도메인 : (계좌결제서비스)

```
(분석/설계 내용 참조)
```

## Event Storming 결과
* MESEz로 모델링한 이벤트스토밍 결과 : http://www.msaez.io/#/storming/9jZsKaOObZg9sIWkpGQ0AqEx6kv2/mine/43513577ef0b64659209b3c97904ee99/-MK-BBvebd33BLu8-JwH

### 01. 이벤트 도출
![01 이벤트 도출](https://user-images.githubusercontent.com/34112237/97229725-22fba480-181c-11eb-830e-c88bf57fd70d.PNG)

### 02. 액터 커맨드 추가하기
![02 액터 커맨드 추가하기](https://user-images.githubusercontent.com/34112237/97229731-2727c200-181c-11eb-8b22-397e72991db2.PNG)

### 03. 어그리게잇으로 묶기
![03 어그리게잇으로 묶기](https://user-images.githubusercontent.com/34112237/97229738-2a22b280-181c-11eb-822a-0626222aa12e.PNG)

### 04. 바운디드 컨텍스트로 묶기
![04 pub sub하기전](https://user-images.githubusercontent.com/34112237/97229746-2bec7600-181c-11eb-9639-bb12758c4585.PNG)

### 05-1. 모델 수정 - 주문 취소3개 삭제
![05 주문취소3개없애야함](https://user-images.githubusercontent.com/34112237/97229755-2e4ed000-181c-11eb-871b-63c4817266ca.PNG)

### 05-2. 모델 수정 - 주문취소접수,알림발송됨 삭제
![06 주문취소접수,알림발송됨 삭제해야함](https://user-images.githubusercontent.com/34112237/97229765-30b12a00-181c-11eb-95b2-89ba111ee328.PNG)

### 05-3. 모델 수정 - REQ/RES 적용
![07 주문됨에서 바로 주문접수되면안됨](https://user-images.githubusercontent.com/34112237/97229771-33138400-181c-11eb-87cd-d8187a1b4ab1.PNG)

### 06. 팀과제최종
![08 팀과제최종](https://user-images.githubusercontent.com/34112237/97229777-34dd4780-181c-11eb-9cff-c61059a46bd8.PNG)

### 06. 팀과제최종(영어)
![09 팀과제최종영어](https://user-images.githubusercontent.com/34112237/97229782-36a70b00-181c-11eb-9417-f3c387f84689.PNG)

### 07. 개인과제 (추가 개발)
1) 주문배달 완료 이벤트 발생시 쿠폰을 발행한다
2) 쿠폰발생 이력을 기록한다.
![image](https://user-images.githubusercontent.com/69283705/97536919-42075b80-1a01-11eb-8c94-170af7d29695.png)

### 1차 모형 기능적/비기능적 요구사항을 만족하는지 검증
1. 기능적 요구사항 
    1. 고객이 메뉴를 주문한다 (OK)
    1. 고객이 결제한다 (OK)
    1. 주문이 되면 주문/배송 시스템에 전달된다. (OK)
    1. 판매자가 확인하여 제작을 시작한다. (OK)
    1. 배송자가 확인하여 배송을 시작한다. (OK)
    1. 배송자가 배송 완료 후 완료를 입력한다. (OK)
    1. 주문이 취소되면 결제가 취소된다 (OK)
    1. 주문 상태가 변경될 때마다 일림 관리자에 기록한다. (OK)

1. 비기능적 요구사항
    1. 결제가 되지 않은 주문건은 아예 거래가 성립되지 않아야 한다  Sync 호출  (OK)
    1. 주문/배송 기능이 수행되지 않더라도 주문은 365일 24시간 받을 수 있어야 한다  Async (event-driven), Eventual Consistency (OK)
    1. 주문과 조회를 분리하여 시스템 성능을 향상시킨다.  (CQRS) (OK)

## 헥사고날 아키텍처 다이어그램 도출
![image](https://user-images.githubusercontent.com/69283705/97531359-652d0d80-19f7-11eb-92d4-1a5d95e15e3b.png)

# 구현
## 실행 결과
![CancelLog(External request)](https://user-images.githubusercontent.com/34112237/97390068-4b1ffc00-191f-11eb-98c2-563bed04de5f.png)
![CancelMessage](https://user-images.githubusercontent.com/34112237/97390071-4d825600-191f-11eb-88e3-3f777e4ed091.png)
![CancelSTEP01-POST](https://user-images.githubusercontent.com/34112237/97390075-4fe4b000-191f-11eb-92fb-300535fc0f18.png)
![CancelSTEP02-PATCH](https://user-images.githubusercontent.com/34112237/97390078-51ae7380-191f-11eb-8543-8b3fa9aefb1b.png)
![CoreMessage](https://user-images.githubusercontent.com/34112237/97390086-5541fa80-191f-11eb-89db-e092a9e962ec.png)
![CoreSTEP01-POST](https://user-images.githubusercontent.com/34112237/97390090-58d58180-191f-11eb-910a-6c80dae95fa7.png)
![CoreSTEP02-PATCH](https://user-images.githubusercontent.com/34112237/97390093-5b37db80-191f-11eb-9ca8-2f49d30cfc8f.png)
![CoreSTEP03-PATCH](https://user-images.githubusercontent.com/34112237/97390100-5e32cc00-191f-11eb-9f8e-2c28650b65cc.png)
![CoreSTEP04-PATCH](https://user-images.githubusercontent.com/34112237/97390106-60952600-191f-11eb-88db-5494b8a5c584.png)

![image](https://user-images.githubusercontent.com/69283705/97531829-614dbb00-19f8-11eb-8242-c7339b874675.png)

## 서비스 호출
### Pizza Order 사용법
주문
```
http POST localhost:8088/pizzaOrders customerId=10 state="PLACE" menuOption="pepperoniPizza" price=10000 paymentMethod="CreditCard" address="ThewellI007Ho"
```
주문 취소
```
http PATCH localhost:8088/pizzaOrders/1 state="CANCEL"
```
   
### Payment 사용법


### OrderDelivery 사용법
피자제작시작 입력
```
http PATCH localhost:8088/orderDeliveries/1 state="PizzaProductionStarted"
```
배송출발 입력
```
http PATCH localhost:8088/orderDeliveries/1 state="DeliveryStarted"
```
배송완료 입력
```
http PATCH localhost:8088/orderDeliveries/1 state="DeliveryCompleted"
```

### 주문 취소
```
http PATCH localhost:8088/pizzaOrders/1 state="CANCEL"
```

## 폴리글랏 퍼시스턴스
쿠폰이력관리서비스의 DB 설정을 "H2 --> Mongo" 로 변경하여 적용함  (POM.xml,  application.yaml)
```
<!--		<dependency>-->
<!--			<groupId>com.h2database</groupId>-->
<!--			<artifactId>h2</artifactId>-->
<!--			<scope>runtime</scope>-->
<!--		</dependency>-->

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
        
        spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017
      database: admin
      username: sjh
      password: sjh
  profiles: default
#  jpa:
#    properties:
#      hibernate:
#        show_sql: true
#        format_sql: true

#  h2:
#    console:
#      enabled: true
logging:
  level:
#    org.hibernate.type: trace
    org.springframework.cloud: debug
```

## 폴리글랏 프로그래밍
- 개발 Language나 DB로 Polyglot 을 구현할 수 있음(본 프로젝트에서는 H2,Mongo DB를 적용함)
```
(폴리그랏 퍼시스턴스 참조)
```

## 동기식 호출과 Fallback 처리
1)피자주문서비스 <--> 피자결제서비스는 req/resp 방식으로 아키텍쳐를 구성함 (결제가 완료되어야 주문이 완료될 수 있도록 동기식으로 구현)
```
package pizza;
import pizza.config.kafka.KafkaProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableBinding(KafkaProcessor.class)
@EnableFeignClients
public class PizzaOrderManagementApplication {
    protected static ApplicationContext applicationContext;
    public static void main(String[] args) {
        applicationContext = SpringApplication.run(PizzaOrderManagementApplication.class, args);
    }
}
```

## 비동기식 호출 / 시간적 디커플링 / 장애격리 / 최종 (Eventual) 일관성 테스트
1) 피자주문 (결제승인)이 완료되면 Kafka 를 통해 이벤트를 수신하여 피자주문배달서비스를 처리하도록 구현함
2) pub/sub 비동기방식으로 호출하여 Eventual Consistency를 보장함
```
package pizza;

import org.springframework.beans.BeanUtils;
import pizza.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;

@Service
public class PolicyHandler{

    @Autowired OrderDeliveryRepository orderDeliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentApproved_OrderPlace(@Payload PaymentApproved paymentApproved){

        if(paymentApproved.isMe()){
            System.out.println("##### listener OrderPlace : " + paymentApproved.toJson());
            OrderDelivery orderDelivery = new OrderDelivery();
            BeanUtils.copyProperties(paymentApproved, orderDelivery);
            orderDelivery.setId(paymentApproved.getOrderId());
            orderDelivery.setOrderState("PizzaProductionReady");
            orderDelivery.setPaymentDate(paymentApproved.getTimestamp());
            orderDelivery.setLastEventDate(paymentApproved.getTimestamp());
            System.out.println("##### Command [StartPizzaProduction] activated by PolicyHandler");
            System.out.println(MessageFormat.format("###### /{0}/{1}/{2}/{3}/"
                    , orderDelivery.getId(), orderDelivery.getCustomerId(), orderDelivery.getAddress(), orderDelivery.getLastEventDate()));
            orderDeliveryRepository.save(orderDelivery);
        }
    }

}
```

###### deployment.yaml 설정
```
spec:
  ...
  template:
    metadata:
      labels:
        app: Paymentmanagement
    spec:
      containers:
        - name: Paymentmanagement
          resources:
            limits:
              cpu: 500m
            requests:
              cpu: 200m
```


## Saga
orderCanceled에서 paymentCancel로 pub 후 PaymentHistory 변경
```
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCanceled_PaymentCancel(@Payload OrderCanceled orderCanceled){
        if(orderCanceled.isMe()){
            System.out.println("##### listener PaymentCancel : " + orderCanceled.toJson());
            // do paymentCancel action
            paymentHistoryRepository.findById(orderCanceled.getId()).ifPresent((paymentHistory) -> {
                paymentHistory.setState(orderCanceled.getState());
                paymentHistoryRepository.save(paymentHistory);
            });
            System.out.println(MessageFormat.format("%%% $$$ Request for billing service(External) /OrderId:{0}/CustomerId:{1}/PaymentMethod:{2}/Price:{3}/"
                    , orderCanceled.getId(), orderCanceled.getCustomerId(), orderCanceled.getPaymentMethod(), orderCanceled.getPrice()));
        }
```

## CQRS
![image](https://user-images.githubusercontent.com/34112237/97382926-c1b4fd80-190f-11eb-9ccd-1d12a7729e1d.png)
- view 스티커 구현 (삭제)
- 핵심 Biz로직은 동기식 처리 (Req/Res) 및 비동기식 처리(pub/sub)
- 조회 목적 이력 관리를 위하여 비동기(pub/sub) 방식으로 주요 Event 별도 로그 저장
(그림. 모델에서 각 event 표시, 저장 로직 표시)

## Correlation
- 주문 취소 이벤트 발생시 결제처리가 되지 않도록 주문 취소 정보를 Update 해줌

@Service
public class PolicyHandler{
    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCanceled_PaymentCancel(@Payload OrderCanceled orderCanceled){
        if(orderCanceled.isMe()){
            System.out.println("##### listener PaymentCancel : " + orderCanceled.toJson());
            // do paymentCancel action
            paymentHistoryRepository.findById(orderCanceled.getId()).ifPresent((paymentHistory) -> {
                paymentHistory.setState(orderCanceled.getState());
                paymentHistoryRepository.save(paymentHistory);
            });
            System.out.println(MessageFormat.format("%%% $$$ Request for billing service(External) /OrderId:{0}/CustomerId:{1}/PaymentMethod:{2}/Price:{3}/"
                    , orderCanceled.getId(), orderCanceled.getCustomerId(), orderCanceled.getPaymentMethod(), orderCanceled.getPrice()));
        }
    }

}


## Gateway
```
spring:
  profiles: docker
  cloud:
    gateway:
      routes:
        - id: pizzaordermanagement
          uri: http://pizzaordermanagement:8080
          predicates:
            - Path=/pizzaOrders/** 
        - id: paymentmanagement
          uri: http://paymentmanagement:8080
          predicates:
            - Path=/paymentHistories/** 
        - id: notificationmanagement
          uri: http://notificationmanagement:8080
          predicates:
            - Path=/notificationHistories/** 
        - id: satisfactionmanagement
          uri: http://satisfactionmanagement:8080
          predicates:
            - Path=/satisfactions/** 
        - id: orderdeliverymanagement
          uri: http://orderdeliverymanagement:8080
          predicates:
            - Path=/orderDeliveries/** 
        - id: marketingmanagement
          uri: http://marketingmanagement:8080
          predicates:
            - Path= 
      globalcors:
        corsConfigurations:
          '[/**]':
            allowedOrigins:
              - "*"
            allowedMethods:
              - "*"
            allowedHeaders:
              - "*"
            allowCredentials: true

server:
  port: 8080
 ```
# 운영

## Deploy/Pipeline
PizzaOrderManagement GITHUB에 신규 파일 추가
![image](https://user-images.githubusercontent.com/34112237/97384017-2ec99280-1912-11eb-8f36-26b3c444b234.png)

CI/CD 파이프라인 자동 적용
![image](https://user-images.githubusercontent.com/34112237/97383819-d1354600-1911-11eb-9c0a-912216b45410.png)
![image](https://user-images.githubusercontent.com/34112237/97384616-61c05600-1913-11eb-89ba-813220216e9e.png)

```
skcc02@Azure:~$ kubectl get all
NAME                                           READY   STATUS    RESTARTS   AGE
pod/gateway-6f676b79b9-zpfrn                   1/1     Running   0          36m
pod/httpie                                     1/1     Running   1          17h
pod/notificationmanagement-6776554c78-ltj4d    1/1     Running   0          23m
pod/orderdeliverymanagement-7546b6f744-vvh9n   1/1     Running   0          15h
pod/paymentmanagement-8656994556-6qw66         1/1     Running   0          101s
pod/pizzaordermanagement-667cb666bd-d5fln      1/1     Running   0          24m

NAME                              TYPE           CLUSTER-IP     EXTERNAL-IP      PORT(S)          AGE
service/gateway                   LoadBalancer   10.0.94.200    52.149.189.108   8080:30155/TCP   36m
service/kubernetes                ClusterIP      10.0.0.1       <none>           443/TCP          16h
service/notificationmanagement    ClusterIP      10.0.131.123   <none>           8080/TCP         125m
service/orderdeliverymanagement   ClusterIP      10.0.208.63    <none>           8080/TCP         16h
service/paymentmanagement         ClusterIP      10.0.57.39     <none>           8080/TCP         3m59s
service/pizzaordermanagement      ClusterIP      10.0.244.64    <none>           8080/TCP         3h34m

NAME                                      READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/gateway                   1/1     1            1           36m
deployment.apps/notificationmanagement    1/1     1            1           125m
deployment.apps/orderdeliverymanagement   1/1     1            1           16h
deployment.apps/paymentmanagement         1/1     1            1           4m2s
deployment.apps/pizzaordermanagement      1/1     1            1           3h34m

NAME                                                 DESIRED   CURRENT   READY   AGE
replicaset.apps/gateway-6f676b79b9                   1         1         1       36m
replicaset.apps/notificationmanagement-6776554c78    1         1         1       24m
replicaset.apps/notificationmanagement-67f8757474    0         0         0       125m
replicaset.apps/notificationmanagement-6b7b6d796b    0         0         0       124m
replicaset.apps/orderdeliverymanagement-54fcf466cf   0         0         0       16h
replicaset.apps/orderdeliverymanagement-5cf5dc657c   0         0         0       16h
replicaset.apps/orderdeliverymanagement-65b5f4fcfb   0         0         0       16h
replicaset.apps/orderdeliverymanagement-7546b6f744   1         1         1       15h
replicaset.apps/orderdeliverymanagement-7ff58767b9   0         0         0       16h
replicaset.apps/orderdeliverymanagement-cf84dcfc     0         0         0       16h
replicaset.apps/paymentmanagement-559b8dc87b         0         0         0       3m59s
replicaset.apps/paymentmanagement-5969d57847         0         0         0       4m2s
replicaset.apps/paymentmanagement-8656994556         1         1         1       102s
replicaset.apps/pizzaordermanagement-57c4d9cc7b      0         0         0       70m
replicaset.apps/pizzaordermanagement-667cb666bd      1         1         1       24m
replicaset.apps/pizzaordermanagement-66d968895c      0         0         0       66m
replicaset.apps/pizzaordermanagement-7779c544df      0         0         0       3h34m
replicaset.apps/pizzaordermanagement-7d54c94bb6      0         0         0       62m
replicaset.apps/pizzaordermanagement-85c5f67d85      0         0         0       3h34m
```


## Circuit Breaker 
- 장애전파 차단을 위한 패턴으로 아래 3가지로 구현할 수 있음 
1)코드 기반 (Feignclient 삽입)
2)라이브러리 기반(Hystrix)
3)Proxy 기반 (Istion/Envoy 등)


## Autoscale (HPA)
사용자의 요청을 모두 받아들이기 위해 Auto Scale 기능을 적용 


- 주문배송 관리 서비스에 대한 replica 를 동적으로 늘려주도록 HPA 를 설정한다. 설정은 CPU 사용량이 20프로를 넘어서면 replica 를 최대 10개까지 늘려준다:
```
kubectl autoscale deployment orderdeliverymanagement --cpu-percent=20 --min=1 --max=10
```

- 오토스케일이 어떻게 되고 있는지 모니터링을 걸어둔다:
```
watch -n 3 kubectl get all
```
![auto scale  늘어나기 전 pods list](https://user-images.githubusercontent.com/44703764/97434966-22215a80-1963-11eb-8bfe-a32d7f9cfcf4.png)

- 무한 워크로드를 걸어준다.
```
while true;do curl 52.149.189.108:8080/orderDeliveries;done
```

- cpu 사용량 조회를 위해 hpa를 모니터링한다.
```
watch -n 1 kubectl get hpa
```
![auto scale  fullload - replica 10으로 늘어남](https://user-images.githubusercontent.com/44703764/97434956-1df53d00-1963-11eb-9e39-2cf8aa5f49e3.png)

- cpu 사용량이 20%를 넘어가면서 스케일 아웃이 벌어지는 것을 확인할 수 있다:
![auto scale  load target 초괴하여 replica 늘어남](https://user-images.githubusercontent.com/44703764/97434963-1f266a00-1963-11eb-9081-8fb8bcd7ae48.png)

- ![auto scale  fullload - replica 10으로 늘어난 pods list](https://user-images.githubusercontent.com/44703764/97434995-2d748600-1963-11eb-81ac-8afeef211f08.png)

- 무한 로드 중단 후 cpu 사용량이 안정되면 replica가 줄어드는 것을 확인할 수 있다:
![auto scale  부하정상화된 후 1로 줄어듦](https://user-images.githubusercontent.com/44703764/97434949-1c2b7980-1963-11eb-9e2a-56509717cf6a.png)



## 무정지 재배포 (Liveness Probe & Readiness Probe)

* 먼저 무정지 재배포가 100% 되는 것인지 확인하기 위해서 Autoscaler 설정을 제거함

- seige를 사용하여 CI/CD 실행 직전에 워크로드를 모니터링 함.
```
siege -c50 -t60S -r10 http://10.0.94.200:8080/pizzaOrders
```

- git commit을 하여 CI/CD 파이프라인 시작
- seige 의 화면으로 넘어가서 Availability 가 100% 미만으로 떨어졌는지 확인
![readiness  readiness없을 때 배포 시 가용성 낮음](https://user-images.githubusercontent.com/44703764/97436892-0e2b2800-1966-11eb-8c5e-e66da089c099.png)


배포기간중 Availability 가 평소 100%에서 80% 대로 떨어지는 것을 확인.
원인은 쿠버네티스가 새로 올려진 서비스의 상태를 무조건 READY로 인식하여 서비스 유입을 성급하게 진행한 것이기 때문. 이를 막기위해 Readiness Probe 를 설정함:

- deployment.yaml 을 수정하여 readiness probe 설정한 후 git commit.

- 동일한 시나리오로 재배포 한 후 Availability 확인:
![readiness  설정 후 무중단 배포된 것 확인함](https://user-images.githubusercontent.com/44703764/97436897-0ec3be80-1966-11eb-8a6e-99b02482995a.png)


배포기간 동안 Availability 가 변화없기 때문에 무정지 재배포가 성공한 것으로 확인됨.


## Zero-downtime deploy (Readiness Probe)
- 배포가 될 때 무정지로... 부하 중에 새로운 버전으로....

## Self-healing (Liveness Probe)
- 리스타트되도록 증적 캡쳐	

![image](https://user-images.githubusercontent.com/34112237/97389585-047dd200-191e-11eb-8a2d-11639b262d76.png)

![image](https://user-images.githubusercontent.com/34112237/97389810-a4d3f680-191e-11eb-9635-d01352b6aa07.png)


## Config Map
- application.yml

![image](https://user-images.githubusercontent.com/34112237/97435458-c86d6000-1963-11eb-8c36-1debcb95ae40.png)
- deployment.yml

![image](https://user-images.githubusercontent.com/34112237/97435479-d0c59b00-1963-11eb-844f-e593772a39a1.png)
- configmap 설정

![image](https://user-images.githubusercontent.com/34112237/97435518-dd49f380-1963-11eb-9592-8c7c16bab8f3.png)
- Application 활용

![image](https://user-images.githubusercontent.com/34112237/97435550-e8048880-1963-11eb-8434-1325a51c2868.png)



   
