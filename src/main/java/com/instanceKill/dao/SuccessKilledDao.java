package com.instanceKill.dao;

import org.apache.ibatis.annotations.Param;

import com.instanceKill.entiy.SuccessKilled;

public interface SuccessKilledDao {

	/**
	 * ���빺����ϸ���ɹ����ظ�
	 * @param instanceKillId
	 * @param userPhone
	 * @return ���������
	 */
	int insertSuccessKilled(@Param("instanceKillId")long instanceKillId,@Param("userPhone")long userPhone);
	/**
	 * ����ID��ѯSuccessKilled��Я����ɱ��Ʒ����ʵ��
	 * @param instanceKillId
	 * @return
	 */
	SuccessKilled queryByIdWithInstanceKillId(@Param("instanceKillId")long instanceKillId,@Param("userPhone")long userPhone);
}
