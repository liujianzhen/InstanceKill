package com.instanceKill.service;

import java.util.List;

import com.instanceKill.dto.Exposer;
import com.instanceKill.dto.InstanceKillExecution;
import com.instanceKill.entiy.InstanceKill;
import com.instanceKill.exception.InstanceKillCloseException;
import com.instanceKill.exception.InstanceKillException;
import com.instanceKill.exception.RepeatKillException;
/**
 * 站在“使用者”的角度设计接口
 * 三个方面：方法定义粒度、参数、返回类型（return类型/异常）
 * @author Administrator
 *
 */
public interface InstanceKillService {

	/**
	 * 查询所有秒杀记录
	 * @return
	 */
	List<InstanceKill> getInstanceKillList();
	/**
	 * 查询单个秒杀记录
	 * @param instanceKillId
	 * @return
	 */
	InstanceKill getById(long instanceKillId);
	/**
	 * 秒杀开启时输出秒杀接口地址，否则输出系统时间和秒杀时间
	 * 目的：防止根据地址规则被猜出秒杀接口地址而被人利用插件提前抢购
	 * @param instanceKillId
	 */
	Exposer exportInstanceKillUrl(long instanceKillId);
	
	/**
	 * 执行秒杀操作
	 * @param instacneKillId
	 * @param userPhone
	 * @param md5
	 */
	InstanceKillExecution executeInstanceKill(long instanceKillId,long userPhone,String md5)
		throws InstanceKillException,RepeatKillException,InstanceKillCloseException;
}
