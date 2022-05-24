# Notification Provider Project 
---
---
## RESTAPI 명세서
### FCM push transmit 
| index | Method | URI | Description | 
| :---: | --- | --- | --- |
| 1 | POST    | `/push/transmit/{appId}/{accountId}` | 사용자 별 Push 발송 요청 |
<br>

---
---
## RESTAPI 정의서
### Firebase 서비스 계정 비공개 키 관리
#### 1. Firebase 서비스 계정 비공개 키 등록  
```POST http://[ServerIp]:[ServerPort]/[context-path]/push/certification```  
```Content-Type:application/json```  
```
http://localhost:4000/lab-notification-provider/fcm/transmit/token
{
	"transmitReqId": "transReqId01",
	"projectId": "lab-notification-c8723",
	"token": "dPYzmClRNsjP9UE60B0mfL:APA91bF9MWTBXfg-AzkBzHT5OSK-zwoEBQz-hdZI-ef2lwiawMvfWFBlXPNzP-Ke8GPE5_LF0kI1c35f3KdLj1M3iBApaixEAqCDj3unYxCn9L98NIhpKPGg-inAmCYbEIsBgV_X8h8F",
	"certification": {
		"type": "service_account",
		"project_id": "lab-notification-c8723",
		"private_key_id": "bfea1c2872b74eda674ac7a783360196d586733b",
		"private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDE2or/tbmRj1ln\nD6jnWvH7rwtA8CcWOK0ZYirvq/m6VofQcuszwtwNaAuOztMQc/za83u5y+smzcen\ny1O91EiJ9DGZDCMaiSWEZqHI5HN7MDzQJYJ6D44A0qAzNR7u87Zcl5Vsajtqh2b6\nfvNOhUKmb76CUatDZ6s3ejT1jL8ueRkBgysQOR3BUN4trNvJPXTPT2n0fV4mIQgS\nRl/lVKHQARmdMpP8fdzCbFM/k8r64mUg+6vJAruHLJy3ZFvcIm+YJZ3zIQz4G3bK\n5T579z0M243zx8ws7fPetgw3WDboO9oJQ1PIa4S6vJ5pCUydebHC72djec0xGIX5\n//XCubU9AgMBAAECggEAIN77afKu74L341FGm4dgs+3JW8keRcQmB4CQeeAGPdrY\nJ/Mgmk6WDzkKAQlBTlSH6wnQZbiK5jpjdf3qWF770+luZMeVjwdKt/6pUlHvhKkL\n5pf03yv8wt9o99QNe8o/b9uyDF362PGOPRL4zupKtF8heUJo7kL/4K4r7LrZa8lq\nvOyxCS0qr/7zapg5pSFZ7rgu10lKNcTiCFWzzYNZHvwU0EHBc6KWMBMLWGpsi6FP\nNJCBUEtFewyVE9DWTMIyERv3cLrRZkuoQqdQu4zMz+zrzRpjbYsbs0tCCkV37O2v\nLMRKyauslkHVGAkBafHu0JyjjBGCq3CDNDf5C/2rMwKBgQDxK0IaRnrXGR4TVy1B\nnXC32t+iR1ggiYlKXH8IjuHBLhtARAloDrKZCiwrkMFpFXYr73OONzn+hzhBC1wa\nWDtzj3IwBrYA6wUxRLIG/JLQ2mh+eQbJsGiKBvZgq1s8nqb7U90HXObUKZwDwDAK\n0SZLDx99dQzaoUIq+hzyjMO0hwKBgQDQ9aArlnMbLXPlkyugvHYf3raxsBEHgKjS\nj0q5XEsePdGAfoSEE2iq7OQPIgyAwAhZ8ku6yTkodY+OIgEqSKvu6kcVh2bSVpYK\nzQIwHs1jxTX/7zMDol63RobUS+o2ROl9xQbdsM8Vsij1eUzgpg8BifCi/9EcOctG\njHxMxUK9GwKBgCYaAX2JOQsWqsu/cCT3rME/USmgP+oUEJ6w8lj1yfDtSdzjYuEP\nwWOwWb4gRo5Fl7/lOspzKzRtRJPyKYD4A5yEQzQXO1X1y4I3KjE0YjWg6/VfRZVD\ny6BS7UXkjKuNHTCD1V2+bbGDSWTGTlDHLleZcg4uGfPIml2T2lLvrlOtAoGBAMpJ\nPqlzmDLzKYNRmaadS21G2W3imFm+/6/278vJwR6hkbLmx7pkX6s4cP8FYz0wqeUJ\nXq6QJjk4taRO3vnkKC29ttrsnWl6UP5bwIZ9CzBOthvxoF0gTrj2GYhjhXTL+Y27\nsw3iIFdMhcI9j2La3yQKOV5ScEj22gaN8bcQL6+JAoGAbzDghtHgQgn+MYBCQne6\nwMARhkNNF69n5yFmft+8vSsg4QgGjozitcAw6Gte9qa1TA1FKEoBAQ2+CQ+qZ7DT\nuQJ60Uds8tIO2JKzfRaXxi5YUuoUqRC7AyHnZ2iZn5UDZDpSasviMS0p5rgN30yD\n8yiN/y6Bnm5fJO5PDlb11RQ=\n-----END PRIVATE KEY-----\n",
		"client_email": "firebase-adminsdk-u59tc@lab-notification-c8723.iam.gserviceaccount.com",
		"client_id": "110450656369097039927",
		"auth_uri": "https://accounts.google.com/o/oauth2/auth",
		"token_uri": "https://oauth2.googleapis.com/token",
		"auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
		"client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-u59tc%40lab-notification-c8723.iam.gserviceaccount.com"
	},
	"notification": {
		"title": "Lab Message Title",
		"body": "Lab Message Body",
		"image": "https://lab-web-notification.firebaseapp.com/jane.png"
	},
	"data": {
		"name": "wrench",
		"mass": "1.3kg",
		"count": "3"
	},
	"callbackUrl": "http://localhost:9090/lab-notification/callback/provider"
}
```