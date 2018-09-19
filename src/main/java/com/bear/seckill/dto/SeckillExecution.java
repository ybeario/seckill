package com.bear.seckill.dto;

import com.bear.seckill.entity.SuccessKilled;
import com.bear.seckill.enums.SeckillStatEnum;

/**
 * @author Y.bear
 * @version 创建时间：2018年9月18日 下午4:16:33 类说明 封装秒杀执行后的结果
 */

public class SeckillExecution {
	private long seckillId;

	private int state;

	private String stateinfo;

	private SuccessKilled successKilled;

	public SeckillExecution(long seckillId, SeckillStatEnum statEnum) {
		super();
		this.seckillId = seckillId;
		this.state = statEnum.getState();
		this.stateinfo = statEnum.getStateInfo();
	}

	public SeckillExecution(long seckillId, SeckillStatEnum statEnum, SuccessKilled successKilled) {
		super();
		this.seckillId = seckillId;
		this.state = statEnum.getState();
		this.stateinfo = statEnum.getStateInfo();
		this.successKilled = successKilled;
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateinfo() {
		return stateinfo;
	}

	public void setStateinfo(String stateinfo) {
		this.stateinfo = stateinfo;
	}

	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}

	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}

}
