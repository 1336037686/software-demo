package com.fjut.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.fjut.pojo.MaterialsSellDetail;
import com.fjut.pojo.vo.MaterialsSellDetailVo;

@Mapper
public interface MaterialsSellDetailMapper {
	
	/**
	 * 添加进出仓详情信息
	 */
	@Insert("insert into materialsSellDetail values(null, #{materialsSellId}, #{materialsId}, #{total})")
	public int addMaterialsSellDetail(MaterialsSellDetail materialsSellDetail);
	
	/**
	 * 根据进出仓id查询进出仓详情
	 * @return
	 */
	@Select("SELECT materialsSellDetail.id,materials.materialsName,materialsSellDetail.total FROM materialsSellDetail,materials WHERE materialsSellDetail.materialsId = materials.id and materialsSellId = #{materialsSellId}")
	public List<MaterialsSellDetailVo> getMaterialsSellDetailsVoByMaterialsSellId(String materialsSellId);
	
}
