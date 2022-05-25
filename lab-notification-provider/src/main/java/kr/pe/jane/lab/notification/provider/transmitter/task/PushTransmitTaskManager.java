package kr.pe.jane.lab.notification.provider.transmitter.task;

import javax.inject.Inject;

import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class PushTransmitTaskManager {

	@Inject
	private TaskExecutor callbackThreadPoolTaskExecutor ;
	
	public void execute( PushTransmitTask _task ) {
		callbackThreadPoolTaskExecutor.execute( _task ) ;
	}
}
