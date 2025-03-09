# Notification Management Project 

## REST API 명세서

### Firebase 서비스 계정 비공개 키 관리   
| index | Method | URI                                                          | Description                   | 
|:-----:|--------|--------------------------------------------------------------|-------------------------------|
|   1   | POST   | `/push/certification`                                        | Firebase 서비스 계정 비공개 키 등록      |
|   2   | GET    | `/push/certifications?pageNum={pageNum}&pageSize={pageSize}` | Firebase 서비스 계정 비공개 키 조회(리스트) |
|   3   | GET    | `/push/certification/{appId}`                                | Firebase 서비스 계정 비공개 키 조회(단건)  |
|   4   | PUT    | `/push/certification/{appId}`                                | Firebase 서비스 계정 비공개 키 수정      |
|   5   | DELETE | `/push/certification/{appId}`                                | Firebase 서비스 계정 비공개 키 삭제      |
   
### App(Client) 토큰 관리
| index | Method | URI | Description | 
| :---: | --- | --- | --- |
| 1 | POST    | `/push/token` | App 토큰 등록 |
| 2 | GET     | `/push/tokens/{appId}?pageNum={pageNum}&pageSize={pageSize}` | Token 조회(리스트 by appId) |
| 3 | GET     | `/push/tokens/{appId}/{accountId}?pageNum={pageNum}&pageSize={pageSize}` | Token 조회(리스트 by appId, accountId, page, size) |
| 4 | PUT     | `/push/token/{appId}/{accountId}` | Token 사용 여부를 사용 or 미사용 으로 변경(by appId, accountId) |
| 5 | DELETE  | `/push/tokens/{appId}/{accountId}` | Token 삭제(by appId, accountId) |
| 6 | DELETE  | `/push/token/{appId}/{accountId}/{token}` | Token 삭제(by appId, accountId, token) |
   
### Push 발송 요청 
| index | Method | URI | Description | 
| :---: | --- | --- | --- |
| 1 | POST    | `/push/transmit/{appId}/{accountId}` | 사용자 별 Push 발송 요청 |
| 2 | POST    | `/push/transmit/{appId}` | 앱 별 Push 발송 요청 |
| 3 | POST    | `/push/transmit/{appId}/{topic}` | 주제 별 Push 발송 요청 |
   

### 로그 관리
| index | Method | URI | Description | 
| :---: | --- | --- | --- |
| 1 | GET     | `/push/trace/{transReqId}` | PUSH 발송 요청 로그 조회(단건) |
| 2 | GET     | `/push/trace/{reqDt}?pageNum={pageNum}&pageSize={pageSize}` | PUSH 발송 요청 로그 조회(리스트 by 요청일자) |
   
---   
## REST API 상세

### Firebase 서비스 계정 비공개 키 관리

#### 1. Firebase 서비스 계정 비공개 키 등록  

##### Request :: POST 방식으로 호출
- HTTP URL : ```http://[ServerIp]:[ServerPort]/[context-path]/push/certification```  
- HTTP Header : ```Content-Type:application/json```  
- Request Parameter (Http Resquest Body :: Json 형식)

| 파라미터명 | 타입 | 필수여부 | 설명 | 
| :---: | --- | --- | --- |
| `appId` | String | 필수 | 앱 ID |
| `appType` | String | 필수 | 앱 타입(Android / iOS / Web) |
| `pmsCd` | String | 필수 | 푸쉬 메시지 서비스 제공자 코드(FCM / ...) |
| `reqUser` | String | 필수 | 요청자 |
| `projectId` | String | 필수 | PMS 프로젝트 ID |
| `certification` | Json | 필수 | PMS 계정 비공개 키 |

##### Response Format :: JOSN 형태로 반환  
| 엘리먼트명 | 타입 | Depth | 배열구분 | 설명 | 값 구분 | 
| :---: | --- | --- | --- | --- | --- |
| `responseCode` | String | 1 |  | 응답코드 | “1000” : 성공(정상), 이외: 실패 |
| `responseMessage` | String | 1 |  | 응답 메시지 | “ok”: 성공, 이외: 실패 사유  |


