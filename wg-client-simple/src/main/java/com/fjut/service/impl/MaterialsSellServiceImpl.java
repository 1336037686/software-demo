package com.fjut.service.impl;
import java.util.ArrayList;
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
import com.fjut.pojo.vo.ChartVo;
import com.fjut.pojo.vo.InOutDataVo;
import com.fjut.pojo.vo.MaterialsSellVo;
import com.fjut.service.MaterialsSellService;
import com.fjut.util.DateUtil;

@Service
@Transactional(rollbackFor = Exception.class)  	//添加事物，自动捕获异常
public class MaterialsSellServiceImpl implements MaterialsSellService {
	
	@Autowired
	private MaterialsMapper materialsMapper;
	
	@Autowired
	private MaterialsSellMapper materialsSellMapper;
	
	@Autowired
	private MaterialsSellDetailMapper materialsSellDetailMapper;

	/**
	 * 进仓操作
	 * @throws Exception 
	 */
	@Override
	public boolean addMaterialsSellIn(MaterialsSell materialsSell, List<MaterialsSellDetail> materialsSellDetailsList) throws Exception {
		//添加物料进出仓主表
		int addMaterialsSellResult = materialsSellMapper.addMaterialsSell(materialsSell);
		if(addMaterialsSellResult <= 0) throw new Exception();
		if(materialsSellDetailsList != null) {
			for (MaterialsSellDetail materialsSellDetail : materialsSellDetailsList) {
				//添加物料进出仓详情表
				int addMaterialsSellDetailResult = materialsSellDetailMapper.addMaterialsSellDetail(materialsSellDetail);
				if(addMaterialsSellDetailResult <= 0) throw new Exception();
				//更改物料库存
				Materials materials = materialsMapper.getMaterialsById(materialsSellDetail.getMaterialsId());
				int updateMaterialResult = materialsMapper.updateMaterialStockQuantity(materials.getId(), materials.getStockQuantity() + materialsSellDetail.getTotal());
				if(updateMaterialResult <= 0) throw new Exception();
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

	/**
	 * 根据id删除订单
	 */
	@Override
	public boolean deleteMaterialSell(String id) throws Exception {
		//先将详情删除再删除订单
		if(materialsSellDetailMapper.deleteMaterialsSellDetailByMaterialSellId(id) > 0 && materialsSellMapper.deleteMaterialsSellById(id) > 0) {
			return true;
		}else {
			throw new Exception();
		}
	}

	/**
	 * 搜索订单
	 * 只支持id和类型
	 */
	@Override
	public Object[][] searchMaterialSell(String input) {
		List<MaterialsSellVo> allMaterialsSell = null;
		int type = -1;
		if("进仓".equals(input.trim())) type = 1;
		if("出仓".equals(input.trim())) type = 0;
		if(type != -1) {
			allMaterialsSell = materialsSellMapper.searchMaterialSell(type);
		}else {			
			allMaterialsSell = materialsSellMapper.searchMaterialSell(input);
		}
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

	/**
	 * 查找所有年份
	 */
	@Override
	public List<String> getAllYear() {
		return materialsSellMapper.getAllYear();
	}

	/**
	 * 获取报表数据
	 */
	@Override
	public List<List<ChartVo>> getChartData(String materialsId, String year, int month1, int month2) {
		List<List<ChartVo>> result = new ArrayList<>();
		List<ChartVo> list1 = materialsSellMapper.getChartData(materialsId, year, month1, month2, 1);
		List<ChartVo> list2 = materialsSellMapper.getChartData(materialsId, year, month1, month2, 0);
		
		result.add(list1);
		result.add(list2);
		return result;
	}

	/**
	 * 查找所有有记录的年份
	 */
	@Override
	public List<String> getAllMonth(String year) {
		return materialsSellMapper.getAllMonth(year);
	}

	/**
	 * 根据日期获取进出仓记录
	 */
	@Override
	public Object[][] getInOutDataByDate(String year, String month) {
		List<InOutDataVo> list = materialsSellMapper.getInOutDataByDate(year, month);
		Object[][] result = null;
		if(list != null) {
			result = new Object[list.size()][6];
			for (int i = 0; i < list.size(); i++) {
				result[i][0] = list.get(i).getId();
				result[i][1] = list.get(i).getMaterialsName();
				result[i][2] = list.get(i).getTotal() > 0 ? list.get(i).getTotal() : -list.get(i).getTotal();
				result[i][3] = list.get(i).getType();
				result[i][4] = list.get(i).getDate();
				result[i][5] = list.get(i).getUsername();
			}
		}
		return result == null ? new Object[0][6] : result;
	}

	/**
	 * 根据年份和物料id获取进出仓记录
	 */
	@Override
	public Object[][] getBillDataByYearAndMaterial(String materialsId, String year) {
		//封装进出仓数据
		List<InOutDataVo> list = materialsSellMapper.getBillDateByYearAndMaterial(materialsId, year);
		Object[][] result = null;
		if(list != null) {
			result = new Object[list.size() + 1][6];
			for (int i = 1; i < list.size() + 1; i++) {
				result[i][0] = list.get(i - 1).getId();
				result[i][1] = list.get(i - 1).getMaterialsName();
				result[i][2] = list.get(i - 1).getTotal() > 0 ? list.get(i - 1).getTotal() : -list.get(i - 1).getTotal();
				result[i][3] = list.get(i - 1).getType();
				result[i][4] = list.get(i - 1).getDate();
				result[i][5] = list.get(i - 1).getUsername();
			}
		}
		//封装物料信息
		Materials materials = materialsMapper.getMaterialsById(materialsId);
		result[0][0] = materials.getMaterialsId();
		result[0][1] = materials.getMaterialsName();
		result[0][2] = materials.getModel();
		result[0][3] = materials.getUnit();
		result[0][4] = materials.getStockQuantity();
		
		return result == null ? new Object[0][7] : result;
	}
}
