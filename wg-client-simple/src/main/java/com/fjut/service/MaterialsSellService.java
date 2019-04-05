package com.fjut.service;

import java.util.List;

import com.fjut.pojo.MaterialsSell;
import com.fjut.pojo.MaterialsSellDetail;

/**
 * 进出仓服务类
 * @author LGX
 *
 */
public interface MaterialsSellService {
	
	/**
	 * 添加进仓
	 * @return
	 */
	public boolean addMaterialsSellIn(MaterialsSell materialsSell, List<MaterialsSellDetail> materialsSellDetailsList);
	
	/**
	 * 查找所有物料信息
	 */
	public Object[][] getAllMaterialSell();

}
