package kr.pe.hyeonkyun.notification.provider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class LabNotificationProviderConfig {

	@Bean(name = "transmitThreadPoolTaskExecutor")
	public TaskExecutor transmitPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

		executor.setCorePoolSize(64);
		executor.setMaxPoolSize(512);
		executor.setQueueCapacity(100000);
		executor.setThreadNamePrefix("PushTask-");
		executor.initialize();

		return executor;
	}
	
	@Bean(name = "callbackThreadPoolTaskExecutor")
	public TaskExecutor callbacktPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

		executor.setCorePoolSize(64);
		executor.setMaxPoolSize(512);
		executor.setQueueCapacity(100000);
		executor.setThreadNamePrefix("CallbackTask-");
		executor.initialize();

		return executor;
	}
}
