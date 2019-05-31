package com.fjut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fjut.dao.mapper.MaterialsMapper;
import com.fjut.dao.mapper.MaterialsSellDetailMapper;
import com.fjut.dao.mapper.MaterialsSellMapper;
import com.fjut.pojo.Materials;
import com.fjut.pojo.MaterialsSellDetail;
import com.fjut.pojo.vo.MaterialsSellDetailVo;
import com.fjut.service.MaterialsSellDetailService;

/**
 * 物料订单详情实现类
 * @Transactional(rollbackFor = Exception.class) 设置开启事务，设置事务的触发机制为抛出异常（Exception）
 * @Service 设置扫描类型为服务层
 * @Autowired 自动注入
 * @SuppressWarnings("all") 去除所有警告
 * 
 *
 */
@Service
@Transactional(rollbackFor = Exception.class) 
@SuppressWarnings("all")
public class MaterialsSellDetailServiceImpl implements MaterialsSellDetailService {

	@Autowired 
	private MaterialsSellDetailMapper materialsSellDetailMapper;
	
	@Autowired
	private MaterialsSellMapper materialsSellMapper;
	
	@Autowired
	private MaterialsMapper materialsMapper;
	
	/**
	 * 根据订单id获取订单详情
	 */
	@Override
	public Object[][] MaterialsSellDetailByMaterialsSellId(String materialsSellId) {
		//根据订单id获取查询数据
		List<MaterialsSellDetailVo> list = materialsSellDetailMapper.getMaterialsSellDetailsVoByMaterialsSellId(materialsSellId);
		//遍历数据集合，封装数据到二维数组
		if(list != null) {			
			Object[][] result = new Object[list.size()][4];
			for (int i = 0; i < list.size(); i++) {
				result[i][0] = i + 1;
				result[i][1] = list.get(i).getMaterialsName();
				result[i][2] = list.get(i).getTotal() > 0 ? list.get(i).getTotal() : -list.get(i).getTotal();
				result[i][3] = list.get(i).getId();
			}
			return result;
		}
		return new Object[0][0];
	}

//	@Override
//	public boolean deleteMaterialsSellDetailById(int id) throws Exception {
//		MaterialsSellDetail materialsSellDetail = materialsSellDetailMapper.getMaterialsSellDetailById(id);
//		List<MaterialsSellDetailVo> list = materialsSellDetailMapper.getMaterialsSellDetailsVoByMaterialsSellId(materialsSellDetail.getMaterialsSellId());
//		//如果记录详情只剩一条，删除的时候需要把订单一起删除
//		if(list.size() == 1) { 
//			if(materialsSellDetailMapper.deleteMaterialsSellDetailById(id) > 0 && materialsSellMapper.deleteMaterialsSellById(materialsSellDetail.getMaterialsSellId()) > 0) {
//				return true;
//			}
//			throw new Exception();
//		}
//		//如果大于一条
//		if(materialsSellDetailMapper.deleteMaterialsSellDetailById(id) > 0) {
//			return true;
//		}
//		throw new Exception();
//	}
}
