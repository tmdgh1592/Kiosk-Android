# 에이지프리 키오스크(Age-Free Kiosk)

## ✌ Topic
<디지털 전환을 이끄는 힘: 소프트웨어><br>
디지털 소외계층을 위한 에이지프리(Age-Free) 키오스크 개발

**기존 디지털 전환 수행중 발생한 문제점 해결**
-> '키오스크 포비아'라는 말이 나올 만큼, 디지털 소외계층의 키오스크 접근성 문제

**새로운 디지털 전환 시도**
->소상공인 판매 데이터 정량화 및 분석


## ⚡ Key Features
- 에이지프리 키오스크: 연령에 상관없이 모든 사용자가 사용할 수 있는 키오스크
- 키오스크 사용에 있어 불편하지 않게 연령대별 최적화된 UX/UI를 제공
- 소상공인 판매상품과 고객 나이/성별 데이터 저장 및 분석
- 수집된 데이터를 기반으로 제품 추천

## 😊 Flow
에이지프리 키오스크 관련 전반적인 구동 플로우입니다.
![aa](https://user-images.githubusercontent.com/22411406/175378328-23520d9a-452f-4c55-8977-0192b8337a61.png)

## 👊 Introduction
에이지프리 키오스크 관련 실제 구동 화면입니다.<br>

1. 시작( 포장, 매장 )
2. 얼굴 인식
3. 기본 화면 ( default ) -> 옵션 선택 -> 추천 메뉴 -> 선택 제품 확인 -> 결제 수단 선택 -> 결제 -> 영수증
4. 쉬운 화면 ( old ) -> 옵션 선택 (온도 -> 농도 -> 양 -> 수량 ) -> 선택 제품 확인 -> 결제 수단 선택 -> 결제 -> 영수증

### 1. 시작 ( 포장, 매장 ) ✌
<p align="left" display="inline">
  <img src="https://user-images.githubusercontent.com/56534241/175430358-0a95607d-d98c-4b5f-9eae-50eae14abd3c.png" width="270" height="495"/>
</p><br><br>

### 2. 얼굴 인식 ✨
<p align="left" display="inline">
  <img src="https://user-images.githubusercontent.com/56534241/175430361-28a0543d-8d7e-470d-9b83-3c33f382c545.png" width="270" height="495"/>
  <img src="https://user-images.githubusercontent.com/56534241/175430363-fb1660f9-9220-4116-8a35-3cdd110016a2.png" width="270" height="495"/>
</p><br><br>

### 3. 기본 화면 👨👩
<p align="left" display="inline">
  <img src="https://user-images.githubusercontent.com/56534241/175430367-6ef666ad-65cc-4505-9a37-f24d66aa730f.png" width="270" height="495"/>
  <img src="https://user-images.githubusercontent.com/56534241/175430368-6e802166-fe57-4952-9c17-f1df36e8f043.png" width="270" height="495"/>
  <img src="https://user-images.githubusercontent.com/56534241/175430369-0dd34943-46b0-4433-999f-595f25b1f993.png" width="270" height="495"/>
  <img src="https://user-images.githubusercontent.com/56534241/175430373-b4c4eaa3-2d93-433a-bbbf-f4edff5cf7aa.png" width="270" height="495"/>
  <img src="https://user-images.githubusercontent.com/56534241/175430336-5a633ca7-fc1d-40c8-9aa9-4c1754f33930.png" width="270" height="495"/>
  <img src="https://user-images.githubusercontent.com/56534241/175430340-95922a87-93bf-4a25-b3ad-a0d89af64358.png" width="270" height="495"/>
 
</p><br><br>

### 4. 쉬운 화면 👴🏻👵🏻
<p align="left" display="inline">
  <img src="https://user-images.githubusercontent.com/56534241/175430342-95944b6d-4ac2-4196-9b87-caf9805e415d.png" width="270" height="495"/>
  <img src="https://user-images.githubusercontent.com/56534241/175430344-e00c9b56-27a5-483d-9e4e-80e5482b6b53.png" width="270" height="495"/>
  <img src="https://user-images.githubusercontent.com/56534241/175430345-85c95ba7-598b-40d9-af50-5eb3cf198a97.png" width="270" height="495"/>
  <img src="https://user-images.githubusercontent.com/56534241/175430347-30297355-ef22-4198-af3a-eabfdf2c8e05.png" width="270" height="495"/>
  <img src="https://user-images.githubusercontent.com/56534241/175430352-addf7555-cf0f-4719-9dea-b06de619b544.png" width="270" height="495"/>
  <img src="https://user-images.githubusercontent.com/56534241/175430357-172492ef-1fba-4622-aa29-035558484dee.png" width="270" height="495"/>
</p><br><br>


## 😃 Client: Package
클라이언트에서 사용한 패키지 관련 설명입니다.

### - Ui : Activity, Fragment 등 화면과 CustomView 관련 클래스
- old - 시니어 고객을 위한 패키지
- defaults - 그 외, 키오스크에 크게 불편함을 느끼지 않는 고객을 위한 패키지

### - Data : 서버와 통신하기 위한 RestAPI, DTO 관련 클래스

### - Utils : Extensions, PreferenceManager, DataProvider 등 프로그램 작성에 도움을 제공하는 클래스

### - OpenCV를 사용하여 얼굴인식
- 얼굴인식 후 이용자의 사진을 retrofit2 library를 이용해 서버에 전송하여 학습된 AI 모델을 이용해 성별, 나이 등 분석 

## 🌕 Server: API Architecture
서버에서 사용한 API 아키텍쳐 관련 설명입니다.
<br><br>
<img src="https://user-images.githubusercontent.com/56534241/175431852-f8a7d85f-7f26-4841-8683-4477e752a548.png" width="995" height="521"/>
<br><br>
### - POST /v1/detect: 클라이언트에서 사진을 보냈을 때 연령, 성별을 알려주는 API
- 내부적으로 Kakao Vision API를 call 하면서 동작함
- 기존 다른 머신러닝 모델들(FaceNet)과 비교했을 때 정확도가 더 높아 선택
- 연령, 성별을 클라이언트로 내려주어 클라이언트에서 적절한 UI를 보여주는데 사용

### - POST /v1/order: 클라이언트에서 서버로 주문을 보내는 API
- 연령, 성별, 메뉴, 메뉴 추가 정보, 수량등을 서버로 전송
- 서버에서 통계를 저장해 나중에 데이터 분석으로 사용할 수 있도록 하고, 주문을 저장함

### - GET /v1/order: 클라이언트에서 서버에 존재하는 주문 목록을 가져오는 API
- 연령, 성별, 메뉴, 옵션 등 정보를 서버에서 가져옴
- 이를 바탕으로 매장 내 직원이 음식을 만들고 서빙을 진행

### - GET /v1/best/{age}/{gender}: 내 나이와 성별에 맞는 Best Item 추천 API
- 내 연령과 성별을 Query Parameter로 넣어주어 서버로 전송함
- 서버에서는 기존에 쌓인 데이터를 기반으로 적절히 음식을 추천해줌(간단한 예시: 60대 여성의 경우 카모마일티)

## 🐔 Tech Stack
- Client: Kotlin, Android, OpenCV, Android Studio
- Server: Python Flask, MySQL, Kakao Vision API, Swagger

## ☀ Team
- Team 에이지프리
- 김영우(BE), 이준수(BE), 최승호(FE), 오윤성(FE), 유혜린(AD)
