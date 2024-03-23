package kr.pe.hyeonkyun.notification.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class IdGeneratorUtils {
	
	public static String makeTransmitReqId() {
		return new SimpleDateFormat("yyMMdd-HHmmss-").format(new Date()) + UUID.randomUUID().toString();
	}	
}
