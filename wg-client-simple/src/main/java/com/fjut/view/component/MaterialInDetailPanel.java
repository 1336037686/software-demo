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
import java.awt.event.ActionEvent;

/**
 * 进仓详情
 * @author LGX
 *
 */
@SuppressWarnings("all")
public class MaterialInDetailPanel extends JPanel {
	
	public static Set<String> selectMaterial = new HashSet<>();
	
	private JButton okBtn;
	private JButton updateBtn;						
	
	private MaterialsSellDetail materialsSellDetail;													//存储数据
	private boolean isOk = false; 																		//是否确认

	/**
	 * Create the panel. 627, 43
	 */
	public MaterialInDetailPanel(int x, int y, int index, String materialsSellId, List<Materials> materialList) {
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
		materialComboBox.setBounds(74, 11, 132, 21);
		
		JLabel label_6 = new JLabel("数量");
		add(label_6);
		label_6.setBounds(250, 14, 54, 15);
		
		JTextField sumField = new JTextField();
		add(sumField);
		sumField.setBounds(289, 11, 105, 21);
		sumField.setColumns(10);
		
		okBtn = new JButton("确认");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComboxVo materialSelect = (ComboxVo) materialComboBox.getSelectedItem();
				String sumStr = sumField.getText();
				//判断是否已经添加当前物料
				if(selectMaterial.contains(materialSelect.getKey())) {
					JOptionPane.showMessageDialog(null, "不能重复添加", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//数据检验
				if(DataUtil.isNull(sumStr) && !DataUtil.isIntStr(sumStr)) {
					JOptionPane.showMessageDialog(null, "输入错误", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int total = Integer.parseInt(sumStr);
				if(total <= 0) {
					JOptionPane.showMessageDialog(null, "数量不能小于或等于0", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//设置isOk（当前订单小条目）为确认状态
				isOk = true;
				//设置确认按钮为不可点击状态
				okBtn.setEnabled(false);
				//设置修改按钮为可点击状态
				updateBtn.setEnabled(true);
				//设置下拉列表不可选择
				materialComboBox.setEnabled(false);
				//设置数量选择为不可选择
				sumField.setEnabled(false);
				//添加物料条目到Set集合中，该集合表示已经确认的物料订单
				selectMaterial.add(materialSelect.getKey());
				materialsSellDetail = new MaterialsSellDetail(0, materialsSellId, materialSelect.getKey(), total);
			}
		});
		add(okBtn);
		okBtn.setBounds(432, 10, 75, 23);
		setBounds(x, y, 625, 43);
		
		updateBtn = new JButton("修改");
		updateBtn.setEnabled(false);
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//点击修改时，从保存的集合中删除当前物料订单
				selectMaterial.remove(materialsSellDetail.getMaterialsId());
				//设置isOk为未确认状态
				isOk = false;
				//设置确认按钮可以点击
				okBtn.setEnabled(true);
				//设置更新按钮不能点击
				updateBtn.setEnabled(false);
				//设置物料下拉菜单可以选择
				materialComboBox.setEnabled(true);
				//设置数量为可以编辑
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
	
}
