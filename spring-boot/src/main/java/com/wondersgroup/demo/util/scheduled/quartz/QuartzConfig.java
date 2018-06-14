package com.wondersgroup.demo.util.scheduled.quartz;

import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean () {
    	SchedulerFactoryBean factoryBean=new SchedulerFactoryBean();
        return factoryBean;
    }

    @Bean
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) throws Exception {
        Scheduler scheduler=schedulerFactoryBean.getScheduler();
        scheduler.start();
        return scheduler;
    }


}
