# Notification Management Project 
## RESTAPI 명세
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


<br><br>  

---
<br>


## RESTAPI 정의 
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

<br><br>

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

<br><br>

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


<br><br>  

---
---
백업 라인 이하 설계 완료 후 제거
<br><br>  




#### 4. 로그 관리
```
4.1. PUSH 발송 로그 조회 
GET http://[ServerIp]:[ServerPort]/[context-path]/push/trace/[{transReqId}]

POST http://[ServerIp]:[ServerPort]/[context-path]/push/trace/[{appId}]
{
    "fromDate" : "2021-02-01",
    "toDate" : "2021-02-02",
    "pageNo" : "1", 
    "size" : "10"
}
```

### Database 설계 ( mariadb 기준 )

#### 1. PMS 서버 키 관리 테이블
```
-- DROP TABLE TB_PUSH_CERTIFICATION ;
CREATE TABLE `TB_PUSH_CERTIFICATION` (
  `APP_ID` varchar(10) NOT NULL PRIMARY KEY COMMENT '앱 ID',
  `PMS_CD` varchar(10) NOT NULL COMMENT '푸쉬 메시지 서비스 제공자 코드(FCM|...)',
  `PROJECT_ID` varchar(50) NOT NULL COMMENT 'PMS 프로젝트 ID',
  `CERTIFICATION` text NOT NULL COMMENT 'PMS 계정 비공개 키',
  `APP_TYPE` varchar(10) NULL COMMENT '앱 타입(Android | iOS | Web)',
  `INS_USER` varchar(50) NULL,
  `INS_DT` datetime NULL,
  `UPD_USER` varchar(50) NULL,
  `UPD_DT` datetime NULL
) ENGINE=InnoDB COMMENT='푸쉬 서비스 계정 비공개 키 정보'
```

#### 2. Push 클라이언트 토큰 관리 테이블
```
-- DROP TABLE TB_PUSH_TOKEN ;
CREATE TABLE `TB_PUSH_TOKEN` (
  `APP_ID` varchar(10) NOT NULL COMMENT '앱 ID',
  `ACCOUNT_ID` varchar(50) NOT NULL COMMENT '계정 ID',
  `TOKEN` varchar(250) NOT NULL COMMENT 'TOKEN',
  `USE_YN` char(1) NOT NULL DEFAULT 'Y',
  `REG_DT` datetime NOT NULL DEFAULT current_timestamp(),
  `UPD_DT` datetime DEFAULT NULL,
  PRIMARY KEY (`APP_ID`,`ACCOUNT_ID`,`TOKEN`)
) ENGINE=InnoDB COMMENT='푸쉬 토큰 정보' ;
```
#### 3. Push 발송 요청 로그 관리 테이블
```
-- DROP TABLE TB_PUSH_TRANSMIT_REQ
CREATE TABLE `TB_PUSH_TRANSMIT_REQ` (
  `TRANSMIT_REQ_ID` varchar(50) PRIMARY KEY COMMENT '발송_요청_ID', 
  `APP_ID` varchar(10) NOT NULL COMMENT '앱 ID',
  `TRANSMIT_REQ_PARAM` TEXT NOT NULL COMMENT '발송 요청 메시지',
  `REQ_IP` varchar(20) DEFAULT NULL,
  `REQ_DT` datetime NOT NULL DEFAULT current_timestamp(),
  `ERR_MSG` TEXT DEFAULT NULL
) ENGINE=InnoDB COMMENT='푸쉬 발송 요청 수신 정보(legacy to provider)' ;

-- DROP TABLE TB_PUSH_TRANSMIT
CREATE TABLE `TB_PUSH_TRANSMIT` (
  `TRANSMIT_REQ_ID` varchar(50) NOT NULL COMMENT '발송_요청_ID',
  `TARGET` varchar(250) DEFAULT NULL COMMENT '타겟',
  `TARGET_TYPE` varchar(10) DEFAULT NULL COMMENT '타겟_타입(token|batch|topic|condition)',
  `ACCOUNT_ID` varchar(50) DEFAULT NULL COMMENT '계정_ID',
  `REQ_DATA` TEXT DEFAULT NULL COMMENT '요청_데이터',  
  `RES_STATUS_CD` smallint DEFAULT NULL COMMENT '응답_상태_코드',
  `RES_DATA` TEXT DEFAULT NULL COMMENT '응답_데이터',
  `REQ_DT` datetime NOT NULL DEFAULT current_timestamp(),
  `END_DT` datetime DEFAULT NULL,
  PRIMARY KEY (`TRANSMIT_REQ_ID`, `TARGET`)
) ENGINE=InnoDB COMMENT='푸쉬 발송 요청 정보(provider to fcm)' ;
```

