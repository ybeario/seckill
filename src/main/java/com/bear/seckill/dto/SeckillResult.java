package com.bear.seckill.dto;

/**
 * @author Y.bear
 * @version 创建时间：2018年9月19日 下午7:54:56 类说明
 */
//封装json结果
public class SeckillResult<T> {
	private boolean success;
	
	private T data;
	
	private String error;

	public SeckillResult(boolean success, String error) {
		super();
		this.success = success;
		this.error = error;
	}

	public SeckillResult(boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	
}
