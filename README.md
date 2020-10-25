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

