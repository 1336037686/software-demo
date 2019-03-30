package com.fjut.service.impl;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fjut.dao.mapper.MaterialsMapper;
import com.fjut.pojo.Materials;
import com.fjut.pojo.User;
import com.fjut.service.MaterialsService;
import com.fjut.util.DateUtil;

/**
 * 物料服务
 * @author LGX
 *
 */
@Service
public class MaterialsServiceImpl implements MaterialsService {

	@Autowired
	private MaterialsMapper materialsMapper;
	
	/**
	 * 添加物料
	 */
	@Override
	public boolean addMaterial(Materials materials) {
		Materials isExist = materialsMapper.getMaterialsByMaterialsId(materials.getMaterialsId());
		if(isExist != null) {
			JOptionPane.showMessageDialog(null, "该物料代码已经存在，请重新输入", "提示", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(materialsMapper.addMaterials(materials) > 0) {
			JOptionPane.showMessageDialog(null, "添加成功", "Message", JOptionPane.INFORMATION_MESSAGE);
			return true;
		}else {			
			JOptionPane.showMessageDialog(null, "添加失败", "Message", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
	}

	/**
	 * 获取所有物料信息
	 */
	@Override
	public Object[][] getAllMaterial() {
		List<Materials> materialsList = materialsMapper.getAllMaterials();
		if(materialsList != null) {			
			Object[][] materialMSG = new Object[materialsList.size()][6];
			for (int i = 0; i < materialsList.size(); i++) {
				materialMSG[i][0] = i + 1;
				materialMSG[i][1] = materialsList.get(i).getMaterialsId();
				materialMSG[i][2] = materialsList.get(i).getMaterialsName();
				materialMSG[i][3] = materialsList.get(i).getModel();
				materialMSG[i][4] = materialsList.get(i).getUnit();
				materialMSG[i][5] = DateUtil.dateFormate(materialsList.get(i).getCreateDate());
			}
			return materialMSG;
		}
		return new Object[0][0];
	}

	/**
	 * 根据物料代码获取物料
	 */
	@Override
	public Materials getMaterialsByMaterialsId(String materialsId) {
		return materialsMapper.getMaterialsByMaterialsId(materialsId);
	}

	/**
	 * 更新物料信息
	 */
	@Override
	public boolean updateMaterials(Materials materials) {
		if(materialsMapper.updateMaterial(materials) > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 删除物料信息
	 */
	@Override
	public boolean deleteMaterials(String id) {
		if(materialsMapper.deleteMaterial(id) > 0) return true;
		return false;
	}

}
