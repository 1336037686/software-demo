package com.fjut.service;

import java.util.List;

import com.fjut.pojo.Materials;

/**
 * 物料服务类
 * @author LGX
 *
 */
public interface MaterialsService {
	
	/**
	 * 添加物料
	 */
	public boolean addMaterial(Materials materials);
	
	/**
	 * 查找所有物料信息
	 */
	public Object[][] getAllMaterial();
	
	/**
	 * 查找所有物料信息
	 */
	public List<Materials> getAllMaterialList();
	
	/**
	 * 根据id获取物料对象
	 */
	public Materials getMaterialsByMaterialsId(String materialsId);
	
	/**
	 * 根据关键字查询物料信息 
	 */
	public Object[][] getSearchMaterial(String input);
	
	
	/**
	 * 更新物料信息
	 */
	public boolean updateMaterials(Materials materials);
	
	/**
	 * 删除物料信息
	 */
	public boolean deleteMaterials(String id);
	
}
