package com.fjut.pojo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 操作日志
 * @author LGX
 *
 */
@Getter@Setter
public class SystemLog {
	/**
	 * ID
	 */
	private int id;
	
	/**
	 * 操作日期
	 */
	private Date date;
	
	/**
	 * 操作人员
	 */
	private String userId;
	
	/**
	 * 具体操作
	 */
	private String handle;

	public SystemLog() {}

	public SystemLog(int id, Date date, String userId, String handle) {
		this.id = id;
		this.date = date;
		this.userId = userId;
		this.handle = handle;
	}

	@Override
	public String toString() {
		return "SystemLog [id=" + id + ", date=" + date + ", userId=" + userId + ", handle=" + handle + "]";
	}
}
