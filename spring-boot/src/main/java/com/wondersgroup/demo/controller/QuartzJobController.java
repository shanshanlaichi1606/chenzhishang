package com.wondersgroup.demo.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wondersgroup.demo.entity.ScheduleJob;
import com.wondersgroup.demo.service.LoginUserService;
import com.wondersgroup.demo.util.scheduled.quartz.ScheduleService;

@RestController
@RequestMapping("/quartz")
public class QuartzJobController {
	@Resource
	private LoginUserService loginUserService;
	
	@Resource
	private ScheduleService scheduleService;
	
	/**
	 * 添加任务
	 */
	@GetMapping("/add")
	public void addScheduleJob(){
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setJobName("测试人员");
		scheduleJob.setJobGroup("1组");
		scheduleJob.setCronExpression("1/10 * * * * ? * ");
		scheduleJob.setStatus("1");
		scheduleJob.setDescription("10秒执行一次");
		scheduleJob.setCreateDate(new Date());
		scheduleJob.setModifyDate(new Date());
		scheduleJob.setStartDate(new Date());
		scheduleJob.setJobSql("");
		scheduleService.addJob(scheduleJob);
	}
	/**
	 * 修改
	 */
	@GetMapping("/update/{id}")
	public void updateScheduleJob(@PathVariable int id){
		
		ScheduleJob scheduleJob =scheduleService.selectByPrimaryKey(id);
		scheduleJob.setCronExpression("1/15 * * * * ? * ");
		scheduleService.modifyJobTime(scheduleJob);
	}

}