#### 2. Firebase 서비스 계정 비공개 키 조회(리스트)

##### Request :: GET 방식으로 호출
- HTTP URL : ```http://[ServerIp]:[ServerPort]/[context-path]/push/certifications?pageNum={pageNum}&pageSize={pageSize}```  
- Request Parameter (GET 방식, QueryParameter)   

| 파라미터명 | 타입 | 필수여부 | 설명 | 
| :---: | --- | --- | --- |
| `pageNum` | Number | 필수 | 페이지번호 |
| `pageSize` | Number | 필수 | 페이지당 리스트 개수 |

##### Response Format :: JOSN 형태로 반환
| 엘리먼트명 | 타입 | Depth | 배열구분 | 설명 | 값 구분 | 
| :---: | --- | --- | --- | --- | --- |
| `responseCode` | String | 1 |  | 응답코드 | “1000” : 성공(정상), 이외: 실패 |
| `responseMessage` | String | 1 |  | 응답 메시지 | “ok”: 성공, 이외: 실패 사유  |
| `responseBody` | Json | 1 |  | 응답 코드가 “1000” 성공일 경우, 응답 데이터 |  |
| `pagination` | Json | 2 |  | 페이지정보 |  |
| `page` | Number | 3 |  | 페이지번호 |  |
| `size` | Number | 3 |  | 페이지당 리스트 개수 |  |
| `totalCount` | Number | 3 |  | 전체 리스트 개수 |  |
| `totalPageCount` | Number | 3 |  | 전체 페이지 개수 |  |
| `pushCertificationInfoList` | Json | 2 | 배열  | 서비스 계정 비공개 키 리스트 |  |
| `appId` | String | 3 |  | 앱 ID |  |
| `pmsCd` | String | 3 |  | 푸쉬 메시지 서비스 제공자 코드(FCM / ...) |  |
| `projectId` | String | 3 |  | PMS 프로젝트 ID |  |
| `appType` | String | 3 |  | 앱 타입(Android / iOS / Web) |  |
| `insUser` | String | 3 |  | 등록자 |  |
| `insDt` | String | 3 |  | 등록일시 |  |
| `updUser` | String | 3 |  | 수정자 |  |
| `updDt` | String | 3 |  | 수정일시 |  |
| `certification` | Json | 3 |  | PMS 계정 비공개 키 |  |

#### 3. Firebase 서비스 계정 비공개 키 조회(단건)
- HTTP URL : ```http://[ServerIp]:[ServerPort]/[context-path]/push/certification/{appId}```  
- Request Parameter (GET 방식, PathVariable)   

| 파라미터명 | 타입 | 필수여부 | 설명 | 
| :---: | --- | --- | --- |
| `appId` | Number | 필수 | 앱 ID |

##### Response Format :: JOSN 형태로 반환
| 엘리먼트명 | 타입 | Depth | 배열구분 | 설명 | 값 구분 | 
| :---: | --- | --- | --- | --- | --- |
| `responseCode` | String | 1 |  | 응답코드 | “1000” : 성공(정상), 이외: 실패 |
| `responseMessage` | String | 1 |  | 응답 메시지 | “ok”: 성공, 이외: 실패 사유  |
| `responseBody` | Json | 1 |  | 응답 코드가 “1000” 성공일 경우, 응답 데이터 |  |
| `pushCertificationInfo` | Json | 2 | 배열  | 서비스 계정 비공개 키 정보 |  |
| `appId` | String | 3 |  | 앱 ID |  |
| `pmsCd` | String | 3 |  | 푸쉬 메시지 서비스 제공자 코드(FCM / ...) |  |
| `projectId` | String | 3 |  | PMS 프로젝트 ID |  |
| `appType` | String | 3 |  | 앱 타입(Android / iOS / Web) |  |
| `insUser` | String | 3 |  | 등록자 |  |
| `insDt` | String | 3 |  | 등록일시 |  |
| `updUser` | String | 3 |  | 수정자 |  |
| `updDt` | String | 3 |  | 수정일시 |  |
| `certification` | Json | 3 |  | PMS 계정 비공개 키 |  |


