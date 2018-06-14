package com.wondersgroup.demo.util.scheduled.quartz;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wondersgroup.demo.entity.ScheduleJob;
import com.wondersgroup.util.TimeOrDateUtils;

@DisallowConcurrentExecution
public class QuartzJobFactory implements Job {
	private static final Logger logger = LoggerFactory.getLogger(QuartzJobFactory.class.getName());

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
		ScheduleJob scheduleJob = (ScheduleJob) mergedJobDataMap.get("scheduleJob");
		//ScheduleJobMapper scheduleJobMapper = 	(ScheduleJobMapper) mergedJobDataMap.get("scheduleJobMapper");
		logger.info(TimeOrDateUtils.formateDate(new Date())+"定时任务开始执行，任务名称[" + scheduleJob.getJobName() + "] :"+scheduleJob.getCronExpression());
		// 执行业务逻辑
	}
}
