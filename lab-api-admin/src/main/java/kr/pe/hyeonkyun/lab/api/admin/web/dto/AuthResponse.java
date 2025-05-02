package kr.pe.hyeonkyun.lab.api.admin.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class AuthResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -7177559533064096403L;

    private String accessToken;

    private String refreshToken;
}