#### 4. Firebase 서비스 계정 비공개 키 수정 

##### Request :: PUT 방식으로 호출
- HTTP URL : ```http://[ServerIp]:[ServerPort]/[context-path]/push/certification/{appId}```  
- HTTP Header : ```Content-Type:application/json```  
- Request Parameter (Http Resquest Body :: Json 형식)

| 파라미터명 | 타입 | 필수여부 | 설명 | 
| :---: | --- | --- | --- |
| `appType` | String | 필수 | 앱 타입(Android / iOS / Web) |
| `pmsCd` | String | 필수 | 푸쉬 메시지 서비스 제공자 코드(FCM / ...) |
| `reqUser` | String | 필수 | 요청자 |
| `projectId` | String | 필수 | PMS 프로젝트 ID |
| `certification` | Json | 필수 | PMS 계정 비공개 키 |

##### Response Format :: JOSN 형태로 반환  
| 엘리먼트명 | 타입 | Depth | 배열구분 | 설명 | 값 구분 | 
| :---: | --- | --- | --- | --- | --- |
| `responseCode` | String | 1 |  | 응답코드 | “1000” : 성공(정상), 이외: 실패 |
| `responseMessage` | String | 1 |  | 응답 메시지 | “ok”: 성공, 이외: 실패 사유  |


#### 5. Firebase 서비스 계정 비공개 키 삭제

##### Request :: DELETE 방식으로 호출
- HTTP URL : ```http://[ServerIp]:[ServerPort]/[context-path]/push/certification/{appId}```  
- Request Parameter (DELETE 방식, PathVariable)   

| 파라미터명 | 타입 | 필수여부 | 설명 | 
| :---: | --- | --- | --- |
| `appId` | String | 필수 | 앱 ID | 

##### Response Format :: JOSN 형태로 반환  
| 엘리먼트명 | 타입 | Depth | 배열구분 | 설명 | 값 구분 | 
| :---: | --- | --- | --- | --- | --- |
| `responseCode` | String | 1 |  | 응답코드 | “1000” : 성공(정상), 이외: 실패 |
| `responseMessage` | String | 1 |  | 응답 메시지 | “ok”: 성공, 이외: 실패 사유  |

      
### App(Client) 토큰 관리

#### 1. Token 등록

##### Request :: POST 방식으로 호출
- HTTP URL : ```http://[ServerIp]:[ServerPort]/[context-path]/push/token```  
- HTTP Header : ```Content-Type:application/json```  
- Request Parameter (Http Resquest Body :: Json 형식)

| 파라미터명 | 타입 | 필수여부 | 설명 | 
| :---: | --- | --- | --- |
| `appId` | String | 필수 | 앱 ID |
| `accountId` | String | 필수 | 계정 ID |
| `token` | String | 필수 | 토큰 값 |

##### Response Format :: JOSN 형태로 반환  
| 엘리먼트명 | 타입 | Depth | 배열구분 | 설명 | 값 구분 | 
| :---: | --- | --- | --- | --- | --- |
| `responseCode` | String | 1 |  | 응답코드 | “1000” : 성공(정상), 이외: 실패 |
| `responseMessage` | String | 1 |  | 응답 메시지 | “ok”: 성공, 이외: 실패 사유  |
 

#### 2. Token 조회(리스트 by appId)

##### Request :: GET 방식으로 호출
- HTTP URL : ```http://[ServerIp]:[ServerPort]/[context-path]/push/tokens/{appId}?pageNum={pageNum}&pageSize={pageSize}```  
- Request Parameter (GET 방식, PathVariable, QueryParameter)   

| 파라미터명 | 타입 | 필수여부 | 설명 | 
| :---: | --- | --- | --- |
| `appId` | String | 필수 | 앱 ID |
| `pageNum` | Number | 필수 | 페이지번호 |
| `pageSize` | Number | 필수 | 페이지당 리스트 개수 |

