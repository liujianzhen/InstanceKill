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
	
	//注入service依赖
	@Autowired
	private InstanceKillDao instanceKillDao;
	
	@Autowired
	private SuccessKilledDao successKilledDao;
	
	//md5盐值字符串，用于混淆MD5
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
	//像Exposer这种和业务不是非常相关的，用于传递信息的对象就定义在dto中
	public Exposer exportInstanceKillUrl(long instanceKillId) {
		InstanceKill instanceKill = instanceKillDao.queryById(instanceKillId);
		if (instanceKill == null) {
			return new Exposer(false, instanceKillId);
		}
		Date startTime = instanceKill.getStartTime();
		Date endTime = instanceKill.getEndTime();
		//系统当前时间
		Date nowTime = new Date();
		if (nowTime.getTime() < startTime.getTime()
				|| nowTime.getTime() > endTime.getTime()) {
			return new Exposer(false, instanceKillId, nowTime.getTime(),
					startTime.getTime(), endTime.getTime());
		}
		//转化特定字符串的过程，不可逆
		String md5=getMd5(instanceKillId);
		return new Exposer(true, md5, instanceKillId);
	}

	@Override
	@Transactional
	/*
	 * 使用注解控制事务方法的优点
	 * 1、开发团队达成一致约定，明确标注事务方法的编程风格。
	 * 2、保证事务方法的执行时间尽可能短，不要穿插其他的网络操作如RPC、HTTP请求等，或者剥离到事务方法外部。
	 * 3、不是所有的方法都需要事务，如只有一条修改操作，或只读操作不需要事务控制。
	 */
	public InstanceKillExecution executeInstanceKill(long instanceKillId,
			long userPhone, String md5) throws InstanceKillException,
			RepeatKillException, InstanceKillCloseException {
		
		if(md5==null||!md5.equals(getMd5(instanceKillId))){
			throw new InstanceKillException("instanceKill data rewrite");
		}
		//执行秒杀逻辑:减库存 + 记录购买行为
		Date nowTime = new Date();
		//减库存
		try {
			int updateCount = instanceKillDao.reduceNumber(instanceKillId, nowTime);
			if(updateCount <= 0){
				//没有更新到记录，秒杀结束
				throw new InstanceKillCloseException("instanceKill is closed");
			}else{
				//记录购买行为
				int insertCount = successKilledDao.insertSuccessKilled(instanceKillId, userPhone);
				//唯一instanceKillId, userPhone
				if(insertCount <= 0){
					//重复秒杀
					throw new RepeatKillException("instanceKill repeated");
				}else{
					//秒杀成功
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
			// 所有编译期异常 转化为运行期异常
			//这样,一旦try里面的语句发生任何错误，spring都会进行回滚，spring只支持运行期异常的回滚
			throw new InstanceKillException("instanceKill inner error:"
					+ e.getMessage());
		}
	}
}
