package kr.pe.hyeonkyun.notification.provider.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class ProxyTaskManager {

	private final TaskExecutor transmitThreadPoolTaskExecutor ;

	@Autowired
	ProxyTaskManager(TaskExecutor transmitThreadPoolTaskExecutor) {
		this.transmitThreadPoolTaskExecutor = transmitThreadPoolTaskExecutor;
	}

	public void execute( ProxyTask _task ) {
		transmitThreadPoolTaskExecutor.execute( _task ) ;
	}	
}
