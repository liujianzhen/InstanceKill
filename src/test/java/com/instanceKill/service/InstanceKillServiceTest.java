package com.instanceKill.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.instanceKill.dto.Exposer;
import com.instanceKill.dto.InstanceKillExecution;
import com.instanceKill.entiy.InstanceKill;
import com.instanceKill.exception.InstanceKillException;
import com.instanceKill.exception.RepeatKillException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:spring/spring-dao.xml",
	"classpath:spring/spring-service.xml"
	})
public class InstanceKillServiceTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InstanceKillService instanceKillService;
	@Test
	public void testGetInstanceKillList() {
		List<InstanceKill> list = instanceKillService.getInstanceKillList();
		logger.info("list={}",list);
	}

	@Test
	public void testGetById() {
		long id = 1000;
		InstanceKill instanceKill = instanceKillService.getById(id);
		logger.info("instanceKill={}",instanceKill);
	}

	@Test
	public void testInstanceKillLogic() throws Exception{
		long id =1000;
		Exposer exposer = instanceKillService.exportInstanceKillUrl(id);
		if(exposer.isExposed()){
			logger.info("exposer={}",exposer);
			long phone = 18276547654L;
			String md5 = exposer.getMd5();
			try {
				InstanceKillExecution execution= instanceKillService.executeInstanceKill(id, phone, md5);
				logger.info("result:{}",execution);
			} catch (RepeatKillException e) {
				logger.error(e.getMessage());
			}catch (InstanceKillException e) {
				logger.error(e.getMessage());
			}
		}else{
			//ÃëÉ±Î´¿ªÆô
			logger.warn("exposer={}",exposer);
		}
	}
}