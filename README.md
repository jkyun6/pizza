# pizza 

# 서비스 시나리오

기능적 요구사항
1.
1.
1.
1.

비기능적 요구사항
1.
1.


# 분석 설계

## Event Storming 결과
* MESEz로 모델링한 이벤트스토밍 결과 : http://www.msaez.io/#/storming/9jZsKaOObZg9sIWkpGQ0AqEx6kv2/mine/43513577ef0b64659209b3c97904ee99/-MK-BBvebd33BLu8-JwH

### 이벤트 도출

### 부적격 이벤트 탈락

### 어그리게잇으로 묶기

### 바운디드 컨텍스트로 묶기

### 폴리시 부착

### 완성된 1차 모형

### 1차 모형 기능적/비기능적 요구사항을 만족하는지 검증

### 모델 수정


## 헥사고날 아키텍처 다이어그램 도출


# 구현

## DDD의 적용

## 폴리글랏 퍼시스턴스


# PizzaOrderManagement


# PaymentManagement


# OrderDeliveryManagement


## 폴리글랏 프로그래밍


## 동기식 호출과 Fallback 처리


## 비동기식 호출 / 시간적 디커플링 / 장애격리 / 최종 (Eventual) 일관성 테스트

# 운영

## CI/CD 설정


## 동기식 호출 / 서킷 브레이킹 / 장애 격리

### 오토 스케일 아웃


## 무정지 배포


# deployment.yaml 의 readiness probe 의 설정:

# 평가항목
## Saga
 orderCanceled에서 paymentCancel로 pub 후 PaymentHistory 변경
## CQRS
   - view 스티커 구현
   - 주문 post 마이페이지 레코드 추가
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
   
   
## 자주 사용하는 명령어
### Pizza Order 사용법
   주문
   http POST localhost:8081/pizzaOrders customerId=10 state="PLACE" menuOption="" price=10000 paymentMethod="CreditCard" address="The`wellI007Ho"
   
   주문 취소
   http PATCH localhost:8081/pizzaOrders/1 state="CANCEL"
   
   
### Payment 사용법
   
   
   
### OrderDelivery 사용법
   피자제작시작 입력
   http PATCH localhost:8083/orderDeliveries/1 orderState="PizzaProductionStarted"
   
   배송출발 입력
   http PATCH localhost:8083/orderDeliveries/1 orderState="DeliveryStarted"
   
   배송완료 입력
   http PATCH localhost:8083/orderDeliveries/1 orderState="DeliveryCompleted"
   
   
   
### kafka 사용법
   kafka폴더 이동.
   1. zookeeper 실행
   .\bin\windows\zookeeper-server-start.bat ..\..\config\zookeeper.properties
   2. kafka server 실행
   .\bin\windows\kafka-server-start.bat ..\..\config\server.properties
   
   메세지 확인하기
   kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic pizza --from-beginning
