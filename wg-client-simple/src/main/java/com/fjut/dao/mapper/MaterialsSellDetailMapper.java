package com.fjut.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
	
	/**
	 * 根据id查询订单详情
	 */
	@Select("select * from materialsSellDetail where id = #{id}")
	public MaterialsSellDetail getMaterialsSellDetailById(int id);
	
	/**
	 * 根据id删除进出仓记录详情
	 */
	@Delete("delete from materialsSellDetail where id = #{id}")
	public int deleteMaterialsSellDetailById(int id);
	
	/**
	 * 根据订单id删除所有详情
	 */
	@Delete("delete from materialsSellDetail where materialsSellId = #{materialsSellId}")
	public int deleteMaterialsSellDetailByMaterialSellId(String materialsSellId);
	
	/**
	 * 根据id修改订单详情商品数量
	 */
	@Update("update materialsSellDetail set total = #{total} where id = #{id}")
	public int updateMaterialsSellDetailTotal(@Param("id") int id, @Param("total") int total);
	
	
}
