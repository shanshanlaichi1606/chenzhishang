package com.wondersgroup.demo.util.scheduled.quartz;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.wondersgroup.demo.entity.ScheduleJob;
import com.wondersgroup.demo.mapper.ScheduleJobMapper;

/**
 * 定时任务 service
 * @author chen
 *
 */
@Service
public class ScheduleService {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleService.class);

	@Resource
	private ScheduleJobMapper scheduleJobMapper;

	@Resource
	private Scheduler scheduler;
	

	public void initScheduleJob() {
		List<ScheduleJob> qResult = scheduleJobMapper.selectAll();
		if (qResult == null || CollectionUtils.isEmpty(qResult)) {
			return;
		}
		for (ScheduleJob scheduleJob : qResult) {
				 //则添加定时任务
			  addJob(scheduleJob);
		}
	}
	/**
	 * 添加定时任务
	 * @param job
	 */
	public void addJob(ScheduleJob job) {
		try {
			//通过job名称 分组 获取job 触发器 
			TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
			if (null ==  scheduler.getTrigger(triggerKey)) {
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
				CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
				JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class).withIdentity(job.getJobName(), job.getJobGroup()).build();
				jobDetail.getJobDataMap().put("scheduleJob", job);
				jobDetail.getJobDataMap().put("scheduleJobMapper", scheduleJobMapper);
				scheduler.scheduleJob(jobDetail, cronTrigger);
				job.setCreateDate(new Date());
				job.setEndDate(cronTrigger.getNextFireTime());
				scheduleJobMapper.insert(job);
			} else {
				logger.error("该任务已经存在！");
			}
		} catch (Exception e) {
			logger.error("向任务调度中添加定时任务异常！" + e.getMessage(), e);
		}
	}
	/**
	 * 修改定时任务
	 * @param job
	 */
	public void modifyJobTime(ScheduleJob job) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
			Trigger trigger = scheduler.getTrigger(triggerKey);
			if (null !=  trigger) {
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
				CronTrigger cronTrigger = ((CronTrigger) trigger).getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
				cronTrigger.getJobDataMap().put("scheduleJob", job);
				cronTrigger.getJobDataMap().put("scheduleJobMapper", scheduleJobMapper);
				scheduler.rescheduleJob(triggerKey, cronTrigger);
				job.setModifyDate(new Date());
				job.setEndDate(cronTrigger.getNextFireTime());
				scheduleJobMapper.updateByPrimaryKey(job);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void removeJob(Integer scheduleJobId) {
		try {
			ScheduleJob job = scheduleJobMapper.selectByPrimaryKey(scheduleJobId);
			if (null == job) {
			}
			JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
			scheduler.deleteJob(jobKey);
			scheduleJobMapper.delete(job);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void runOnce(Integer scheduleJobId) {
		ScheduleJob job = scheduleJobMapper.selectByPrimaryKey(scheduleJobId);
		if (null == job) {
		}
		JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
		try {
			scheduler.triggerJob(jobKey);
		} catch (SchedulerException e) {
			logger.error("运行一次定时任务失败", e);
		}
	}

	public void startJobs() {
		try {
			scheduler.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void shutdownJobs() {
		try {
			if (!scheduler.isShutdown())
				scheduler.shutdown();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void pauseJob(Integer scheduleJobId) {
		try {
			ScheduleJob job = scheduleJobMapper.selectByPrimaryKey(scheduleJobId);
			if (null == job) {
			}

			JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
			scheduler.pauseJob(jobKey);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void resumeJob(Integer scheduleJobId) {
		try {
			ScheduleJob job = scheduleJobMapper.selectByPrimaryKey(scheduleJobId);
			if (null == job) {
			}
			JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
			scheduler.resumeJob(jobKey);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*public List<ScheduleJobVo> queryList() {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		QueryResult qResult = queryScheduleJobByName(null, null, -1, -1);
		List<ScheduleJob> scheduleJobList = null;

		List<ScheduleJobVo> scheduleJobVoList=new ArrayList<ScheduleJobVo>();

		if (null != qResult && qResult.getResultList().size() > 0) {
			scheduleJobList = qResult.getResultList();
			try {
				for (ScheduleJob vo : scheduleJobList) {
					JobKey jobKey = JobKey.jobKey(vo.getJobName(), vo.getJobGroup());
					List triggers = scheduler.getTriggersOfJob(jobKey);
					if (CollectionUtils.isEmpty(triggers)) {
						continue;
					}
					Trigger trigger = (Trigger) triggers.iterator().next();
					vo.setJobTrigger(trigger.getKey().getName());
					//如果设置为手动启动的任务，则设置触发器状态为挂起
					if (vo.getStatus().equals(Constant.MANUAL))
						scheduler.pauseJob(jobKey);

					Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
					//vo.setStatus(triggerState.name());
					vo.setTrigerStatus(triggerState.name());
					if ((trigger instanceof CronTrigger)) {
						CronTrigger cronTrigger = (CronTrigger) trigger;
						String cronExpression = cronTrigger.getCronExpression();
						vo.setCronExpression(cronExpression);
					}
					ScheduleJobVo voJobVo = ScheduleJobVo.toDTO(vo);
					scheduleJobVoList.add(voJobVo);
				}
			} catch (SchedulerException e) {
				LOG.error(e.getMessage());
			}
		}
		return scheduleJobVoList;
	}

	public List<ScheduleJobVo> queryExecutingJobList() {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
			List jobList = new ArrayList(executingJobs.size());
			for (JobExecutionContext executingJob : executingJobs) {
				ScheduleJobVo job = new ScheduleJobVo();
				JobDetail jobDetail = executingJob.getJobDetail();
				JobKey jobKey = jobDetail.getKey();
				Trigger trigger = executingJob.getTrigger();
				job.setJobName(jobKey.getName());
				job.setJobGroup(jobKey.getGroup());
				job.setJobTrigger(trigger.getKey().getName());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setStatus(triggerState.name());
				if ((trigger instanceof CronTrigger)) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
				}
				jobList.add(job);
			}
			return jobList;
		} catch (SchedulerException e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	public void insertJob(ScheduleJob job) {
		job.setCreateDate(new Date());
		job.setModifyDate(new Date());
		this.scheduleJobDao.merge(job);
	}

	*/

	/**
	 * 主键查询 返回实体
	 * 
	 * @param id
	 * @return
	 */
	public ScheduleJob selectByPrimaryKey(int id) {
		return scheduleJobMapper.selectByPrimaryKey(id);
	}
}