package com.instanceKill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.instanceKill.entiy.InstanceKill;

public interface InstanceKillDao {

	/**
	 * �����
	 * @param instanceKillId
	 * @param killTime
	 * @return ���Ӱ������>1,��ʾ���µļ�¼����
	 */
	int reduceNumber(@Param("instanceKillId")long instanceKillId,@Param("killTime")Date killTime);
	/**
	 * ����ID��ѯ��ɱ����
	 * @param instanceKillId
	 * @return
	 */
	InstanceKill queryById(long instanceKillId);
	/**
	 * ����ƫ������ѯ��ɱ��Ʒ�б�
	 * @param offet
	 * @param limit
	 * @return
	 * javaû�б����βεļ�¼��
	 * List<InstanceKill> queryAll(int offset,int limit);->
	 * List<InstanceKill> queryAll(arg0,arg1);
	 *����Ҫʹ��@Param����ע��
	 */
	List<InstanceKill> queryAll(@Param("offset")int offset,@Param("limit")int limit);
}
