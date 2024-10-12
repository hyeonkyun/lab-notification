package kr.pe.hyeonkyun.notification;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@MapperScan(basePackages = "kr.pe.hyeonkyun.notification.domain.repository")
public class LabNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabNotificationApplication.class, args);
	}
}
