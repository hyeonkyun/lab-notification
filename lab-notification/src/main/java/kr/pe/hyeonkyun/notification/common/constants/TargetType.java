package kr.pe.hyeonkyun.notification.common.constants;

public enum TargetType {
	TOKEN, BATCH, TOPIC, CONDITION;

	public static String toMessage( TargetType targetType ) {
		switch ( targetType ) {
		case TOKEN:		return "token";
		case BATCH:		return "batch";
		case TOPIC:		return "topic";
		case CONDITION:	return "condition";
		default:		return "";
		}
	}

}
