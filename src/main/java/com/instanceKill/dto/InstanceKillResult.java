package com.instanceKill.dto;

//����Ajax���󷵻����ͣ���װjson���
public class InstanceKillResult<T> {

	private boolean success;
	private T data;
	String error;
	
	public InstanceKillResult(boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}

	public InstanceKillResult(boolean success, String error) {
		super();
		this.success = success;
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}