---
## TODO List
```
1. 공통 에러 정의 v
2. 발송 로그 CRUD 방향 설계 v
3. 목록 조회 페이징 처리 v
4. data 페이로드 추가 v
5. notificatino 이미지 알림 추가 v
------------- 1.0 release -------------

TODO 1.x ~ 2.0 :
* web custom messege 처리 설계 v
* android custom messege 처리 설계
* ios custom messege 처리 설계 
* APP(여러 사용자) 기준 Push batch 발송 요청 (multipart)
* APP(토픽) 기준 Push 발송 요청 
------------- 2.0 release -------------

etc
- 토큰 정리(배치) 룰 설계
- xmpp(upstream)
- fcm 외 pms(apns direct, baidu), ...? 
```

---
## back up line ( 설계 마무리 후 제거~ )
```
maria 5.5 이하 스키마

CREATE TABLE `TB_PUSH_TOKEN` (
  `APP_ID` varchar(10) NOT NULL COMMENT '앱 ID',
  `ACCOUNT_ID` varchar(50) NOT NULL COMMENT '계정 ID',
  `TOKEN` varchar(250) NOT NULL COMMENT 'TOKEN',
  `USE_YN` char(1) NOT NULL DEFAULT 'Y',
  `REG_DT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPD_DT` datetime DEFAULT NULL,
  PRIMARY KEY (`APP_ID`,`ACCOUNT_ID`,`TOKEN`)
) ENGINE=InnoDB COMMENT='푸쉬 토큰 정보' ;

CREATE TABLE `TB_PUSH_TRANSMIT_REQ` (
  `TRANSMIT_REQ_ID` varchar(50) PRIMARY KEY COMMENT '발송_요청_ID', 
  `APP_ID` varchar(10) NOT NULL COMMENT '앱 ID',
  `TRANSMIT_REQ_PARAM` TEXT NOT NULL COMMENT '발송 요청 메시지',
  `REQ_IP` varchar(20) DEFAULT NULL,
  `REQ_DT` datetime DEFAULT NULL,
  `ERR_MSG` TEXT DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='푸쉬 발송 요청 수신 정보(legacy to provider)' ;

CREATE TABLE `TB_PUSH_TRANSMIT` (
  `TRANSMIT_REQ_ID` varchar(50) NOT NULL COMMENT '발송_요청_ID',
  `TARGET` varchar(250) DEFAULT NULL COMMENT '타겟',
  `TARGET_TYPE` varchar(10) DEFAULT NULL COMMENT '타겟_타입(token|batch|topic|condition)',
  `ACCOUNT_ID` varchar(50) DEFAULT NULL COMMENT '계정_ID',
  `REQ_DATA` TEXT DEFAULT NULL COMMENT '요청_데이터',  
  `RES_STATUS_CD` smallint DEFAULT NULL COMMENT '응답_상태_코드',
  `RES_DATA` TEXT DEFAULT NULL COMMENT '응답_데이터',
  `REQ_DT` datetime DEFAULT NULL,
  `END_DT` datetime DEFAULT NULL,
  PRIMARY KEY (`TRANSMIT_REQ_ID`, `TARGET`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='푸쉬 발송 요청 정보(provider to fcm)' ;

common push body
{
    "notification": {
      "title": "Lab Message Title", 
      "body": "Lab Message Body",
      "image" : "https://lab-web-notification.firebaseapp.com/jane.png"   
    },
    "data" : { 
      "name": "wrench",
      "mass": "1.3kg", 
      "count": "3" 
    }
}

webpush body
webpush.headers.TTL(time_to_live ) : 메시지의 최대 수명을 지정할 수 있습니다. 
  값의 길이가 0초에서 2,419,200초(28일) 사이여야 하며 FCM이 메시지를 저장하여 전송 시도하는 최대 기간에 해당합니다. 
  이 필드가 포함되지 않은 요청은 최대 기간인 4주가 기본값입니다.
  
  {
    "notification": {
      "title": "Lab Message Title",
      "body": "Lab Message Body",
      "image" : "https://lab-web-notification.firebaseapp.com/jane.png" 
    },
    "data" : { 
      "name": "wrench",
      "mass": "1.3kg", 
      "count": "3" 
    },
    "webpush": {
      "headers": {
        "TTL":"4500"
      },
      "fcm_options" : {
        "link": "https://lab-web-notification.firebaseapp.com/jane.png",
        "analytics_label": "webpush_label"
      }
    }
  }

```

## proxy api
```
http://localhost:9090/lab-notification/push/transmit/provider/LABPUSH001/X0122512

{
  "notification": {
    "title": "Lab Message Title",
    "body": "Lab Message Body",
    "image" : "https://lab-web-notification.firebaseapp.com/jane.png" 
  },
  "data" : { 
    "name": "wrench",
    "mass": "1.3kg", 
    "count": "3" 
  },
  "webpush": {
    "headers": {
      "TTL":"4500"
    },
    "fcm_options" : {
      "link": "https://lab-web-notification.firebaseapp.com/jane.png",
      "analytics_label": "webpush_label"
    }
  }
}
```