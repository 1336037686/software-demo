package com.fjut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fjut.dao.mapper.MaterialsMapper;
import com.fjut.dao.mapper.MaterialsSellDetailMapper;
import com.fjut.dao.mapper.MaterialsSellMapper;
import com.fjut.pojo.Materials;
import com.fjut.pojo.MaterialsSell;
import com.fjut.pojo.MaterialsSellDetail;
import com.fjut.service.MaterialsSellService;

@Service
@Transactional 	//添加事物
public class MaterialsSellServiceImpl implements MaterialsSellService {
	
	@Autowired
	private MaterialsMapper materialsMapper;
	
	@Autowired
	private MaterialsSellMapper materialsSellMapper;
	
	@Autowired
	private MaterialsSellDetailMapper materialsSellDetailMapper;

	/**
	 * 进仓操作
	 */
	@Override
	public boolean addMaterialsSellIn(MaterialsSell materialsSell, List<MaterialsSellDetail> materialsSellDetailsList) {
		System.out.println(materialsSell);
		System.out.println(materialsSellDetailsList);
		//添加物料进出仓主表
		int addMaterialsSellResult = materialsSellMapper.addMaterialsSell(materialsSell);
		if(addMaterialsSellResult <= 0) return false;
		if(materialsSellDetailsList != null) {
			for (MaterialsSellDetail materialsSellDetail : materialsSellDetailsList) {
				//添加物料进出仓详情表
				int addMaterialsSellDetailResult = materialsSellDetailMapper.addMaterialsSellDetail(materialsSellDetail);
				if(addMaterialsSellDetailResult <= 0) return false;
				//更改物料库存
				Materials materials = materialsMapper.getMaterialsById(materialsSellDetail.getMaterialsId());
				int updateMaterialResult = materialsMapper.updateMaterialStockQuantity(materials.getId(), materials.getStockQuantity() + materialsSellDetail.getTotal());
				if(updateMaterialResult <= 0) return false;
			}
		}
		return true;
	}
}
