package com.fjut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fjut.dao.mapper.SystemLogMapper;
import com.fjut.pojo.SystemLog;
import com.fjut.service.SystemLogService;

/**
 * 日志操作服务层
 * @author LGX
 *
 */
@Service
public class SystemLogServiceImpl implements SystemLogService{
	
	@Autowired
	private SystemLogMapper systemLogMapper;

	@Override
	public boolean insertLog(SystemLog systemLog) {
		int result = systemLogMapper.insertLog(systemLog);
		return result > 0 ? true : false;
	}

	@Override
	public List<SystemLog> selectLog() {
		return systemLogMapper.selectLog();
	}

}
