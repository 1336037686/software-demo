package com.fjut.view.component;

import javax.swing.JPanel;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fjut.pojo.Materials;
import com.fjut.pojo.User;
import com.fjut.service.MaterialsService;
import com.fjut.util.DataUtil;
import com.fjut.util.DateUtil;
import com.fjut.util.SpringContextUtils;
import com.fjut.view.page.MaterialAddPage;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * 物料管理面板
 * @author LGX
 *
 */
@Lazy
@Scope("prototype")
@Component("MaterialManagerPanel")
@SuppressWarnings("all")
public class MaterialManagerPanel extends JPanel {
	private JTextField materialSearchField;
	private JTextField idField;
	private JTextField materialIdField;
	private JTextField materialNameField;
	private JTextField materialModelField;
	private JTextField materialsCreateDateField;
	private JTextArea materialRemarksFiled;
	private JComboBox materialUnitField;
	private TableComponent table;
	
	//物料单位
	private String[] materialUnits = {"件", "套", "公斤", "吨", "升", "米", "毫米", "个"};
	private MaterialsService materialsService = SpringContextUtils.getBean(MaterialsService.class);
	
	// 表格数据
	private Object[] columnNames = { "序号", "物料代码", "物料名称", "型号规格", "计量单位", "库存数量", "创建时间" };
	private Object[][] rowData;

	/**
	 * Create the panel.
	 */
	public MaterialManagerPanel() {
		setBounds(0, 0, 1165, 730);
		setLayout(null);
		
		JButton materialAddBtn = new JButton("添加物料档案");
		materialAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//创建MaterialAddPage实例对象
				MaterialAddPage materialAddPage = (MaterialAddPage) SpringContextUtils.getBean("MaterialAddPage");
				//设置物料界面显示
				materialAddPage.setVisible(true);
			}
		});
		materialAddBtn.setBounds(10, 10, 132, 23);
		add(materialAddBtn);
		
		materialSearchField = new JTextField();
		materialSearchField.setBounds(287, 11, 363, 21);
		add(materialSearchField);
		materialSearchField.setColumns(10);
		
		JButton searchBtn = new JButton("搜索");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取搜索框数据
				String searchInput = materialSearchField.getText();
				//数据校验（是否为空）
				if(DataUtil.isNull(searchInput)) {
					JOptionPane.showMessageDialog(null, "输入为空，请重新输入", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//更新用户列表
				updateUserList(1, searchInput);
			}
		});
		searchBtn.setBounds(660, 10, 93, 23);
		add(searchBtn);
		
		JPanel materialListPanel = new JPanel();
		materialListPanel.setBorder(BorderFactory.createTitledBorder("物料列表"));
		materialListPanel.setBounds(10, 43, 743, 670);
		add(materialListPanel);
		materialListPanel.setLayout(null);
		
		//添加数据
		rowData = materialsService.getAllMaterial();
		
