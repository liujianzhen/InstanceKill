package com.instanceKill.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.instanceKill.dao.SuccessKilledDao;
import com.instanceKill.entiy.SuccessKilled;

@RunWith(SpringJUnit4ClassRunner.class)
//����Junit spring�����ļ�λ��
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

	@Resource
	private SuccessKilledDao successKilledDao;
	@Test
	public void testInsertSuccessKilled() {
		
		/*
		 * ��һ�Σ�Updates: 1
		 * �ڶ��Σ�Updates: 0
		 */
		
		long id=1001L;
		long phone=18270883456L;
		int insertCount=successKilledDao.insertSuccessKilled(id, phone);
		System.out.println(insertCount);
	}

	@Test
	public void testQueryByIdWithInstanceKillId() {
		long id=1000L;
		long phone=18270883456L;
		SuccessKilled successKilled = successKilledDao.queryByIdWithInstanceKillId(id, phone);
		System.out.println(successKilled);
		System.out.println(successKilled.getInstanceKill());
	}

}
