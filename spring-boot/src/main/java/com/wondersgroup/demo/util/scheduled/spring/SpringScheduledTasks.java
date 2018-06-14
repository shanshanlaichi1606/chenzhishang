//package com.wondersgroup.demo.util.scheduled.spring;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import javax.annotation.Resource;
//
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.wondersgroup.demo.service.LoginUserService;
//import com.wondersgroup.demo.util.redis.RedisService;
//
///**
// * spring 定时任务
// * 
// * @author chen
// *
// */
//@Component
//@Configurable
//@EnableScheduling
//public class SpringScheduledTasks {
//	@Resource
//	private LoginUserService loginUserService;
//	@Resource
//	private RedisService redisService;
//
//	// 30秒执行一次  默认容器启动立刻执行一次
//	@Scheduled(fixedRate = 1000 * 30)
//	public void reportCurrentTime() {
//		redisService.del("selectAll");
//		System.err.println("30秒执行一次: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//	}
//	
//	//xx:30秒执行一次  
//	@Scheduled(cron = " 30 * * * * *  ")
//	public void reportCurrentTimeCor() {
//		redisService.del("selectAll");
//		System.err.println("30秒执行一次: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//	}
//
//	// 每1分钟执行一次 
//	@Scheduled(cron = "0 */1 *  * * * ")
//	public void reportCurrentByCron() {
//		System.err.println("每1分钟执行一次 : " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//	}
//
//}