##### Response Format :: JOSN 형태로 반환
| 엘리먼트명 | 타입 | Depth | 배열구분 | 설명 | 값 구분 | 
| :---: | --- | --- | --- | --- | --- |
| `responseCode` | String | 1 |  | 응답코드 | “1000” : 성공(정상), 이외: 실패 |
| `responseMessage` | String | 1 |  | 응답 메시지 | “ok”: 성공, 이외: 실패 사유  |
| `responseBody` | Json | 1 |  | 응답 코드가 “1000” 성공일 경우, 응답 데이터 |  |
| `pagination` | Json | 2 |  | 페이지정보 |  |
| `page` | Number | 3 |  | 페이지번호 |  |
| `size` | Number | 3 |  | 페이지당 리스트 개수 |  |
| `totalCount` | Number | 3 |  | 전체 리스트 개수 |  |
| `totalPageCount` | Number | 3 |  | 전체 페이지 개수 |  |
| `pushTokenInfoList` | Json | 2 | 배열  | 토큰 정보 리스트 |  |
| `appId` | String | 3 |  | 앱 ID |  |
| `accountId` | String | 3 |  | 계정 ID |  |
| `token` | String | 3 |  | 토큰 값 |  |
| `useYn` | String | 3 |  | 사용여부 |  |
| `regDt` | String | 3 |  | 등록일시 |  |
| `updDt` | String | 3 |  | 수정일시 |  |
 
#### 3. Token 조회(리스트 by appId, accountId, page, size)
- HTTP URL : ```http://[ServerIp]:[ServerPort]/[context-path]/push/tokens/{appId}/{accountId}?pageNum={pageNum}&pageSize={pageSize}```  
- Request Parameter (GET 방식, PathVariable, QueryParameter)   

| 파라미터명 | 타입 | 필수여부 | 설명 | 
| :---: | --- | --- | --- |
| `appId` | String | 필수 | 앱 ID |
| `accountId` | String | 필수 | 계정 ID |
| `pageNum` | Number | 필수 | 페이지번호 |
| `pageSize` | Number | 필수 | 페이지당 리스트 개수 |

##### Response Format :: JOSN 형태로 반환
| 엘리먼트명 | 타입 | Depth | 배열구분 | 설명 | 값 구분 | 
| :---: | --- | --- | --- | --- | --- |
| `responseCode` | String | 1 |  | 응답코드 | “1000” : 성공(정상), 이외: 실패 |
| `responseMessage` | String | 1 |  | 응답 메시지 | “ok”: 성공, 이외: 실패 사유  |
| `responseBody` | Json | 1 |  | 응답 코드가 “1000” 성공일 경우, 응답 데이터 |  |
| `pagination` | Json | 2 |  | 페이지정보 |  |
| `page` | Number | 3 |  | 페이지번호 |  |
| `size` | Number | 3 |  | 페이지당 리스트 개수 |  |
| `totalCount` | Number | 3 |  | 전체 리스트 개수 |  |
| `totalPageCount` | Number | 3 |  | 전체 페이지 개수 |  |
| `pushTokenInfoList` | Json | 2 | 배열  | 토큰 정보 리스트 |  |
| `appId` | String | 3 |  | 앱 ID |  |
| `accountId` | String | 3 |  | 계정 ID |  |
| `token` | String | 3 |  | 토큰 값 |  |
| `useYn` | String | 3 |  | 사용여부 |  |
| `regDt` | String | 3 |  | 등록일시 |  |
| `updDt` | String | 3 |  | 수정일시 |  |
  

#### 4. Token 사용 여부를 사용 or 미사용 으로 변경(by appId, accountId)

##### Request :: PUT 방식으로 호출
- HTTP URL : ```http://[ServerIp]:[ServerPort]/[context-path]/push/token/{appId}/{accountId}```  
- HTTP Header : ```Content-Type:application/json```  

- Request Parameter (PUT 방식, PathVariable)  

| 파라미터명 | 타입 | 필수여부 | 설명 | 
| :---: | --- | --- | --- |
| `appId` | String | 필수 | 앱 ID |
| `accountId` | String | 필수 | 계정 ID |

