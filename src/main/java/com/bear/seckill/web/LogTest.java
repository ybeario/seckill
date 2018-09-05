package com.bear.seckill.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Y.bear
 * @version 创建时间：2018年9月1日 下午2:22:35 类说明
 */
@Controller
public class LogTest {
	@GetMapping("/{name}/{age}")
	public void getName(@PathVariable String name, @PathVariable String age) {
		System.out.println(name+age);
	}
}
