package kr.pe.hyeonkyun.notification.provider.task;

import javax.inject.Inject;

import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class ProxyTaskManager {
	
	@Inject
	private TaskExecutor transmitThreadPoolTaskExecutor ;
	
	public void execute( ProxyTask _task ) {
		transmitThreadPoolTaskExecutor.execute( _task ) ;
	}	
}
