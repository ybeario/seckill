package com.bear.seckill.dao.si;

import java.util.Date;
import java.util.List;

import com.bear.seckill.entity.Seckill;

public interface SeckillMapper {
	int deleteByPrimaryKey(Long seckillId);

	int insert(Seckill record);

	int insertSelective(Seckill record);

	Seckill selectByPrimaryKey(Long seckillId);

	int updateByPrimaryKeySelective(Seckill record);

	int updateByPrimaryKey(Seckill record);

	int reduceNumber(long seckillId, Date killTime);

	List<Seckill> queryAll(int offset, int limit);
}