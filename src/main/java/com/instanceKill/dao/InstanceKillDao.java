package com.instanceKill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.instanceKill.entiy.InstanceKill;

public interface InstanceKillDao {

	/**
	 * 减库存
	 * @param instanceKillId
	 * @param killTime
	 * @return 如果影响行数>1,表示更新的记录行数
	 */
	int reduceNumber(@Param("instanceKillId")long instanceKillId,@Param("killTime")Date killTime);
	/**
	 * 根据ID查询秒杀对象
	 * @param instanceKillId
	 * @return
	 */
	InstanceKill queryById(long instanceKillId);
	/**
	 * 根据偏移量查询秒杀商品列表
	 * @param offet
	 * @param limit
	 * @return
	 * java没有保存形参的记录：
	 * List<InstanceKill> queryAll(int offset,int limit);->
	 * List<InstanceKill> queryAll(arg0,arg1);
	 *所以要使用@Param（）注解
	 */
	List<InstanceKill> queryAll(@Param("offset")int offset,@Param("limit")int limit);
}
