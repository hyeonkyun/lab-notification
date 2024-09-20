# Notification Provider Project 

## REST API 명세서

### FCM push transmit 
| index | Method | URI | Description | 
| :---: | --- | --- | --- |
| 1 | POST    | `/fcm/transmit/token` | pms 메시지 발송 |
   
---   
## REST API 상세

### FCM push transmit 

#### 1. pms 메시지 발송

##### Request :: POST 방식으로 호출
- HTTP URL : ```http://[ServerIp]:[ServerPort]/[context-path]/fcm/transmit/token```  
- HTTP Header : ```Content-Type:application/json```  
- Request Parameter (Http Resquest Body :: Json 형식)

| 파라미터명 | 타입 | 필수여부 | 설명 | 
| :---: | --- | --- | --- |
| `transmitReqId` | String | 필수 | 발송 요청 ID |
| `projectId` | String | 필수 | PMS 프로젝트 ID |
| `token` | String | 필수 | 토큰 값 |
| `certification` | Json | 필수 | PMS 계정 비공개 키 |
| `notification` | Json | 필수 |  |
| `data` | Json | 필수 | target에 전달할 사용자 정의 데이터 |
| `callbackUrl` | String | 필수 | 발송 정보 콜백 Url |

##### Response Format :: JOSN 형태로 반환  
| 엘리먼트명 | 타입 | Depth | 배열구분 | 설명 | 값 구분 | 
| :---: | --- | --- | --- | --- | --- |
| `responseCode` | String | 1 |  | 응답코드 | “1000” : 성공(정상), 이외: 실패 |
| `responseMessage` | String | 1 |  | 응답코드 | “ok”: 성공, 이외: 실패 사유  |


## Object 정의

### `notification` (Object Type :: Json 형식)
| 엘리먼트명 | 타입 | 필수여부 | 설명 | 값 구분 | 
| :---: | --- | --- | --- | --- |
| `title` | String | 필수 | 제목 |  |
| `body` | String | 필수 | 본문 |  |
| `image` | String |  | 이미지url |  |

