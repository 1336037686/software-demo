package com.fjut.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fjut.pojo.MaterialsSell;

@Mapper
public interface MaterialsSellMapper {
	
	/**
	 * 添加进出仓订单
	 */
	@Insert("insert into materialsSell values (#{id}, #{date}, #{userId}, #{remarks}, #{type})")
	public int addMaterialsSell(MaterialsSell materialsSell);

}
