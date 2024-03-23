# Database 설계 ( mariadb 기준 )

```   
CREATE DATABASE lab;

CREATE USER 'labuser'@'%' IDENTIFIED BY 'labuser23!';

GRANT ALL PRIVILEGES ON lab.* TO 'labuser'@'%';

ALTER DATABASE lab CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

FLUSH PRIVILEGES;
```   
   
1. PMS 서버 키 관리 테이블
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
   
2. Push 클라이언트 토큰 관리 테이블
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
   
3. Push 발송 요청 로그 관리 테이블
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
```
maria 5.5 이하 스키마 back up

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
```
