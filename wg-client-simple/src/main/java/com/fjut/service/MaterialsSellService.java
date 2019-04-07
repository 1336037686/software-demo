package com.fjut.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fjut.pojo.MaterialsSell;
import com.fjut.pojo.MaterialsSellDetail;
import com.fjut.pojo.vo.ChartVo;
import com.fjut.pojo.vo.InOutDataVo;

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
	public boolean addMaterialsSellIn(MaterialsSell materialsSell, List<MaterialsSellDetail> materialsSellDetailsList) throws Exception;
	
	/**
	 * 查找所有物料信息
	 */
	public Object[][] getAllMaterialSell();
	
	/**
	 * 根据id删除订单记录
	 */
	public boolean deleteMaterialSell(String id) throws Exception;
	
	/**
	 * 搜索
	 */
	public Object[][] searchMaterialSell(String input);
	
	/**
	 * 查找所有有记录年分
	 * @return
	 */
	public List<String> getAllYear();
	
	/**
	 * 根据年份查找所有有记录的月分
	 */
	public List<String> getAllMonth(String year);
	
	/**
	 * 获取
	 * @return
	 */
	public List<List<ChartVo>> getChartData(String materialsId, String year, int month1, int month2);
	
	/**
	 * 根据日期获取进出仓记录
	 */
	public Object[][] getInOutDataByDate(String year, String month);
	
	/**
	 * 根据年份和物料id获取进出仓记录
	 */
	public Object[][] getBillDataByYearAndMaterial(String materialsId, String year);

}
