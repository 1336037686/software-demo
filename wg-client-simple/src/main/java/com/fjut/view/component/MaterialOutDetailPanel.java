package com.fjut.view.component;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.fjut.pojo.Materials;
import com.fjut.pojo.MaterialsSell;
import com.fjut.pojo.MaterialsSellDetail;
import com.fjut.pojo.vo.ComboxVo;
import com.fjut.util.DataUtil;
import com.fjut.util.MD5Util;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;

/**
 * 进仓详情
 * @author LGX
 *
 */
@SuppressWarnings("all")
public class MaterialOutDetailPanel extends JPanel {
	private JButton okBtn;
	private JButton updateBtn;						
	
	private MaterialsSellDetail materialsSellDetail;													//存储数据
	private boolean isOk = false; 																		//是否确认
	private JTextField textField;

	/**
	 * Create the panel. 627, 43
	 */
	public MaterialOutDetailPanel(int x, int y, int index, String materialsSellId, List<Materials> materialList) {
		setLayout(null);
		JLabel lblNewLabel = new JLabel("物料选择");
		add(lblNewLabel);
		lblNewLabel.setBounds(10, 12, 54, 18);
		
		JComboBox materialComboBox = new JComboBox();
		add(materialComboBox);
		if(materialList != null) {
			for (Materials m : materialList) {			
				materialComboBox.addItem(new ComboxVo(m.getId(), m.getMaterialsName()));
			}
		}
		materialComboBox.setBounds(74, 11, 98, 21);
		
		
		JLabel label = new JLabel("剩余");
		label.setBounds(182, 14, 33, 15);
		add(label);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(216, 11, 80, 21);
		add(textField);
		textField.setColumns(10);
		if(materialList.size() > 0) {			
			textField.setText(materialList.get(0).getStockQuantity() + "");
		}

		JLabel label_6 = new JLabel("数量");
		add(label_6);
		label_6.setBounds(306, 14, 54, 15);
		
		JTextField sumField = new JTextField();
		add(sumField);
		sumField.setBounds(344, 11, 66, 21);
		sumField.setColumns(10);
		
		//下拉选监听
		materialComboBox.addItemListener((e) -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				ComboxVo vo = (ComboxVo) e.getItem();
				Materials materials = getMaterialById(vo.getKey(), materialList);
				textField.setText(materials.getStockQuantity() + "");
            }
		});
		
		
		okBtn = new JButton("确认");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComboxVo materialSelect = (ComboxVo) materialComboBox.getSelectedItem();
				
				String sumStr = sumField.getText();
				if(MaterialInDetailPanel.selectMaterial.contains(materialSelect.getKey())) {
					JOptionPane.showMessageDialog(null, "不能重复添加", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(DataUtil.isNull(sumStr) && !DataUtil.isIntStr(sumStr)) {
					JOptionPane.showMessageDialog(null, "输入错误", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int total = Integer.parseInt(sumStr);
				if(total <= 0) {
					JOptionPane.showMessageDialog(null, "数量不能小于或等于0", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(total > getMaterialById(materialSelect.getKey(), materialList).getStockQuantity()) {
					JOptionPane.showMessageDialog(null, "数量大于库存", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				isOk = true;
				okBtn.setEnabled(false);
				updateBtn.setEnabled(true);
				materialComboBox.setEnabled(false);
				sumField.setEnabled(false);
				MaterialInDetailPanel.selectMaterial.add(materialSelect.getKey());
				materialsSellDetail = new MaterialsSellDetail(0, materialsSellId, materialSelect.getKey(), -total);
			}
		});
		add(okBtn);
		okBtn.setBounds(432, 10, 75, 23);
		setBounds(x, y, 625, 43);
		
		updateBtn = new JButton("修改");
		updateBtn.setEnabled(false);
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MaterialInDetailPanel.selectMaterial.remove(materialsSellDetail.getMaterialsId());
				isOk = false;
				okBtn.setEnabled(true);
				updateBtn.setEnabled(false);
				materialComboBox.setEnabled(true);
				sumField.setEnabled(true);
			}
		});
		updateBtn.setBounds(517, 10, 75, 23);
		add(updateBtn);

	}
	
	/**
	 * 判断当前进仓单号已确认
	 */
	public boolean isOK() {
		return this.isOk;
	}

	/**
	 * 获取数据
	 */
	public MaterialsSellDetail getMaterialsSellDetailData() {
		return materialsSellDetail;
	}
	
	/**
	 * 根据ID获取物料信息
	 * @param id
	 * @param materialsList
	 * @return
	 */
	public Materials getMaterialById(String id, List<Materials> materialsList) {
		if(materialsList == null) return null;
		for (Materials materials : materialsList) {
			if(materials.getId().equals(id)) {
				return materials;
			}
		}
		return null;
	}
	
}
