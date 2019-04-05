package com.fjut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fjut.dao.mapper.MaterialsSellDetailMapper;
import com.fjut.pojo.vo.MaterialsSellDetailVo;
import com.fjut.pojo.vo.MaterialsSellVo;
import com.fjut.service.MaterialsSellDetailService;
import com.fjut.util.DateUtil;

@Service
public class MaterialsSellDetailServiceImpl implements MaterialsSellDetailService {

	@Autowired
	private MaterialsSellDetailMapper materialsSellDetailMapper;
	
	/**
	 * 根据订单id获取订单详情
	 */
	@Override
	public Object[][] MaterialsSellDetailByMaterialsSellId(String materialsSellId) {
		List<MaterialsSellDetailVo> list = materialsSellDetailMapper.getMaterialsSellDetailsVoByMaterialsSellId(materialsSellId);
		if(list != null) {			
			Object[][] result = new Object[list.size()][4];
			for (int i = 0; i < list.size(); i++) {
				result[i][0] = i + 1;
				result[i][1] = list.get(i).getMaterialsName();
				result[i][2] = list.get(i).getTotal();
				result[i][3] = list.get(i).getId();
			}
			return result;
		}
		return new Object[0][0];
	}

}
