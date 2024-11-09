package kr.pe.hyeonkyun.notification.provider.callback.task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class CallbackTaskManager {

	private final TaskExecutor transmitThreadPoolTaskExecutor ;

	@Autowired
	CallbackTaskManager(TaskExecutor transmitThreadPoolTaskExecutor) {
		this.transmitThreadPoolTaskExecutor = transmitThreadPoolTaskExecutor;
	}

	
	public void execute( CallbackTask _task ) {
		transmitThreadPoolTaskExecutor.execute( _task ) ;
	}
}
