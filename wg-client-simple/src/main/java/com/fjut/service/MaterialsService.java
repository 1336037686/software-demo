package com.fjut.service;

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
	 * 根据id获取物料对象
	 */
	public Materials getMaterialsByMaterialsId(String materialsId);
	
	
	/**
	 * 更新物料信息
	 */
	public boolean updateMaterials(Materials materials);
	
	/**
	 * 删除物料信息
	 */
	public boolean deleteMaterials(String id);
	
}
