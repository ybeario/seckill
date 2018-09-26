package com.bear.seckill.web;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bear.seckill.dto.Exposer;
import com.bear.seckill.dto.SeckillExecution;
import com.bear.seckill.dto.SeckillResult;
import com.bear.seckill.entity.Seckill;
import com.bear.seckill.enums.SeckillStatEnum;
import com.bear.seckill.exception.RepeatKillException;
import com.bear.seckill.exception.SeckillCloseException;
import com.bear.seckill.service.SeckillService;

/**
 * @author Y.bear
 * @version 创建时间：2018年9月19日 下午7:45:03 类说明
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SeckillService service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Seckill> seckillList = service.getSeckillList();
		model.addAttribute("list", seckillList);
		return "views/list";
	}

	@GetMapping("/{seckillId}/detail")
	public String detail(@PathVariable("seckillId") Long seckillId, Model model) {

		if (seckillId == null) {
			return "redirect:/seckill/list";
		}
		Seckill seckill = service.getById(seckillId);
		if (seckill == null) {
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill", seckill);
		return "views/detail";
	}

	// ajax json
	@PostMapping(value = "/{seckillId}/exposer", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
		SeckillResult<Exposer> result;
		try {
			Exposer exposer = service.exportSeckillUrl(seckillId);
			result = new SeckillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = new SeckillResult<Exposer>(false, e.getMessage());
		}
		return result;

	}

	@PostMapping(value = "/{seckillId}/{md5}/excution", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
			@PathVariable("md5") String md5, @CookieValue(value = "killPhone", required = false) Long userPhone) {
		if (userPhone == null) {
			return new SeckillResult<SeckillExecution>(false, "未注册");
		}
		try {
			SeckillExecution execution = service.excuteSeckill(seckillId, userPhone, md5);
			return new SeckillResult<SeckillExecution>(true, execution);
		} catch (SeckillCloseException e) {
			SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatEnum.END);
			return new SeckillResult<SeckillExecution>(true, seckillExecution);
		} catch (RepeatKillException e) {
			SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
			return new SeckillResult<SeckillExecution>(true, seckillExecution);
		} catch (Exception e) {
			SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
			return new SeckillResult<SeckillExecution>(true, seckillExecution);
		}
	}

	@GetMapping("/time/now")
	@ResponseBody
	public SeckillResult<Long> time() {
		Date now = new Date();
		return new SeckillResult<Long>(true, now.getTime());
	}
}
