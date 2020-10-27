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
    1. 결제시스템이 과중되면 사용자를 잠시동안 받지 않고 결제를 잠시후에 하도록 유도한다  Circuit breaker, fallback
1. 성능
    1. 주문과 조회를 분리하여 시스템 성능을 향상시킨다.  (CQRS)
    1. 배달상태가 바뀔때마다 카톡 등으로 알림을 줄 수 있어야 한다  Event driven


# 분석 설계

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
    1. 결제시스템이 과중되면 사용자를 잠시동안 받지 않고 결제를 잠시후에 하도록 유도한다  Circuit breaker, fallback (OK)
    1. 주문과 조회를 분리하여 시스템 성능을 향상시킨다.  (CQRS) (OK)
    1. 배달상태가 바뀔때마다 카톡 등으로 알림을 줄 수 있어야 한다  Event driven (OK)

## 헥사고날 아키텍처 다이어그램 도출
![image](https://user-images.githubusercontent.com/34112237/97233785-dcf60f00-1822-11eb-9583-abc3d6ae706e.png)

# 구현
## 서비스 호출
### Pizza Order 사용법
주문
```
http POST localhost:8081/pizzaOrders customerId=10 state="PLACE" menuOption="" price=10000 paymentMethod="CreditCard" address="ThewellI007Ho"
```
주문 취소
```
http PATCH localhost:8081/pizzaOrders/1 state="CANCEL"
```
   
### Payment 사용법


### OrderDelivery 사용법
피자제작시작 입력
```
http PATCH localhost:8083/orderDeliveries/1 orderState="PizzaProductionStarted"
```
배송출발 입력
```
http PATCH localhost:8083/orderDeliveries/1 orderState="DeliveryStarted"
```
배송완료 입력
```
http PATCH localhost:8083/orderDeliveries/1 orderState="DeliveryCompleted"
```

### 주문 취소
```
http 
```

## DDD의 적용
간략한 설명 작성
```
소스코드 붙여넣기
```

## 폴리글랏 퍼시스턴스
간략한 설명 작성
```
소스코드 붙여넣기
```

## 폴리글랏 프로그래밍
간략한 설명 작성
```
소스코드 붙여넣기
```

## 동기식 호출과 Fallback 처리
간략한 설명 작성
```
소스코드 붙여넣기
```

## 비동기식 호출 / 시간적 디커플링 / 장애격리 / 최종 (Eventual) 일관성 테스트
간략한 설명 작성
```
소스코드 붙여넣기
```
# 운영

## CI/CD 설정
간략한 설명 작성
```
소스코드 붙여넣기
```

## 동기식 호출 / 서킷 브레이킹 / 장애 격리
간략한 설명 작성
```
소스코드 붙여넣기
```

### 오토 스케일 아웃
간략한 설명 작성
```
소스코드 붙여넣기
```

## 무정지 배포
간략한 설명 작성
```
소스코드 붙여넣기
```
# deployment.yaml 의 readiness probe 의 설정:


# 평가항목
## Saga
orderCanceled에서 paymentCancel로 pub 후 PaymentHistory 변경

## CQRS
- view 스티커 구현
- 주문 post 마이페이지 레코드 추가
- 핵심 Biz로직은 동기식 처리 (Req/Res) 및 비동기식 처리(pub/sub)
- 조회 목적 이력 관리를 위하여 비동기(pub/sub) 방식으로 주요 Event 별도 로그 저장
(그림. 모델에서 각 event 표시, 저장 로직 표시)

## Correlation
- Correlation key saga cqrs 자동 득점

## Req/Resp
- 

## Gateway

## Deploy/Pipeline (필수 아님, 운영에는 반영되어야 함)

## Circuit Breaker 

## Autoscale (HPA)

## Zero-downtime deploy (Readiness Probe)
- 배포가 될 때 무정지로... 부하 중에 새로운 버전으로....

## Config Map / Persistence Volume (둘 중에 하나)
   - 이뮤터블 이미지, 쿠버네티스 콘피그 맵

## Polyglot
   - 랭기지 레벨 또는 데이터베이스 레벨

## Self-healing (Liveness Probe)
   - 리스타트되도록 증적 캡쳐	
   
   
# 자주 사용하는 명령어
   
## kafka 사용법
kafka폴더 이동.

1. zookeeper 실행
```
.\bin\windows\zookeeper-server-start.bat ..\..\config\zookeeper.properties
```
2. kafka server 실행
```
.\bin\windows\kafka-server-start.bat ..\..\config\server.properties
```
메세지 확인하기
```
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic pizza --from-beginning
```
