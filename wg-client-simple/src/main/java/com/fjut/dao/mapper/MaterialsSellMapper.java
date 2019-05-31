package com.fjut.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.fjut.pojo.MaterialsSell;
import com.fjut.pojo.vo.ChartVo;
import com.fjut.pojo.vo.InOutDataVo;
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
	@Select("SELECT materialsSell.id,user.username,materialsSell.date,materialsSell.type,materialsSell.remarks FROM materialsSell,user WHERE materialsSell.userId = user.id ORDER BY materialsSell.date DESC")
	public List<MaterialsSellVo> getAllMaterialsSell();
	
	/**
	 * 根据id删除进出仓记录
	 */
	@Delete("delete from materialsSell where id = #{id}")
	public int deleteMaterialsSellById(String id);


	/**
	 * 根据关键字查询
	 */
	@Select("SELECT materialsSell.id,user.username,materialsSell.date,materialsSell.type,materialsSell.remarks FROM materialsSell,user WHERE materialsSell.userId = user.id AND (materialsSell.id = #{input} OR user.username LIKE  BINARY CONCAT(CONCAT('%', #{input}, '%'))) ORDER BY materialsSell.date DESC")
	public List<MaterialsSellVo> searchMaterialSell(String input);
	
	/**
	 * 根据进出仓类型查询
	 */
	@Select("SELECT materialsSell.id,user.username,materialsSell.date,materialsSell.type,materialsSell.remarks FROM materialsSell,user WHERE materialsSell.userId = user.id AND (materialsSell.type = #{input}) ORDER BY materialsSell.date DESC")
	public List<MaterialsSellVo> searchMaterialSell2(int input);
	
	/**
	 * 查询订单所有年
	 */
	@Select("SELECT DISTINCT YEAR(date) FROM materialsSell ORDER BY date DESC")
	public List<String> getAllYear();
	
	/**
	 * 查询订单所有月
	 */
	@Select("SELECT DISTINCT MONTH(date) FROM materialsSell where YEAR(date) = #{year} ORDER BY date DESC")
	public List<String> getAllMonth(String year);
	
	/**
	 * 获取某个物料在某个时间段内的进出仓记录
	 */
	@Select("SELECT SUM(materialsSellDetail.total) as sum, MONTH(materialsSell.date) as month, YEAR(materialsSell.date) as year FROM materialsSell,materialsSellDetail,materials "
			+ "WHERE "
			+ "(materialsSell.id = materialsSellDetail.materialsSellId) "
			+ "AND (materialsSellDetail.materialsId = materials.id) "
			+ "AND (materialsSellDetail.materialsId = #{materialsId}) "
			+ "AND (MONTH(materialsSell.date) >= #{month1} AND MONTH(materialsSell.date) <= #{month2}) "
			+ "AND (YEAR(materialsSell.date) = #{year}) "
			+ "AND (materialsSell.type = #{type}) "
			+ "GROUP BY year,month "
			+ "ORDER BY month")
	public List<ChartVo> getChartData(@Param("materialsId") String materialsId, @Param("year") String year, @Param("month1") int month1, @Param("month2") int month2, @Param("type") int type);

	/**
	 * 根据时间统计进出仓记录
	 */
	@Select("SELECT materialsSell.id as id,materials.materialsName as materialsName,materialsSellDetail.total as total,materialsSell.type as type,DATE(materialsSell.date) as date,user.username as username "
			+ "FROM materialsSell,materialsSellDetail,user,materials "
			+ "WHERE "
			+ "materialsSell.id = materialsSellDetail.materialsSellId "
			+ "AND materialsSell.userId = user.id "
			+ "AND materials.id = materialsSellDetail.materialsId "
			+ "AND YEAR(materialsSell.date) = #{year} "
			+ "AND MONTH(materialsSell.date) = #{month}")
	public List<InOutDataVo> getInOutDataByDate(@Param("year") String year, @Param("month") String month);
	
	/**
	 * 根据年份和物料id获取进出仓记录
	 */
	@Select("SELECT materialsSell.id as id,materials.materialsName as materialsName,materialsSellDetail.total as total,materialsSell.type as type,DATE(materialsSell.date) as date,user.username as username "
			+ "FROM materialsSell,materialsSellDetail,user,materials "
			+ "WHERE "
			+ "materialsSell.id = materialsSellDetail.materialsSellId "
			+ "AND materialsSell.userId = user.id "
			+ "AND materials.id = materialsSellDetail.materialsId "
			+ "AND YEAR(materialsSell.date) = #{year} "
			+ "AND materials.id = #{materialsId}")
	public List<InOutDataVo> getBillDateByYearAndMaterial(@Param("materialsId") String materialsId, @Param("year") String year);
	
}
