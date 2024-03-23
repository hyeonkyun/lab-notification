# Notification Management Project 

## RESTAPI 명세서

### Firebase 서비스 계정 비공개 키 관리   
| index | Method | URI | Description | 
| :---: | --- | --- | --- |
| 1 | POST    | `/push/certification` | Firebase 서비스 계정 비공개 키 등록 |
| 2 | GET     | `/push/certifications/{page}/{size}` | Firebase 서비스 계정 비공개 키 조회(리스트) |
| 3 | GET     | `/push/certification/{appId}` | Firebase 서비스 계정 비공개 키 조회(단건) |
| 4 | PUT     | `/push/certification/{appId}` | Firebase 서비스 계정 비공개 키 수정 |
| 5 | DELETE  | `/push/certification/{appId}` | Firebase 서비스 계정 비공개 키 삭제 |
   
### App(Client) 토큰 관리
| index | Method | URI | Description | 
| :---: | --- | --- | --- |
| 1 | POST    | `/push/token` | App 토큰 등록 |
| 2 | GET     | `/push/token/{appId}/{page}/{size}` | Token 조회(리스트 by appId) |
| 3 | GET     | `/push/token/{appId}/{accountId}` | Token 조회(리스트 by appId, accountId) |
| 4 | GET     | `/push/token/{appId}/{accountId}/{page}/{size}` | Token 조회(리스트 by appId, accountId, page, size) |
| 5 | PUT     | `/push/token/{appId}/{accountId}` | Token 사용 여부를 사용 or 미사용 으로 변경(by appId, accountId) |
| 6 | DELETE  | `/push/token/{appId}/{accountId}` | Token 삭제(by appId, accountId) |
| 7 | DELETE  | `Token 삭제(by appId, accountId, token)` | Token 삭제(by appId, accountId, token) |
   

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
| 2 | GET     | `/push/trace/{reqDt}/{pageNo}/{size}` | PUSH 발송 요청 로그 조회(리스트 by 요청일자) |
   
---   
## RESTAPI 정의서

### Firebase 서비스 계정 비공개 키 관리

#### 1. Firebase 서비스 계정 비공개 키 등록  
```POST http://[ServerIp]:[ServerPort]/[context-path]/push/certification```  
```Content-Type:application/json```  
```
{
	"appId": "LABPUSH001",
	"appType": "Web",
	"pmsCd": "FCM",
	"reqUser": "X0122512",
	"projectId": "lab-notification-c8723",
	"certification": 
	{
	  "type": "service_account",
	  "project_id": "lab-notification-c8723",
	  "private_key_id": "bfea1c2872b74eda674ac7a783360196d586733b",
	  "private_key": "......",
	  "client_email": "firebase-adminsdk-u59tc@lab-notification-c8723.iam.gserviceaccount.com",
	  "client_id": "110450656369097039927",
	  "auth_uri": "https://accounts.google.com/o/oauth2/auth",
	  "token_uri": "https://oauth2.googleapis.com/token",
	  "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
	  "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-u59tc%40lab-notification-c8723.iam.gserviceaccount.com"
	}
}
```

#### 2. Firebase 서비스 계정 비공개 키 조회(리스트)
```GET http://[ServerIp]:[ServerPort]/[context-path]/push/certifications/{page}/{size}```

#### 3. Firebase 서비스 계정 비공개 키 조회(단건)
```GET http://[ServerIp]:[ServerPort]/[context-path]/push/certification/{appId}```

#### 4. Firebase 서비스 계정 비공개 키 수정 
```PUT http://[ServerIp]:[ServerPort]/[context-path]/push/certification/{appId}```
```Content-Type:application/json```
```
{
  "appType": "Web",
  "pmsCd": "FCM",
	"reqUser": "X0122512",
	"projectId": "lab-notification-c8723",
  "certification": {...}
}
```

#### 5. Firebase 서비스 계정 비공개 키 삭제
```DELETE http://[ServerIp]:[ServerPort]/[context-path]/push/certification/{appId}```

      
### App(Client) 토큰 관리

#### 1. Token 등록
```POST http://[ServerIp]:[ServerPort]/[context-path]/push/token```
```
Content-Type:application/json
{
    "appId": "LABPUSH001",
    "accountId": "thiago@sk.com",
    "token": "eMM3EjG4J7K3dla...Tzog4Qp7UYG-A"
}
```

#### 2. Token 조회(리스트 by appId)
```GET http://[ServerIp]:[ServerPort]/[context-path]/push/token/{appId}/{page}/{size}```  

#### 3. Token 조회(리스트 by appId, accountId)
```GET http://[ServerIp]:[ServerPort]/[context-path]/push/token/{appId}/{accountId}```  

#### 4. Token 조회(리스트 by appId, accountId, page, size)
```GET http://[ServerIp]:[ServerPort]/[context-path]/push/token/{appId}/{accountId}/{page}/{size}```  


#### 5. Token 사용 여부를 사용 or 미사용 으로 변경(by appId, accountId)
```PUT http://[ServerIp]:[ServerPort]/[context-path]/push/token/{appId}/{accountId}```
```
Content-Type:application/json
{
    "token" : "eMM3EjG4J7K3dla...Tzog4Qp7UYG-A",  -- optional
    "useYn" : "N"
}
```

#### 6. Token 삭제(by appId, accountId)
```DELETE http://[ServerIp]:[ServerPort]/[context-path]/push/token/{appId}/{accountId}```

#### 7. Token 삭제(by appId, accountId, token)
```DELETE http://[ServerIp]:[ServerPort]/[context-path]/push/token/{appId}/{accountId}/{token}```

   

### Push 발송 요청 

#### 1. 사용자 별 Push 발송 요청
```POST http://[ServerIp]:[ServerPort]/[context-path]/push/transmit/{appId}/{accountId}```
```
Content-Type:application/json
{
    "notification": {
      "title": "Lab Message Title", 
      "body": "Lab Message Body",
      "image" : "https://lab-web-notification.firebaseapp.com/jane.png"   -- optional
    },
    "data" : {  -- optional
      "name": "wrench",
      "mass": "1.3kg", 
      "count": "3" 
    }
}
```

#### 2. 앱 별 Push 발송 요청 
```POST http://[ServerIp]:[ServerPort]/[context-path]/push/transmit/{appId}```
```
Content-Type:application/json
{
    "notification": {
      "title": "Lab Message Title", 
      "body": "Lab Message Body",
      "image" : "https://lab-web-notification.firebaseapp.com/jane.png"   -- optional
    },
    "data" : {  -- optional
      "name": "wrench",
      "mass": "1.3kg", 
      "count": "3" 
    }
}
```

#### 3. 주제 별 Push 발송 요청
```POST http://[ServerIp]:[ServerPort]/[context-path]/push/transmit/{appId}/{topic}```
```
Content-Type:application/json
{
    "notification": {
      "title": "Lab Message Title", 
      "body": "Lab Message Body",
      "image" : "https://lab-web-notification.firebaseapp.com/jane.png"   -- optional
    },
    "data" : {  -- optional
      "name": "wrench",
      "mass": "1.3kg", 
      "count": "3" 
    }
}
```


### 로그 관리

#### 1. PUSH 발송 요청 로그 조회(단건) 
```GET http://[ServerIp]:[ServerPort]/[context-path]/push/trace/{transReqId}```

#### 2. PUSH 발송 요청 로그 조회(리스트 by 요청일자) 
```GET http://[ServerIp]:[ServerPort]/[context-path]/push/trace/{reqDt}/{pageNo}/{size}```

   