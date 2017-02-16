package com.instanceKill.dao;

import org.apache.ibatis.annotations.Param;

import com.instanceKill.entiy.SuccessKilled;

public interface SuccessKilledDao {

	/**
	 * 插入购买明细，可过滤重复
	 * @param instanceKillId
	 * @param userPhone
	 * @return 插入的行数
	 */
	int insertSuccessKilled(@Param("instanceKillId")long instanceKillId,@Param("userPhone")long userPhone);
	/**
	 * 根据ID查询SuccessKilled并携带秒杀产品对象实体
	 * @param instanceKillId
	 * @return
	 */
	SuccessKilled queryByIdWithInstanceKillId(@Param("instanceKillId")long instanceKillId,@Param("userPhone")long userPhone);
}
