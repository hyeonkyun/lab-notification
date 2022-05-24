package kr.pe.jane.lab.notification.provider.callback.task;

import javax.inject.Inject;

import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class CallbackTaskManager {

	@Inject
	private TaskExecutor transmitThreadPoolTaskExecutor ;
	
	public void execute( CallbackTask _task ) {
		transmitThreadPoolTaskExecutor.execute( _task ) ;
	}
}