- Request Parameter (Http Resquest Body :: Json 형식)

| 파라미터명 | 타입 | 필수여부 | 설명 | 
| :---: | --- | --- | --- |
| `token` | String |  | 토큰 값 |
| `useYn` | String | 필수 | 사용여부 |

##### Response Format :: JOSN 형태로 반환  
| 엘리먼트명 | 타입 | Depth | 배열구분 | 설명 | 값 구분 | 
| :---: | --- | --- | --- | --- | --- |
| `responseCode` | String | 1 |  | 응답코드 | “1000” : 성공(정상), 이외: 실패 |
| `responseMessage` | String | 1 |  | 응답 메시지 | “ok”: 성공, 이외: 실패 사유  |


#### 5. Token 삭제(by appId, accountId)

##### Request :: DELETE 방식으로 호출
- HTTP URL : ```http://[ServerIp]:[ServerPort]/[context-path]/push/tokens/{appId}/{accountId}```  
- Request Parameter (DELETE 방식, PathVariable)   

| 파라미터명 | 타입 | 필수여부 | 설명 | 
| :---: | --- | --- | --- |
| `appId` | String | 필수 | 앱 ID |
| `accountId` | String | 필수 | 계정 ID |

##### Response Format :: JOSN 형태로 반환  
| 엘리먼트명 | 타입 | Depth | 배열구분 | 설명 | 값 구분 | 
| :---: | --- | --- | --- | --- | --- |
| `responseCode` | String | 1 |  | 응답코드 | “1000” : 성공(정상), 이외: 실패 |
| `responseMessage` | String | 1 |  | 응답 메시지 | “ok”: 성공, 이외: 실패 사유  |

#### 6. Token 삭제(by appId, accountId, token)

##### Request :: DELETE 방식으로 호출
- HTTP URL : ```http://[ServerIp]:[ServerPort]/[context-path]/push/token/{appId}/{accountId}/{token}```  
- Request Parameter (DELETE 방식, PathVariable)   

| 파라미터명 | 타입 | 필수여부 | 설명 | 
| :---: | --- | --- | --- |
| `appId` | String | 필수 | 앱 ID |
| `accountId` | String | 필수 | 계정 ID |
| `token` | String | 필수 | 토큰 값 |

##### Response Format :: JOSN 형태로 반환  
| 엘리먼트명 | 타입 | Depth | 배열구분 | 설명 | 값 구분 | 
| :---: | --- | --- | --- | --- | --- |
| `responseCode` | String | 1 |  | 응답코드 | “1000” : 성공(정상), 이외: 실패 |
| `responseMessage` | String | 1 |  | 응답코드 | “ok”: 성공, 이외: 실패 사유  |


### Push 발송 요청 

#### 1. 사용자 별 Push 발송 요청

##### Request :: POST 방식으로 호출
- HTTP URL : ```http://[ServerIp]:[ServerPort]/[context-path]/push/transmit/{appId}/{accountId}```  
- HTTP Header : ```Content-Type:application/json```  

- Request Parameter (POST 방식, PathVariable)  

| 파라미터명 | 타입 | 필수여부 | 설명 | 
| :---: | --- | --- | --- |
| `appId` | String | 필수 | 앱 ID |
| `accountId` | String | 필수 | 계정 ID |

- Request Parameter (Http Resquest Body :: Json 형식)

| 파라미터명 | 타입 | 필수여부 | 설명 | 
| :---: | --- | --- | --- |
| `notification` | Json | 필수 | 알림 정보 |
| `data` | Json |  | 클라이언트에 전송하는 optional 데이터  |

##### Response Format :: JOSN 형태로 반환  
| 엘리먼트명 | 타입 | Depth | 배열구분 | 설명 | 값 구분 | 
| :---: | --- | --- | --- | --- | --- |
| `responseCode` | String | 1 |  | 응답코드 | “1000” : 성공(정상), 이외: 실패 |
| `responseMessage` | String | 1 |  | 응답 메시지 | “ok”: 성공, 이외: 실패 사유  |
| `responseBody` | Json | 1 |  | 응답 코드가 “1000” 성공일 경우, 응답 데이터 |  |
| `transmitReqId` | String | 2 |  | 발송_요청_ID |  |



