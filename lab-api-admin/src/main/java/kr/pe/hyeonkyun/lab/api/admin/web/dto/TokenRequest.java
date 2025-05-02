package kr.pe.hyeonkyun.lab.api.admin.web.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class TokenRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -2788372772086422607L;

    private String refreshToken;
}
