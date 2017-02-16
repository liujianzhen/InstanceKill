package com.instanceKill.service;

import java.util.List;

import com.instanceKill.dto.Exposer;
import com.instanceKill.dto.InstanceKillExecution;
import com.instanceKill.entiy.InstanceKill;
import com.instanceKill.exception.InstanceKillCloseException;
import com.instanceKill.exception.InstanceKillException;
import com.instanceKill.exception.RepeatKillException;
/**
 * վ�ڡ�ʹ���ߡ��ĽǶ���ƽӿ�
 * �������棺�����������ȡ��������������ͣ�return����/�쳣��
 * @author Administrator
 *
 */
public interface InstanceKillService {

	/**
	 * ��ѯ������ɱ��¼
	 * @return
	 */
	List<InstanceKill> getInstanceKillList();
	/**
	 * ��ѯ������ɱ��¼
	 * @param instanceKillId
	 * @return
	 */
	InstanceKill getById(long instanceKillId);
	/**
	 * ��ɱ����ʱ�����ɱ�ӿڵ�ַ���������ϵͳʱ�����ɱʱ��
	 * Ŀ�ģ���ֹ���ݵ�ַ���򱻲³���ɱ�ӿڵ�ַ���������ò����ǰ����
	 * @param instanceKillId
	 */
	Exposer exportInstanceKillUrl(long instanceKillId);
	
	/**
	 * ִ����ɱ����
	 * @param instacneKillId
	 * @param userPhone
	 * @param md5
	 */
	InstanceKillExecution executeInstanceKill(long instanceKillId,long userPhone,String md5)
		throws InstanceKillException,RepeatKillException,InstanceKillCloseException;
}
