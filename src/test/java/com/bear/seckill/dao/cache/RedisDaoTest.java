package com.bear.seckill.dao.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bear.seckill.dao.si.SeckillDao;
import com.bear.seckill.entity.Seckill;

/**
 * @author Y.bear
 * @version 创建时间：2018年9月27日 下午4:07:44 类说明
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDaoTest {

	@Autowired
	private RedisDao redisDao;

	@Autowired
	private SeckillDao seckillDao;

	private long seckillId = 1002;

	@Test
	public void testSeckill() {
		Seckill seckill = redisDao.getSeckill(seckillId);
		System.out.println(seckill);
		if (seckill == null) {
			seckill = seckillDao.selectByPrimaryKey(seckillId);
			if (seckill != null) {
				String result = redisDao.putSeckill(seckill);
				System.out.println(result + ":insert into Redis" + redisDao.getSeckill(seckillId));
			}
		}
	}

}
