package com.wondersgroup.demo.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "SCHEDULE_JOB")
public class ScheduleJob implements Serializable {
    /**
     * ID
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "JOB_NAME")
    private String jobName;

    @Column(name = "JOB_GROUP")
    private String jobGroup;

    @Column(name = "JOB_TRIGGER")
    private String jobTrigger;

    /**
     * 任务状态
     */
    @Column(name = "STATUS")
    private String status;

    @Column(name = "CRON_EXPRESSION")
    private String cronExpression;

    @Column(name = "JOB_CONTENT")
    private String jobContent;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "MODIFY_DATE")
    private Date modifyDate;

    @Column(name = "JOB_SQL")
    private String jobSql;

    private static final long serialVersionUID = 1L;

    /**
     * 获取ID
     *
     * @return ID - ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return JOB_NAME
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * @param jobName
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * @return JOB_GROUP
     */
    public String getJobGroup() {
        return jobGroup;
    }

    /**
     * @param jobGroup
     */
    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    /**
     * @return JOB_TRIGGER
     */
    public String getJobTrigger() {
        return jobTrigger;
    }

    /**
     * @param jobTrigger
     */
    public void setJobTrigger(String jobTrigger) {
        this.jobTrigger = jobTrigger;
    }

    /**
     * 获取任务状态
     *
     * @return STATUS - 任务状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置任务状态
     *
     * @param status 任务状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return CRON_EXPRESSION
     */
    public String getCronExpression() {
        return cronExpression;
    }

    /**
     * @param cronExpression
     */
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    /**
     * @return JOB_CONTENT
     */
    public String getJobContent() {
        return jobContent;
    }

    /**
     * @param jobContent
     */
    public void setJobContent(String jobContent) {
        this.jobContent = jobContent;
    }

    /**
     * @return DESCRIPTION
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return START_DATE
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return END_DATE
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return CREATE_DATE
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return MODIFY_DATE
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * @param modifyDate
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * @return JOB_SQL
     */
    public String getJobSql() {
        return jobSql;
    }

    /**
     * @param jobSql
     */
    public void setJobSql(String jobSql) {
        this.jobSql = jobSql;
    }
}