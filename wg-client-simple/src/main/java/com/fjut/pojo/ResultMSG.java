package com.fjut.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * 返回对象
 * @author LGX
 *
 */
@Getter@Setter
public class ResultMSG<T> {
	
	/**
	 * 响应信息
	 */
	private String msg;
	
	/**
	 * 响应码
	 */
	private int code;
	
	/**
	 * 响应数据
	 */
	private T data;

	public ResultMSG() {
		
	}
	
	public ResultMSG(int code, String msg) {
		this.msg = msg;
		this.code = code;
	}

	public ResultMSG(int code, String msg, T data) {
		this.msg = msg;
		this.code = code;
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultMSG [msg=" + msg + ", code=" + code + ", data=" + data + "]";
	}
}
