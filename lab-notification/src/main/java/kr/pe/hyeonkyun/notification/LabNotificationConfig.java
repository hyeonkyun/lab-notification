package kr.pe.hyeonkyun.notification;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.pe.hyeonkyun.notification.interceptor.LoggingInterceptor;

@Configuration
public class LabNotificationConfig implements WebMvcConfigurer {

	@Inject
	LoggingInterceptor loggingInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor( loggingInterceptor );
	}
	
	@Bean(name = "transmitThreadPoolTaskExecutor")
	public TaskExecutor transmitPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

		executor.setCorePoolSize(64);
		executor.setMaxPoolSize(512);
		executor.setQueueCapacity(100000);
		executor.setThreadNamePrefix("PushTask-");
		executor.initialize();
		
//		ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
//        return new ExecutorServiceTaskExecutor(executorService);

		return executor;
	}
}
