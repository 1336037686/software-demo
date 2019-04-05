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
import com.fjut.pojo.vo.MaterialsSellVo;
import com.fjut.service.MaterialsSellService;
import com.fjut.util.DateUtil;

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

	/**
	 * 查找所有物料出入仓记录
	 */
	@Override
	public Object[][] getAllMaterialSell() {
		List<MaterialsSellVo> allMaterialsSell = materialsSellMapper.getAllMaterialsSell();
		if(allMaterialsSell != null) {			
			Object[][] materialMSG = new Object[allMaterialsSell.size()][6];
			for (int i = 0; i < allMaterialsSell.size(); i++) {
				materialMSG[i][0] = i + 1;
				materialMSG[i][1] = allMaterialsSell.get(i).getId();
				materialMSG[i][2] = allMaterialsSell.get(i).getUserName();
				materialMSG[i][3] = DateUtil.dateFormate(allMaterialsSell.get(i).getDate());
				materialMSG[i][4] = allMaterialsSell.get(i).getType() == 1 ? "进仓" : "出仓";
				materialMSG[i][5] = allMaterialsSell.get(i).getRemarks();
			}
			return materialMSG;
		}
		return new Object[0][0];
	}
}
