package com.bear.seckill.dao.si;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import com.bear.seckill.entity.Seckill;

/**
 * @author Y.bear
 * @version 创建时间：2018年9月11日 上午10:52:32 类说明
 */
@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SeckillDaoTest {
	@Autowired
	private SeckillDao dao;

	@Test
	public void testSelectByPrimaryKey() {
		long id = 1000;
		Seckill seckill = dao.selectByPrimaryKey(id);
		System.out.println(seckill.getName());
		System.out.println(seckill);
	}

	@Test
	public void testReduceNumber() {
		Date killTime = new Date();
		int count = dao.reduceNumber(1000L, killTime);
		System.out.println("Count=" + count);
	}

	@Test
	public void testqueryAll() {
		List<Seckill> records = dao.queryAll(0, 100);
		for (Seckill seckill : records) {
			System.out.println(seckill);
		}
	}

}
