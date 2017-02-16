package com.instanceKill.entiy;

import java.util.Date;

public class InstanceKill {
	
	private long instanceKillId;
	private String name;
	private int number;
	private Date startTime;
	private Date endTime;
	private Date createTime;
	public long getInstanceKillId() {
		return instanceKillId;
	}
	public void setInstanceKillId(long instanceKillId) {
		this.instanceKillId = instanceKillId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "InstanceKill [instanceKillId=" + instanceKillId + ", name="
				+ name + ", number=" + number + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", createTime=" + createTime + "]";
	}
	
}