#### 2. 앱 별 Push 발송 요청 (TODO LIST)
- HTTP URL : ```http://[ServerIp]:[ServerPort]/[context-path]/push/transmit/{appId}```  
- HTTP Header : ```Content-Type:application/json```  

- Request Parameter (POST 방식, PathVariable)  

| 파라미터명 | 타입 | 필수여부 | 설명 | 
| :---: | --- | --- | --- |
| `appId` | String | 필수 | 앱 ID | 

- Request Parameter (Http Resquest Body :: Json 형식)

| 파라미터명 | 타입 | 필수여부 | 설명 | 
| :---: | --- | --- | --- |
| `notification` | Json | 필수 | 알림 정보 |
| `data` | Json |  | 클라이언트에 전송하는 optional 데이터  |

##### Response Format :: JOSN 형태로 반환  
| 엘리먼트명 | 타입 | Depth | 배열구분 | 설명 | 값 구분 | 
| :---: | --- | --- | --- | --- | --- |
| `responseCode` | String | 1 |  | 응답코드 | “1000” : 성공(정상), 이외: 실패 |
| `responseMessage` | String | 1 |  | 응답 메시지 | “ok”: 성공, 이외: 실패 사유  |
| `responseBody` | Json | 1 |  | 응답 코드가 “1000” 성공일 경우, 응답 데이터 |  |
| `transmitReqId` | String | 2 |  | 발송_요청_ID |  |


#### 3. 주제 별 Push 발송 요청 (TODO LIST)
- HTTP URL : ```http://[ServerIp]:[ServerPort]/[context-path]/push/transmit/{appId}/{topic}```  
- HTTP Header : ```Content-Type:application/json```  

- Request Parameter (POST 방식, PathVariable)  

| 파라미터명 | 타입 | 필수여부 | 설명 | 
| :---: | --- | --- | --- |
| `appId` | String | 필수 | 앱 ID | 
| `topic` | String | 필수 | 주제 | 

- Request Parameter (Http Resquest Body :: Json 형식)

| 파라미터명 | 타입 | 필수여부 | 설명 | 
| :---: | --- | --- | --- |
| `notification` | Json | 필수 | 알림 정보 |
| `data` | Json |  | 클라이언트에 전송하는 optional 데이터  |

##### Response Format :: JOSN 형태로 반환  
| 엘리먼트명 | 타입 | Depth | 배열구분 | 설명 | 값 구분 | 
| :---: | --- | --- | --- | --- | --- |
| `responseCode` | String | 1 |  | 응답코드 | “1000” : 성공(정상), 이외: 실패 |
| `responseMessage` | String | 1 |  | 응답 메시지 | “ok”: 성공, 이외: 실패 사유  |
| `responseBody` | Json | 1 |  | 응답 코드가 “1000” 성공일 경우, 응답 데이터 |  |
| `transmitReqId` | String | 2 |  | 발송_요청_ID |  |


### 로그 관리

#### 1. PUSH 발송 요청 로그 조회(단건) 
- HTTP URL : ```http://[ServerIp]:[ServerPort]/[context-path]/push/trace/{transReqId}```  
- Request Parameter (GET 방식, PathVariable)   

| 파라미터명 | 타입 | 필수여부 | 설명 | 
| :---: | --- | --- | --- |
| `transReqId` | String | 필수 | 발송_요청_ID | 

