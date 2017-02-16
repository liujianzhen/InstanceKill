package com.instanceKill.dto;

import com.instanceKill.entiy.SuccessKilled;
import com.instanceKill.enums.InstanceKillStateEnum;

/**
 * ��װ��ɱִ�к���
 * @author Administrator
 *
 */
public class InstanceKillExecution {

	//��ɱ��ƷID
	private long instanceKillId;
	
	//ִ����ɱ���״̬
	private int state;
	
	//״̬��ʾ
	private String stateInfo;
	
	//��ɱ�ɹ�����
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
