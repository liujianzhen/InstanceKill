package com.instanceKill.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import com.instanceKill.dao.InstanceKillDao;
import com.instanceKill.entiy.InstanceKill;

/**
 * ����spring��Junit�����ϣ�ʹJunit����ʱ����springIOC����
 * @author Administrator
 *spring-test��Junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//����Junit spring�����ļ�λ��
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class InstanceKillDaoTest {

	//ע��Daoʵ��������
	//ʹ��@Resourceע����ҪJDK1.7�汾
	@Resource
	private InstanceKillDao instanceKillDao;
	
	@Test
	public void testQueryById() {
		long id=1000;
		InstanceKill instanceKill=instanceKillDao.queryById(id);
		System.out.println(instanceKill.getName());
		System.out.println(instanceKill);
	}
	
	@Test
	public void testQueryAll() {
		/*
		 * javaû�б����βεļ�¼��
		 * List<InstanceKill> queryAll(int offset,int limit);->
		 * List<InstanceKill> queryAll(arg0,arg1);
		 */
		List<InstanceKill> instanceKills = instanceKillDao.queryAll(0, 100);
		for (InstanceKill instanceKill : instanceKills) {
			System.out.println(instanceKill);
		}
	}
	
	
	@Test
	public void testReduceNumber() {
		Date killTime = new Date();
		int updateCount = instanceKillDao.reduceNumber(1000L, killTime);
		System.out.println(updateCount);
	}


}
