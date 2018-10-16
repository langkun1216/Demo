package com.demo.common.utils.excel;

import java.util.Date;

public class SysJobsDemo {

	
	 
	private String jobName;
 
	private String jobGroup;
  
	private String corn;
 
	private String url;
 
	private String requestMethod;

	private Date startTime;
	private Date endTime;
	private String status;
	private Date createTime;

	public SysJobsDemo() { 
	}

	public SysJobsDemo(String jobName, String jobGroup, String corn, String url, String requestMethod, Date startTime,
			Date endTime, String status, Date createTime) {
		super();
		this.jobName = jobName;
		this.jobGroup = jobGroup;
		this.corn = corn;
		this.url = url;
		this.requestMethod = requestMethod;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "SysJobsDemo{" + "jobName='" + jobName + '\'' + ", jobGroup='" + jobGroup + '\'' + ", corn='" + corn
				+ '\'' + ", url='" + url + '\'' + ", requestMethod=" + requestMethod + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", status=" + status + ", createTime=" + createTime + "}";
	}

	@ExcelResources(title = "Job名称", order = 1)
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	@ExcelResources(title = "Job分组", order = 2)
	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	@ExcelResources(title = "corn表达式", order = 3)
	public String getCorn() {
		return corn;
	}

	public void setCorn(String corn) {
		this.corn = corn;
	}

	@ExcelResources(title = "url", order = 4)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	@ExcelResources(title="请求方式",order=5)
	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	@ExcelResources(title="任务开始时间",order=6)
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@ExcelResources(title="任务结束时间",order=7)
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@ExcelResources(title="任务状态",order=8)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
