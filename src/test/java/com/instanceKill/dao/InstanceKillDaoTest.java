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
 * 配置spring和Junit的整合，使Junit启动时加载springIOC容器
 * @author Administrator
 *spring-test，Junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉Junit spring配置文件位置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class InstanceKillDaoTest {

	//注入Dao实现类依赖
	//使用@Resource注释需要JDK1.7版本
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
		 * java没有保存形参的记录：
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
