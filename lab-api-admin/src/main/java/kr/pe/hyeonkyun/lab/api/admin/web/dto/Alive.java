package kr.pe.hyeonkyun.lab.api.admin.web.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class Alive implements Serializable {

    @Serial
    private static final long serialVersionUID = 2868210232929931052L;

    private String msg;
}
