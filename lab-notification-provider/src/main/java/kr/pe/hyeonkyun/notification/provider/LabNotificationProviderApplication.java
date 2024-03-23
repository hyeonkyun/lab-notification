package kr.pe.hyeonkyun.notification.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@ComponentScan( "kr.pe.hyeonkyun.notification.provider" )
public class LabNotificationProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabNotificationProviderApplication.class, args);
	}

}
