package com.instanceKill.service.impl;

import java.rmi.server.ExportException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.instanceKill.dao.InstanceKillDao;
import com.instanceKill.dao.SuccessKilledDao;
import com.instanceKill.dto.Exposer;
import com.instanceKill.dto.InstanceKillExecution;
import com.instanceKill.entiy.InstanceKill;
import com.instanceKill.entiy.SuccessKilled;
import com.instanceKill.enums.InstanceKillStateEnum;
import com.instanceKill.exception.InstanceKillCloseException;
import com.instanceKill.exception.InstanceKillException;
import com.instanceKill.exception.RepeatKillException;
import com.instanceKill.service.InstanceKillService;

@Service
public class InstanceKillServiceImpl implements InstanceKillService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//ע��service����
	@Autowired
	private InstanceKillDao instanceKillDao;
	
	@Autowired
	private SuccessKilledDao successKilledDao;
	
	//md5��ֵ�ַ��������ڻ���MD5
	private final String salt = "uyiu787@#^*()+hiuiETRG*&(";

	private String getMd5(long instanceKillId){
		String base = instanceKillId + "/" + salt;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
	
	@Override
	public List<InstanceKill> getInstanceKillList() {
		return instanceKillDao.queryAll(0, 4);
	}

	@Override
	public InstanceKill getById(long instanceKillId) {
		return instanceKillDao.queryById(instanceKillId);
	}

	@Override
	//��Exposer���ֺ�ҵ���Ƿǳ���صģ����ڴ�����Ϣ�Ķ���Ͷ�����dto��
	public Exposer exportInstanceKillUrl(long instanceKillId) {
		InstanceKill instanceKill = instanceKillDao.queryById(instanceKillId);
		if (instanceKill == null) {
			return new Exposer(false, instanceKillId);
		}
		Date startTime = instanceKill.getStartTime();
		Date endTime = instanceKill.getEndTime();
		//ϵͳ��ǰʱ��
		Date nowTime = new Date();
		if (nowTime.getTime() < startTime.getTime()
				|| nowTime.getTime() > endTime.getTime()) {
			return new Exposer(false, instanceKillId, nowTime.getTime(),
					startTime.getTime(), endTime.getTime());
		}
		//ת���ض��ַ����Ĺ��̣�������
		String md5=getMd5(instanceKillId);
		return new Exposer(true, md5, instanceKillId);
	}

	@Override
	@Transactional
	/*
	 * ʹ��ע��������񷽷����ŵ�
	 * 1�������ŶӴ��һ��Լ������ȷ��ע���񷽷��ı�̷��
	 * 2����֤���񷽷���ִ��ʱ�価���̣ܶ���Ҫ�������������������RPC��HTTP����ȣ����߰��뵽���񷽷��ⲿ��
	 * 3���������еķ�������Ҫ������ֻ��һ���޸Ĳ�������ֻ����������Ҫ������ơ�
	 */
	public InstanceKillExecution executeInstanceKill(long instanceKillId,
			long userPhone, String md5) throws InstanceKillException,
			RepeatKillException, InstanceKillCloseException {
		
		if(md5==null||!md5.equals(getMd5(instanceKillId))){
			throw new InstanceKillException("instanceKill data rewrite");
		}
		//ִ����ɱ�߼�:����� + ��¼������Ϊ
		Date nowTime = new Date();
		//�����
		try {
			int updateCount = instanceKillDao.reduceNumber(instanceKillId, nowTime);
			if(updateCount <= 0){
				//û�и��µ���¼����ɱ����
				throw new InstanceKillCloseException("instanceKill is closed");
			}else{
				//��¼������Ϊ
				int insertCount = successKilledDao.insertSuccessKilled(instanceKillId, userPhone);
				//ΨһinstanceKillId, userPhone
				if(insertCount <= 0){
					//�ظ���ɱ
					throw new RepeatKillException("instanceKill repeated");
				}else{
					//��ɱ�ɹ�
					SuccessKilled successKilled = successKilledDao.queryByIdWithInstanceKillId(instanceKillId, userPhone);
					return new InstanceKillExecution(instanceKillId,InstanceKillStateEnum.SUCCESS, successKilled);
				}
			
			}
		} catch (InstanceKillCloseException e1) {
			throw e1;
		} catch (RepeatKillException e2) {
			throw e2;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// ���б������쳣 ת��Ϊ�������쳣
			//����,һ��try�������䷢���κδ���spring������лع���springֻ֧���������쳣�Ļع�
			throw new InstanceKillException("instanceKill inner error:"
					+ e.getMessage());
		}
	}
}
