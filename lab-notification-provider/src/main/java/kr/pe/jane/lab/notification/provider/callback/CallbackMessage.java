package kr.pe.jane.lab.notification.provider.callback;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallbackMessage {

	private String transmitReqId ;
	
	private String token ;
	
	private int responseStatusCd ;
	
	private String responseData ;
}
