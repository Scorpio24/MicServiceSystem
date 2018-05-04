package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-01-31 12:30:13
 */
public class EvtLogDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long logNr;
	//
	private String type;
	//
	private String procDefId;
	//
	private String procInstId;
	//
	private String executionId;
	//
	private String taskId;
	//
	private Date timeStamp;
	//
	private String userId;
	//
	private Integer data;
	//
	private String lockOwner;
	//
	private Date lockTime;
	//
	private Integer isProcessed;

	/**
	 * 设置：
	 */
	public void setLogNr(Long logNr) {
		this.logNr = logNr;
	}
	/**
	 * 获取：
	 */
	public Long getLogNr() {
		return logNr;
	}
	/**
	 * 设置：
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：
	 */
	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}
	/**
	 * 获取：
	 */
	public String getProcDefId() {
		return procDefId;
	}
	/**
	 * 设置：
	 */
	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}
	/**
	 * 获取：
	 */
	public String getProcInstId() {
		return procInstId;
	}
	/**
	 * 设置：
	 */
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	/**
	 * 获取：
	 */
	public String getExecutionId() {
		return executionId;
	}
	/**
	 * 设置：
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * 获取：
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * 设置：
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	/**
	 * 获取：
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}
	/**
	 * 设置：
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：
	 */
	public void setData(Integer data) {
		this.data = data;
	}
	/**
	 * 获取：
	 */
	public Integer getData() {
		return data;
	}
	/**
	 * 设置：
	 */
	public void setLockOwner(String lockOwner) {
		this.lockOwner = lockOwner;
	}
	/**
	 * 获取：
	 */
	public String getLockOwner() {
		return lockOwner;
	}
	/**
	 * 设置：
	 */
	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}
	/**
	 * 获取：
	 */
	public Date getLockTime() {
		return lockTime;
	}
	/**
	 * 设置：
	 */
	public void setIsProcessed(Integer isProcessed) {
		this.isProcessed = isProcessed;
	}
	/**
	 * 获取：
	 */
	public Integer getIsProcessed() {
		return isProcessed;
	}
}
