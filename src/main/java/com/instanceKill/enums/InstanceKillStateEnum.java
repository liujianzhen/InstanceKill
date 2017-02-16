package com.instanceKill.enums;

/**
 * ʹ��ö�ٱ������������ֶ�
 * @author Administrator
 *
 */
public enum InstanceKillStateEnum {

	SUCCESS(1,"��ɱ�ɹ�"),
	END(0,"��ɱ����"),
	REPEATE_KILL(-1,"�ظ���ɱ"),
	INNER_ERROR(-2,"ϵͳ�쳣"),
	DATA_REWRITE(-3,"���ݴ۸�");
	
	private int state;
	
	private String stateInfo;

	private InstanceKillStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
	
	public static InstanceKillStateEnum stateof(int index){
		//ö���ڲ���values���������������õ���������
		for (InstanceKillStateEnum state : values()) {
			if(state.getState()==index){
				return state;
			}
		}
		return null;
	}
}
