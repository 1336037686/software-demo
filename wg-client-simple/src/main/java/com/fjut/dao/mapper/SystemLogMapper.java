package com.fjut.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.fjut.pojo.SystemLog;

/**
 * 用户日志操作Mapper
 * @author LGX
 *
 */
@Mapper
public interface SystemLogMapper {
	
	/**
	 * 增加日志
	 */
	@Insert("INSERT INTO systemLog VALUES (#{id}, #{date}, #{userLocalHost}, #{userId}, #{handle})")
	public int insertLog(SystemLog systemLog);
	
	/**
	 * 查找日志
	 */
	@Select("SELECT * FROM systemLog ORDER BY id ASC")
	public List<SystemLog> selectLog();

}
