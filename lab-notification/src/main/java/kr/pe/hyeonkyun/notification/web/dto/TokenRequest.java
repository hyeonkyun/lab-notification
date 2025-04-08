package kr.pe.hyeonkyun.notification.web.dto;

import lombok.Data;

@Data
public class TokenRequest {
    private String refreshToken;
}