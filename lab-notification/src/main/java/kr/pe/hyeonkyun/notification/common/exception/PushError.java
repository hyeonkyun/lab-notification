package kr.pe.hyeonkyun.notification.common.exception;

public class PushError {
	
	public static final String OK							= "1000" ;
	public static final String PARTIAL_OK					= "1001" ;

	//	information
	public static final String UNKNOWN_USER					= "2001" ;
	public static final String UNKNOWN_DEVICE				= "2002" ;

	public static final String CERTIFICATION_NOT_FOUND		= "2101" ;
	public static final String TOKEN_NOT_FOUND				= "2102" ;

	//	operation
	public static final String INTERNAL_ERROR				= "3000" ;
	public static final String OPERATION_TIMEOUT			= "3101" ;

	//  interface fail
	public static final String CONNECTION_FAILED			= "3201" ;
	public static final String AUTHENTICATION_FAILED		= "3211" ;

	//	parameter
	public static final String INVALID_CERTIFICATION		= "4001" ;
	public static final String INVALID_PARAMETER			= "4002" ;
	
	public static final String PARAMETER_MISSING			= "4011" ;
	
	public static final String TOKEN_DUPLICATED				= "4021" ;	
	public static final String CERTIFICATION_DUPLICATED		= "4022" ;

	//	push messaging server
	public static final String PMS_ERROR					= "5000" ;
	public static final String NOT_REGISTERED_GROUP			= "5101" ;

	//	etc
	public static final String UNKNOWN						= "9999" ;

	public static String toMessage( String resultCode ) {
		switch (resultCode) {
		case OK							:	return "ok" ;
		case PARTIAL_OK					:	return "partial ok" ;

		case UNKNOWN_USER				:	return "unknown user" ;
		case UNKNOWN_DEVICE				:	return "unknown device" ;

		case CERTIFICATION_NOT_FOUND	:	return "certification not found" ;
		case TOKEN_NOT_FOUND			:	return "token not found" ;

		case INTERNAL_ERROR				:	return "internal error" ;

		case OPERATION_TIMEOUT			:	return "operation timeout" ;

		case CONNECTION_FAILED			:	return "connection failed" ;

		case AUTHENTICATION_FAILED		:	return "authentication failed" ;

		case INVALID_CERTIFICATION		:	return "invalid certification" ;
		case INVALID_PARAMETER			:	return "invalid parameter" ;
		case PARAMETER_MISSING			:	return "parameter missing" ;
		case TOKEN_DUPLICATED			:	return "token duplicated" ;
		case CERTIFICATION_DUPLICATED	:	return "certification duplicated" ;

		case PMS_ERROR					:	return "pms error" ;

		case NOT_REGISTERED_GROUP		:	return "not registered group" ;

		case UNKNOWN					:	return "unknown error" ;	
		default 						:	return "unknown error" ;
		}		
	}	
}
