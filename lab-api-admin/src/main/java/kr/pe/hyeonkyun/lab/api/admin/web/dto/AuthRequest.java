package kr.pe.hyeonkyun.lab.api.admin.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class AuthRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 2778655234760340035L;

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    private String reqReason;
}