//		Object[][] rowData = { 
//			{ 1, "张三", 80, 80, 80, 240 }, 
//			{ 2, "John", 70, 80, 90, 240 },
//			{ 3, "Sue", 70, 70, 70, 210 }, 
//			{ 4, "Jane", 80, 70, 60, 210 }, 
//			{ 1, "张三", 80, 80, 80, 240 }, 
//			{ 2, "John", 70, 80, 90, 240 }
//		};
//		
		// 创建一个表格，指定 所有行数据 和 表头
		table = new TableComponent(rowData, columnNames);
		// 添加表头
		materialListPanel.add(table.getTableHeader());
		materialListPanel.setLayout(new BorderLayout(5, 5));
		// 将表格添加到滚动面板中
		JScrollPane scrollPane = new JScrollPane(table);
		materialListPanel.add(scrollPane);
		
		
		JPanel materialInfoPanel = new JPanel();
		materialInfoPanel.setBorder(BorderFactory.createTitledBorder("物料信息管理"));
		materialInfoPanel.setBounds(763, 43, 392, 665);
		add(materialInfoPanel);
		materialInfoPanel.setLayout(null);
		
		JLabel label = new JLabel("物料代码");
		label.setBounds(29, 85, 54, 15);
		materialInfoPanel.add(label);
		
		materialIdField = new JTextField();
		materialIdField.setEnabled(false);
		materialIdField.setBounds(96, 82, 263, 21);
		materialInfoPanel.add(materialIdField);
		materialIdField.setColumns(10);
		
		JLabel label_1 = new JLabel("物料名称");
		label_1.setBounds(29, 128, 54, 15);
		materialInfoPanel.add(label_1);
		
		materialNameField = new JTextField();
		materialNameField.setBounds(96, 125, 263, 21);
		materialInfoPanel.add(materialNameField);
		materialNameField.setColumns(10);
		
		JLabel label_2 = new JLabel("型号规格");
		label_2.setBounds(29, 171, 54, 15);
		materialInfoPanel.add(label_2);
		
		materialModelField = new JTextField();
		materialModelField.setColumns(10);
		materialModelField.setBounds(96, 168, 263, 21);
		materialInfoPanel.add(materialModelField);
		
		JLabel label_3 = new JLabel("计量单位");
		label_3.setBounds(29, 213, 54, 15);
		materialInfoPanel.add(label_3);
		
		materialUnitField = new JComboBox();
		materialUnitField.setBounds(96, 210, 263, 21);
		for (String unit : materialUnits) {
			materialUnitField.addItem(unit);
		}
		materialInfoPanel.add(materialUnitField);
		
		JLabel label_4 = new JLabel("创建日期");
		label_4.setBounds(29, 260, 54, 15);
		materialInfoPanel.add(label_4);
		
		materialRemarksFiled = new JTextArea();
		JScrollPane materialRemarksFiledScrollPane = new JScrollPane(materialRemarksFiled);
		materialRemarksFiledScrollPane.setBounds(29, 325, 330, 184);
		materialInfoPanel.add(materialRemarksFiledScrollPane);
		
		JLabel label_5 = new JLabel("物料备注");
		label_5.setBounds(29, 300, 54, 15);
		materialInfoPanel.add(label_5);
		
		materialsCreateDateField = new JTextField();
		materialsCreateDateField.setEnabled(false);
		materialsCreateDateField.setColumns(10);
		materialsCreateDateField.setBounds(96, 257, 263, 21);
		materialInfoPanel.add(materialsCreateDateField);
		
		JButton updateBtn = new JButton("修改");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//确认框
				if(JOptionPane.showConfirmDialog(null, "确认修改？") == 0) {
						//获取选中的记录ID
						String id = idField.getText();
						//数据校验，如果为null，代表没有选中数据
						if(DataUtil.isNull(id)) {
							JOptionPane.showMessageDialog(null, "请先选择一条记录", "提示", JOptionPane.ERROR_MESSAGE);
							return;
						}
						//获取修改数据
						String materialId = materialIdField.getText();
						String materialRemarks = materialRemarksFiled.getText();
						String materialModel = materialModelField.getText();
						String materialName = materialNameField.getText();
						String materialUnit = (String) materialUnitField.getSelectedItem();
						
						//数据校验
						boolean dataCheck = DataUtil.dataCheck(materialId, materialName, materialUnit, materialModel);
						if(!dataCheck) {
							JOptionPane.showMessageDialog(null, "填写错误", "提示", JOptionPane.ERROR_MESSAGE);
							return;
						}
						//更新数据
						try {
							if(materialsService.updateMaterials(new Materials(id, materialId, materialName, materialModel, materialUnit, 0, materialRemarks, null, 0))) {
								JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
								//清空表单数据，将填写的表哥清空
								clearField();
							}
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "修改失败", "提示", JOptionPane.ERROR_MESSAGE);
						}
				}
			}
		});
		updateBtn.setBounds(29, 625, 93, 23);
		materialInfoPanel.add(updateBtn);
		
		JButton deleteBtn = new JButton("删除");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "确认删除？") == 0) {
						//获取选中需要删除的记录ID
						String id = idField.getText();
						//数据校验
						if(DataUtil.isNull(id)) {
							JOptionPane.showMessageDialog(null, "请先选择一条记录", "提示", JOptionPane.ERROR_MESSAGE);
							return;
						}
						//删除物料
						try {
							if(materialsService.deleteMaterials(id)) {
								JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
								clearField();
							}
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "删除失败", "提示", JOptionPane.ERROR_MESSAGE);
						}
				}
			}
		});
		deleteBtn.setBounds(266, 625, 93, 23);
		materialInfoPanel.add(deleteBtn);
		
		JLabel lblid = new JLabel("物料ID");
		lblid.setBounds(29, 43, 54, 15);
		materialInfoPanel.add(lblid);
		
		idField = new JTextField();
		idField.setEnabled(false);
		idField.setColumns(10);
		idField.setBounds(96, 40, 263, 21);
		materialInfoPanel.add(idField);
		
		JButton flushBtn = new JButton("刷新");
		flushBtn.setBounds(164, 10, 68, 23);
		add(flushBtn);
		
		// 设置表格监听事件
		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					String str = e.getSource().toString();
					// 取出当前行
					String indexStr = str.substring(str.lastIndexOf("{") + 1, str.lastIndexOf("}"));
					if (str != null && !"".equals(str.trim()) && indexStr != null && !"".equals(indexStr.trim())) {
						// 获取当前行序号
						int index = Integer.parseInt(indexStr);
						String materialsId = (String) rowData[index][1];
						// 获取选中的用户信息
						Materials selectedMaterials = materialsService.getMaterialsByMaterialsId(materialsId);
						if (selectedMaterials == null) {
							JOptionPane.showMessageDialog(null, "查找出错", "提示", JOptionPane.ERROR_MESSAGE);
							return;
						}
						// 封装数据
						idField.setText(selectedMaterials.getId());
						materialIdField.setText(selectedMaterials.getMaterialsId());
						materialNameField.setText(selectedMaterials.getMaterialsName());
						materialModelField.setText(selectedMaterials.getModel());
						materialUnitField.setSelectedIndex(DataUtil.getTargetIndex(materialUnits, selectedMaterials.getUnit()));
						materialRemarksFiled.setText(selectedMaterials.getRemarks());
						materialsCreateDateField.setText(DateUtil.dateFormate(selectedMaterials.getCreateDate()));
					}
				}
			}
		});
		
		// 刷新
		flushBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("刷新");
				updateUserList(0, null);
			}
		});
		
	}
	
	/**
	 * 更新用户列表
	 * 
	 * @param type
	 *            0 查找所有, 1 根据用户输入查找
	 * @param input
	 */
	private void updateUserList(int type, String input) {
		if (type == 0) {
			rowData = materialsService.getAllMaterial();
		}
		if (type == 1) {
			rowData = materialsService.getSearchMaterial(input);
		}
		table.updateModel(rowData, columnNames);
		clearField();
	}
	
	/**
	 * 清空输入框数据
	 */
	private void clearField() {
		idField.setText("");
		materialIdField.setText("");
		materialRemarksFiled.setText("");
		materialModelField.setText("");
		materialNameField.setText("");
		materialUnitField.setSelectedIndex(0);
	}
}
