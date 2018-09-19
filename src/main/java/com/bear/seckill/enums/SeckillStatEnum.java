package com.bear.seckill.enums;

/**
 * @author Y.bear
 * @version 创建时间：2018年9月19日 下午3:28:43 类说明
 */
public enum SeckillStatEnum {
	END(0, "秒杀结束"), SUCCESS(1, "秒杀成功"), REPEAT_KILL(-1, "重复秒杀"), INNER_ERROR(-2, "系统异常"), DATA_REWRITE(-3, "数据篡改");
	private int state;

	private String stateInfo;

	private SeckillStatEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static SeckillStatEnum stateOf(int index) {
		for (SeckillStatEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;

	}

}
