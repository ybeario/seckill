package com.bear.seckill.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.bear.seckill.dao.si.SeckillDao;
import com.bear.seckill.dao.si.SuccessKilledDao;
import com.bear.seckill.dto.Exposer;
import com.bear.seckill.dto.SeckillExecution;
import com.bear.seckill.entity.Seckill;
import com.bear.seckill.entity.SuccessKilled;
import com.bear.seckill.enums.SeckillStatEnum;
import com.bear.seckill.exception.RepeatKillException;
import com.bear.seckill.exception.SeckillCloseException;
import com.bear.seckill.exception.SeckillException;
import com.bear.seckill.service.SeckillService;

/**
 * @author Y.bear
 * @version 创建时间：2018年9月18日 下午5:05:53 类说明
 */
@Service
public class SeckillServiceImpl implements SeckillService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SeckillDao seckillDao;
	@Autowired
	private SuccessKilledDao successKilledDao;

	private static final String salt = "fhewi372934920u#￥%……*&&……（%#fngl";

	@Override
	public List<Seckill> getSeckillList() {
		return seckillDao.queryAll(0, 1000);
	}

	@Override
	public Seckill getById(long seckillId) {
		return seckillDao.selectByPrimaryKey(seckillId);
	}

	@Override
	public Exposer exportSeckillUrl(long seckillId) {
		Seckill seckill = seckillDao.selectByPrimaryKey(seckillId);
		if (seckill == null) {
			return new Exposer(false, seckillId);
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime = new Date();
		if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
			return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}
		String md5 = getMD5(seckillId);
		return new Exposer(true, md5, seckillId);
	}

	private String getMD5(long seckillId) {
		String base = seckillId + "/" + salt;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;

	}

	@Override
	@Transactional //必须完整执行整个流程，必须采用事务，出现问题必须回滚
	public SeckillExecution excuteSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, SeckillCloseException, RepeatKillException {
		if (md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new SeckillCloseException("seckill data rewrite");
		}
		Date killTime = new Date();
		try {
			int updateCount = seckillDao.reduceNumber(seckillId, killTime);
			if (updateCount <= 0) {
				throw new SeckillCloseException("seckill is closed"); // 数据库减少库存失败
			} else {
				int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
				if (insertCount <= 0) {
					throw new RepeatKillException("seckill repeated");
				} else {
					SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
					return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
				}
			}
		} catch (SeckillCloseException e) {
			throw e;
		} catch (RepeatKillException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SeckillException("seckill inner error:" + e.getMessage());
		}
	}

}
