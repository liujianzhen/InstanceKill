package com.instanceKill.dto;

import com.instanceKill.entiy.SuccessKilled;
import com.instanceKill.enums.InstanceKillStateEnum;

/**
 * 封装秒杀执行后结果
 * @author Administrator
 *
 */
public class InstanceKillExecution {

	//秒杀商品ID
	private long instanceKillId;
	
	//执行秒杀结果状态
	private int state;
	
	//状态标示
	private String stateInfo;
	
	//秒杀成功对象
	private SuccessKilled successKilled;
	
	public InstanceKillExecution(long instanceKillId, InstanceKillStateEnum stateEnum, SuccessKilled successKilled) {
		super();
		this.instanceKillId = instanceKillId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.successKilled = successKilled;
	}

	public InstanceKillExecution(long instanceKillId, InstanceKillStateEnum stateEnum) {
		super();
		this.instanceKillId = instanceKillId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	public long getInstanceKillId() {
		return instanceKillId;
	}

	public void setInstanceKillId(long instanceKillId) {
		this.instanceKillId = instanceKillId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}

	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}
	
	
}