##### Response Format :: JOSN 형태로 반환
| 엘리먼트명 | 타입 | Depth | 배열구분 | 설명 | 값 구분 | 
| :---: | --- | --- | --- | --- | --- |
| `responseCode` | String | 1 |  | 응답코드 | “1000” : 성공(정상), 이외: 실패 |
| `responseMessage` | String | 1 |  | 응답 메시지 | “ok”: 성공, 이외: 실패 사유  |
| `responseBody` | Json | 1 |  | 응답 코드가 “1000” 성공일 경우, 응답 데이터 |  |
| `pushTransmitReqInfo` | Json | 2 |  | 전송요청 정보 |  |
| `transmitReqId` | String | 3 |  | 발송_요청_ID |  |
| `appId` | String | 3 |  | 앱 ID |  |
| `transmitReqParam` | String | 3 |  | 전송요청 파라미터 |  |
| `reqIp` | String | 3 |  | 요청 IP |  |
| `reqDt` | String | 3 |  | 요청일시 |  |
| `errMsg` | String | 3 |  | 에러메시지 |  |
| `pushTransmits` | Json | 3 | 배열 | 타겟 전송 리스트 |  |
| `transmitReqId` | String | 4 |  | 에러메시지 |  |
| `target` | String | 4 |  | 타켓 |  |
| `targetType` | String | 4 |  | 타켓타입 |  |
| `accountId` | String | 4 |  | 계정 ID |  |
| `reqData` | String | 4 |  | pms 요청 데이터 |  |
| `resStatusCd` | String | 4 |  | 응답 코드 |  |
| `resData` | String | 4 |  | pms 응답 데이터 |  |
| `reqDt` | String | 4 |  | 요청일시 |  |
| `endDt` | String | 4 |  | 종료일시 |  |

 

#### 2. PUSH 발송 요청 로그 조회(리스트 by 요청일자) 
- HTTP URL : ```hhttp://[ServerIp]:[ServerPort]/[context-path]/push/trace/{reqDt}?pageNum={pageNum}&pageSize={pageSize}```  
- Request Parameter (GET 방식, PathVariable, QueryParameter)   

| 파라미터명 | 타입 | 필수여부 | 설명 | 
| :---: | --- | --- | --- |
| `reqDt` | String | 필수 | 요청일 | 
| `pageNum` | Number | 필수 | 페이지번호 |
| `pageSize` | Number | 필수 | 페이지당 리스트 개수 |
 
##### Response Format :: JOSN 형태로 반환
| 엘리먼트명 | 타입 | Depth | 배열구분 | 설명 | 값 구분 | 
| :---: | --- | --- | --- | --- | --- |
| `responseCode` | String | 1 |  | 응답코드 | “1000” : 성공(정상), 이외: 실패 |
| `responseMessage` | String | 1 |  | 응답 메시지 | “ok”: 성공, 이외: 실패 사유  |
| `responseBody` | Json | 1 |  | 응답 코드가 “1000” 성공일 경우, 응답 데이터 |  |
| `pushTransmitReqList` | Json | 2 | 배열  | 전송요청 정보 |  |
| `transmitReqId` | String | 3 |  | 발송_요청_ID |  |
| `appId` | String | 3 |  | 앱 ID |  |
| `transmitReqParam` | String | 3 |  | 전송요청 파라미터 |  |
| `reqIp` | String | 3 |  | 요청 IP |  |
| `reqDt` | String | 3 |  | 요청일시 |  |
| `errMsg` | String | 3 |  | 에러메시지 |  |
| `pushTransmits` | Json | 3 | 배열 | 타겟 전송 리스트 |  |
| `transmitReqId` | String | 4 |  | 에러메시지 |  |
| `target` | String | 4 |  | 타켓 |  |
| `targetType` | String | 4 |  | 타켓타입 |  |
| `accountId` | String | 4 |  | 계정 ID |  |
| `reqData` | String | 4 |  | pms 요청 데이터 |  |
| `resStatusCd` | String | 4 |  | 응답 코드 |  |
| `resData` | String | 4 |  | pms 응답 데이터 |  |
| `reqDt` | String | 4 |  | 요청일시 |  |
| `endDt` | String | 4 |  | 종료일시 |  |
 
---
## Object 정의

### `notification` (Object Type :: Json 형식)
| 엘리먼트명 | 타입 | 필수여부 | 설명 | 값 구분 | 
| :---: | --- | --- | --- | --- |
| `title` | String | 필수 | 제목 |  |
| `body` | String | 필수 | 본문 |  |
| `image` | String |  | 이미지url |  |

---