# Notification Provider Project 

## REST API 명세서

### FCM push transmit 
| index | Method | URI | Description | 
| :---: | --- | --- | --- |
| 1 | POST    | `/fcm/transmit/token` | pms 메시지 발송 |
   
---   
## REST API 상세
```
http://localhost:4000/lab-notification-provider/fcm/transmit/token
{
	"transmitReqId": "transReqId01",
	"projectId": "lab-notification-c8723",
	"token": "dPYzmClRNsjP9UE60B0mfL:APA91bF9MWTBXfg-AzkBzHT5OSK-zwoEBQz-hdZI-ef2lwiawMvfWFBlXPNzP-Ke8GPE5_LF0kI1c35f3KdLj1M3iBApaixEAqCDj3unYxCn9L98NIhpKPGg-inAmCYbEIsBgV_X8h8F",
	"certification": {
		"type": "......",
		"project_id": "......",
		"private_key_id": "......",
		"private_key": "......",
		"client_email": "......",
		"client_id": "......",
		"auth_uri": "......",
		"token_uri": "......",
		"auth_provider_x509_cert_url": "......",
		"client_x509_cert_url": "......"
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
