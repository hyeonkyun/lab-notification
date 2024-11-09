package kr.pe.hyeonkyun.notification.provider.transmitter.task;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class PushTransmitTaskManager {

	private final TaskExecutor callbackThreadPoolTaskExecutor ;

	@Autowired
    public PushTransmitTaskManager(TaskExecutor callbackThreadPoolTaskExecutor) {
        this.callbackThreadPoolTaskExecutor = callbackThreadPoolTaskExecutor;
    }

    public void execute( PushTransmitTask _task ) {
		callbackThreadPoolTaskExecutor.execute( _task ) ;
	}
}
