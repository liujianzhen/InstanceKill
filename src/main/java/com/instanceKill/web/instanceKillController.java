package com.instanceKill.web;

import java.rmi.server.ExportException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.instanceKill.dto.Exposer;
import com.instanceKill.dto.InstanceKillExecution;
import com.instanceKill.dto.InstanceKillResult;
import com.instanceKill.entiy.InstanceKill;
import com.instanceKill.enums.InstanceKillStateEnum;
import com.instanceKill.exception.InstanceKillCloseException;
import com.instanceKill.exception.RepeatKillException;
import com.instanceKill.service.InstanceKillService;

@Controller
@RequestMapping("/instanceKill")//url:模块/资源/{id}/细分
public class instanceKillController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InstanceKillService instanceKillService;
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model){
		//list.jsp + model = ModelAndView;
		//获取列表页
		List<InstanceKill> list= instanceKillService.getInstanceKillList();
		model.addAttribute("list", list);
		return "list";// /WEB-INF/jsp/"list".jsp
	}
	
	@RequestMapping(value="/{instnceKillId}/detail",method=RequestMethod.GET)
	public String detail(Model model,@PathVariable("instnceKillId") Long instanceKillId){
		if(instanceKillId==null){
			return "redirect:/instanceKill/list";
		}
		InstanceKill instanceKill = instanceKillService.getById(instanceKillId);
		if(instanceKill==null){
			return "forward:/instanceKill/list";
		}
		model.addAttribute("instanceKill", instanceKill);
		return "detail";
	}
	
	//Ajax json
	@RequestMapping(value="/{instanceKillId}/exposer",
			method=RequestMethod.POST,
			produces={"application/json;charset=utf-8"})
	@ResponseBody
	public InstanceKillResult<Exposer> exposer(@PathVariable("instanceKillId") Long instanceKillId){
		System.out.println(instanceKillId);
		InstanceKillResult<Exposer> result;
		Exposer exposer = instanceKillService.exportInstanceKillUrl(instanceKillId);
		try {
			result = new InstanceKillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			result = new InstanceKillResult<Exposer>(false, e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value="/{instanceKillId}/{md5}/execution",
			method=RequestMethod.POST,
			produces={"application/json;charset=utf-8"})
	@ResponseBody//此注释声明返回类型为json格式
	public InstanceKillResult<InstanceKillExecution> execute(@PathVariable("instanceKillId") Long instanceKillId,
														@PathVariable("md5") String md5,
														@CookieValue(value="instanceKillPhone",required=false) Long phone){
		if(phone == null){
			new InstanceKillResult<InstanceKillExecution>(false, "未注册");
		}
		InstanceKillResult<InstanceKillExecution> result;
		try {
			InstanceKillExecution execution = instanceKillService.executeInstanceKill(instanceKillId, phone, md5);
			return new InstanceKillResult<InstanceKillExecution>(true, execution);
		} catch(RepeatKillException e){
			InstanceKillExecution execution = new InstanceKillExecution(instanceKillId, InstanceKillStateEnum.REPEATE_KILL);
			return new InstanceKillResult<InstanceKillExecution>(true, execution);
		} catch(InstanceKillCloseException e){
			InstanceKillExecution execution = new InstanceKillExecution(instanceKillId, InstanceKillStateEnum.END);
			return new InstanceKillResult<InstanceKillExecution>(true, execution);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			InstanceKillExecution execution = new InstanceKillExecution(instanceKillId, InstanceKillStateEnum.INNER_ERROR);
			return new InstanceKillResult<InstanceKillExecution>(true, execution);
		}
	}
	
	@RequestMapping(value="/time/now",method=RequestMethod.GET)
	@ResponseBody
	public InstanceKillResult<Long> time(){
		Date now = new Date();
		return new InstanceKillResult<Long>(true, now.getTime());
	}
}
