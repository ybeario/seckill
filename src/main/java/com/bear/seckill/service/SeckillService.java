package com.bear.seckill.service;
/**
* @author Y.bear
* @version 创建时间：2018年9月18日 下午4:09:33
* 类说明
*/

import java.util.List;

import com.bear.seckill.dto.Exposer;
import com.bear.seckill.dto.SeckillExecution;
import com.bear.seckill.entity.Seckill;
import com.bear.seckill.exception.RepeatKillException;
import com.bear.seckill.exception.SeckillCloseException;
import com.bear.seckill.exception.SeckillException;

public interface SeckillService {
	/**
	 * 获取秒杀列表
	 * @return
	 */
	List<Seckill> getSeckillList();

	/**
	 * 获取某一条商品秒杀信息
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);

	/**
	 * 暴露url
	 * @param seckillId
	 * @return
	 */
	Exposer exportSeckillUrl(long seckillId);

	/**
	 * 执行秒杀操作
	 * 
	 * @param seckill
	 * @param userPhone
	 * @param md5
	 */
	SeckillExecution excuteSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, SeckillCloseException, RepeatKillException;
}