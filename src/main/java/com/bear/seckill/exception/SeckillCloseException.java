package com.bear.seckill.exception;
/**
* @author Y.bear
* @version 创建时间：2018年9月18日 下午4:20:23
* 类说明
* 秒杀关闭异常。不如商品已经秒完
*/
public class SeckillCloseException extends SeckillException{

	public SeckillCloseException(String message, Throwable cause) {
		super(message, cause);
	}

	public SeckillCloseException(String message) {
		super(message);
	}
	
}
