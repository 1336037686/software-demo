package com.fjut.view.page;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fjut.pojo.Materials;
import com.fjut.service.MaterialsService;
import com.fjut.util.DataUtil;
import com.fjut.util.MD5Util;
import com.fjut.util.SpringContextUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

/**
 * 物料档案添加界面
 * @author LGX
 *
 */
@Lazy
@Scope("prototype")
@Component("MaterialAddPage")
@SuppressWarnings("all")
public class MaterialAddPage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField materialIdField;
	private JTextField materialNameField;
	private JTextField materialModelField;
	
	private MaterialsService materialsService = SpringContextUtils.getBean(MaterialsService.class);
	
	private String[] materialUnits = {"件", "套", "公斤", "吨", "升", "米", "毫米", "个"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MaterialAddPage dialog = new MaterialAddPage();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MaterialAddPage() {
		setBounds(100, 100, 716, 543);
		setModal(true);
		//设置居中显示
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("物料添加");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(302, 10, 135, 27);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("物料代码");
		label_1.setBounds(47, 62, 54, 15);
		contentPanel.add(label_1);
		
		materialIdField = new JTextField();
		materialIdField.setBounds(111, 59, 211, 21);
		contentPanel.add(materialIdField);
		materialIdField.setColumns(10);
		
		JLabel label_2 = new JLabel("物料名称");
		label_2.setBounds(352, 62, 54, 15);
		contentPanel.add(label_2);
		
		materialNameField = new JTextField();
		materialNameField.setBounds(416, 59, 229, 21);
		contentPanel.add(materialNameField);
		materialNameField.setColumns(10);
		
		JLabel label_3 = new JLabel("型号规格");
		label_3.setBounds(47, 107, 54, 15);
		contentPanel.add(label_3);
		
		materialModelField = new JTextField();
		materialModelField.setColumns(10);
		materialModelField.setBounds(111, 104, 211, 21);
		contentPanel.add(materialModelField);
		
		JLabel label_4 = new JLabel("计量单位");
		label_4.setBounds(352, 107, 54, 15);
		contentPanel.add(label_4);
		
		JComboBox materialUnitField = new JComboBox();
		materialUnitField.setBounds(416, 104, 229, 21);
		for (String unit : materialUnits) {
			materialUnitField.addItem(unit);
		}
		contentPanel.add(materialUnitField);
		
		JLabel label_5 = new JLabel("物料备注");
		label_5.setBounds(47, 148, 54, 15);
		contentPanel.add(label_5);
		
		JTextArea materialRemarksFiled = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(materialRemarksFiled);
		scrollPane.setBounds(47, 173, 598, 237);
		contentPanel.add(scrollPane);
		
		JButton addBtn = new JButton("添加");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String materialId = materialIdField.getText();
				String materialRemarks = materialRemarksFiled.getText();
				String materialModel = materialModelField.getText();
				String materialName = materialNameField.getText();
				String materialUnit = (String) materialUnitField.getSelectedItem();
				
				boolean dataCheck = DataUtil.dataCheck(materialId, materialName, materialUnit, materialModel);
				if(!dataCheck) {
					JOptionPane.showMessageDialog(null, "填写错误", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//添加数据
				Materials materials = new Materials(MD5Util.getMD5(), materialId, materialName, materialModel, materialUnit, 0, materialRemarks, new Date(), 0);
				if(materialsService.addMaterial(materials)) {
					setVisible(false);
					dispose();
				}
			}
		});
		addBtn.setBounds(449, 457, 93, 23);
		contentPanel.add(addBtn);
		
		JButton resetBtn = new JButton("重置");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				materialIdField.setText("");
				materialRemarksFiled.setText("");
				materialModelField.setText("");
				materialNameField.setText("");
				materialUnitField.setSelectedIndex(0);
			}
		});
		resetBtn.setBounds(552, 457, 93, 23);
		contentPanel.add(resetBtn);
	}
}
