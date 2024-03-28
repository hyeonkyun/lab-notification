# Push Admin Web 프로젝트 

## 기술 요소
- nodejs
- express
- ejs
- maria db
- bootstrap, edminlte

## 프로젝트 셋팅
1. 프로젝트 기본 골격 셋팅
```
express --view ejs 
npm install
npm audit fix --force
```

2. nodemon 설치 및 인스턴스 구동 확인
```
npm install -g nodemon
"devstart": "nodemon ./bin/www" 패키지 추가
npm run devstart
```

4. adminlte 다운로드(adminlte.io) 및 public 으로 복사
current : AdminLTE-3.2.0   

3. 패키지 설치
- Firebase Admin SDK  
```npm install firebase-admin --save```   
- passport   
```npm install passport passport-local passport-kakao bcrypt```   
- express-session   
```npm install express-session```
- Flash message   
```npm install -s connect-flash```
- mysql   
```npm install mysql```
- moment   
```npm install moment```
```npm install moment-timezone```


## 참고
- https://nodejs.org/docs/latest/api/
- https://compmath.korea.ac.kr/nodejs/Node_Express.html   
- https://vasundhara.io/blogs/crud-app-using-express-and-mysql   
- https://noonestaysthesame.tistory.com/entry/Firebase-FCM%EC%84%9C%EB%B2%84%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%ED%91%B8%EC%8B%9C-%EC%95%8C%EB%A6%BC-%EB%B3%B4%EB%82%B4%EA%B8%B0-1   
- https://inpa.tistory.com/entry/NODE-📚-Passport-모듈-그림으로-처리과정-💯-이해하자   
- https://inpa.tistory.com/entry/NODE-📚-bcrypt-모듈-원리-사용법#비밀번호_암호화하기
- https://inpa.tistory.com/372
- https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=pjok1122&logNo=221566039764    
- https://opentutorials.org/module/938/7371
- https://maivve.tistory.com/125

