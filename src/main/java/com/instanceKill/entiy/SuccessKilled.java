package com.instanceKill.entiy;

import java.util.Date;

public class SuccessKilled {

	private long instanceKill_id;
	private long userPhone;
	private short state;
	private Date createTime;
	private InstanceKill instanceKill;
	

	public InstanceKill getInstanceKill() {
		return instanceKill;
	}
	public void setInstanceKill(InstanceKill instanceKill) {
		this.instanceKill = instanceKill;
	}
	public long getInstanceKill_id() {
		return instanceKill_id;
	}
	public void setInstanceKill_id(long instanceKill_id) {
		this.instanceKill_id = instanceKill_id;
	}
	public long getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}
	public short getState() {
		return state;
	}
	public void setState(short state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "successKilled [instanceKill_id=" + instanceKill_id
				+ ", userPhone=" + userPhone + ", state=" + state
				+ ", createTime=" + createTime + "]";
	}
	
}
