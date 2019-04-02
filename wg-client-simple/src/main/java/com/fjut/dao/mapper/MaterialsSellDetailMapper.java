package com.fjut.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.fjut.pojo.MaterialsSellDetail;

@Mapper
public interface MaterialsSellDetailMapper {
	
	/**
	 * 添加进出仓详情信息
	 */
	@Insert("insert into materialsSellDetail values(null, #{materialsSellId}, #{materialsId}, #{total})")
	public int addMaterialsSellDetail(MaterialsSellDetail materialsSellDetail);
}
