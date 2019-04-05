package com.fjut.view.component;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
				if(DataUtil.isNull(sumStr)) {
					JOptionPane.showMessageDialog(null, "输入错误", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				isOk = true;
				okBtn.setEnabled(false);
				updateBtn.setEnabled(true);
				materialComboBox.setEnabled(false);
				sumField.setEnabled(false);
				int total = Integer.parseInt(sumStr);
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
	
}
