package kr.pe.hyeonkyun.lab.api.admin.web.controller;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AliveController {

    @Value("${service.mode}")
    private String serviceMode;

    @RequestMapping( value = "/alive", method = RequestMethod.GET )
    public Map<String, Object> aliveCheck(HttpServletRequest request ) throws Exception {

        Map<String, Object> responseBody = new HashMap<String, Object>();
        responseBody.put("responseCode", 1000);
        responseBody.put("responseMsg", "OK");
        responseBody.put("serverMode", serviceMode);

        return responseBody;
    }
}
