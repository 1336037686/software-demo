package com.fjut.pojo;

import java.util.Date;

import com.fjut.util.DateUtil;

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
	 * 主机名  + IP地址
	 */
	private String userLocalHost;
	
	/**
	 * 操作人员
	 */
	private String userId;
	
	/**
	 * 具体操作
	 */
	private String handle;

	public SystemLog() {}

	public SystemLog(int id, Date date, String userLocalHost, String userId, String handle) {
		this.id = id;
		this.date = date;
		this.userLocalHost = userLocalHost;
		this.userId = userId;
		this.handle = handle;
	}

	@Override
	public String toString() {
		return  "★ 【日志】: " + id + "\n" + 
				"> 操作时间: " + DateUtil.dateFormate2(date) + "\n" + 
				"> 用户主机: " + userLocalHost + "\n" + 
				"> 操作用户ID: " + userId + "\n" + 
				"> 具体操作: " + handle + "\n";
	}
}
