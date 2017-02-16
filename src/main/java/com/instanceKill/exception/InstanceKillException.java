package com.instanceKill.exception;

//对每个模块或子系统统一封装一个异常
//所有与之相关的异常都集成自该异常
//异常对接口使用者使用过程中有很大的帮助，可以发现问题出现的地方

/**
 * 秒杀相关业务异常
 * @author Administrator
 *
 */
public class InstanceKillException extends RuntimeException{

	public InstanceKillException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InstanceKillException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

//spring只支持运行时异常回滚策略
}
