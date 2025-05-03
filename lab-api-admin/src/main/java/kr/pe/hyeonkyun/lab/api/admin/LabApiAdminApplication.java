package kr.pe.hyeonkyun.lab.api.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan(basePackages = "kr.pe.hyeonkyun.lab.api.admin.domain.mapper")
public class LabApiAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabApiAdminApplication.class, args);
    }
}
