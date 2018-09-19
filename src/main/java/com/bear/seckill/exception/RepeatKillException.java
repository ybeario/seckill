package com.bear.seckill.exception;

/**
 * @author Y.bear
 * @version 创建时间：2018年9月18日 下午4:18:56 类说明 重复秒杀异常
 */
public class RepeatKillException extends SeckillException {

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepeatKillException(String message) {
		super(message);
	}

}
