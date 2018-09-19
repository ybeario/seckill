package com.bear.seckill.exception;

/**
 * @author Y.bear
 * @version 创建时间：2018年9月18日 下午4:21:00 类说明 在秒杀时遇到的异常
 */
public class SeckillException extends RuntimeException {

	public SeckillException(String message, Throwable cause) {
		super(message, cause);
	}

	public SeckillException(String message) {
		super(message);
	}

}
