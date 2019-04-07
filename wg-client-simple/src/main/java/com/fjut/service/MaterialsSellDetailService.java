package com.fjut.service;

public interface MaterialsSellDetailService {
	
	/**
	 * 根据进出仓id获取进出仓商品详情
	 * @param materialsSellId
	 * @return
	 */
	public Object[][] MaterialsSellDetailByMaterialsSellId(String materialsSellId);
	
	/**
	 * 根据id删除订单详情
	 * @return
	 */
	public boolean deleteMaterialsSellDetailById(int id) throws Exception;
}
