package kr.pe.hyeonkyun.notification.provider.web.controller;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.hyeonkyun.notification.provider.common.exception.PushError;
import kr.pe.hyeonkyun.notification.provider.service.IPushTransmitService;
import kr.pe.hyeonkyun.notification.provider.web.dto.PushTransmitParam;
import kr.pe.hyeonkyun.notification.provider.web.dto.PushTransmitResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/fcm/transmit")
public class PushTransmitController {

    private final IPushTransmitService pushTransmitService;

    @Autowired
    public PushTransmitController(IPushTransmitService pushTransmitService) {
        this.pushTransmitService = pushTransmitService;
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PushTransmitResponse pushTransmit(@RequestBody PushTransmitParam pushTransmitParam, HttpServletRequest request) throws Exception {
        Map<String, Object> responseBody = new HashMap<String, Object>();

        log.info(pushTransmitParam.toString());

        pushTransmitService.transmit(pushTransmitParam);

        return new PushTransmitResponse(PushError.OK, PushError.toMessage(PushError.OK), responseBody);
    }
}
	