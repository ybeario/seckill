package com.bear.seckill.dao.si;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.bear.seckill.entity.SuccessKilled;

/**
 * @author Y.bear
 * @version 创建时间：2018年9月11日 下午7:45:55 类说明
 */
@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SuccessKilledDaoTest {
	@Autowired
	private SuccessKilledDao dao;


	@Test
	@Commit // 或者 @Rollback(false) 属于test注解，是否再方法执行完后进行回滚，不影响数据库内容
	public void testinsertSuccessKilled() {
		long seckillId = 1001;
		long userPhone = 18883285384L;
		int insertCount = dao.insertSuccessKilled(seckillId, userPhone);
		System.out.println("insertCount=" + insertCount);
	}



	@Test
	public void testQueryByIdWithSeckill() {
		long seckillId = 1000;
		long userPhone = 18883285384L;
		SuccessKilled successKilled = dao.queryByIdWithSeckill(seckillId, userPhone);
		System.out.println(successKilled);
		System.out.println(successKilled.getSeckill());
	}

}
