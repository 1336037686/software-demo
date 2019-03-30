package com.fjut.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.fjut.pojo.Materials;

/**
 * 物料操作Mapper
 * @author LGX
 *
 */
@Mapper
public interface MaterialsMapper {
	
	/**
	 * 添加物料
	 */
	@Insert("insert into materials values (#{id}, #{materialsId}, #{materialsName}, #{model}, #{unit}, #{stockQuantity}, #{remarks}, #{createDate}, #{isDelete})")
	public int addMaterials(Materials materials);
	
	/**
	 * 根据id查找物料
	 */
	@Select("select * from materials where materialsId = #{materialsId} and isDelete = 0")
	public Materials getMaterialsByMaterialsId(String materialsId);
	
	/**
	 * 查找所有物料档案
	 */
	@Select("select * from materials where isDelete = 0")
	public List<Materials> getAllMaterials();
	
	/**
	 * 修改物料信息
	 * 修改：物料名，规格，单位，备注
	 */
	@Update("update materials set materialsName = #{materialsName}, model = #{model}, unit = #{unit}, remarks = #{remarks} where id = #{id}")
	public int updateMaterial(Materials materials);
	
	
	/**
	 * 删除物料
	 */
	@Update("update materials set isDelete = 1 where id = #{id}")
	public int deleteMaterial(String id);

}
