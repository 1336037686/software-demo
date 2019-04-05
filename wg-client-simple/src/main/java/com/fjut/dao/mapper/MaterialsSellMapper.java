package com.fjut.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.fjut.pojo.MaterialsSell;
import com.fjut.pojo.vo.MaterialsSellVo;

@Mapper
public interface MaterialsSellMapper {
	
	/**
	 * 添加进出仓订单
	 */
	@Insert("insert into materialsSell values (#{id}, #{date}, #{userId}, #{remarks}, #{type})")
	public int addMaterialsSell(MaterialsSell materialsSell);
	
	/**
	 * 查找所有进出仓记录
	 * @return
	 */
	@Select("SELECT materialsSell.id,user.username,materialsSell.date,materialsSell.type,materialsSell.remarks FROM materialsSell,user WHERE materialsSell.userId = user.id")
	public List<MaterialsSellVo> getAllMaterialsSell();
	
	

}
