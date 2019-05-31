package com.fjut.service;

import java.util.List;

import com.fjut.pojo.SystemLog;

/**
 * 日志服务类
 * @author LGX
 *
 */
public interface SystemLogService {
	
	/**
	 * 添加日志
	 * @param systemLog
	 * @return
	 */
	public boolean insertLog(SystemLog systemLog);
	
	/**
	 * 查询所有日志
	 * @return
	 */
	public List<SystemLog> selectLog();

}
