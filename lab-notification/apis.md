# notification apis 

1. Firebase 서비스 계정 비공개 키 관리( Firebase Servcie Account private key Manager )
1.1. Key 등록
`POST http://[ServerIp]:[ServerPort]/[context-path]/push/certification
`Content-Type:application/json
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

1.2. Key 조회
`GET http://[ServerIp]:[ServerPort]/[context-path]/push/certifications/[{page}]/[{size}]
`GET http://[ServerIp]:[ServerPort]/[context-path]/push/certification/[{appId}]

1.3. Key 수정
`PUT http://[ServerIp]:[ServerPort]/[context-path]/push/certification/[{appId}]
`Content-Type:application/json
```
{
  "appType": "Web",
  "pmsCd": "FCM",
	"reqUser": "X0122512",
	"projectId": "lab-notification-c8723",
  "certification": {...}
}
```

1.4. Key 삭제
`DELETE http://[ServerIp]:[ServerPort]/[context-path]/push/certification/[{appId}]
