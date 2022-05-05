package kr.pe.jane.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@ComponentScan( "kr.pe.jane.notification" )
public class LabNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabNotificationApplication.class, args);
	}
}
