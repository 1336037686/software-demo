package com.fjut.service;

import java.util.List;

import com.fjut.pojo.SystemLog;

public interface SystemLogService {
	
	public boolean insertLog(SystemLog systemLog);
	
	public List<SystemLog> selectLog();

}
