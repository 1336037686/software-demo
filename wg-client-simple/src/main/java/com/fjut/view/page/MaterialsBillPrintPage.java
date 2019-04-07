package com.fjut.view.page;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.fjut.pojo.Materials;
import com.fjut.pojo.vo.ComboxVo;
import com.fjut.service.MaterialsSellService;
import com.fjut.service.MaterialsService;
import com.fjut.util.POIUtil;
import com.fjut.util.SpringContextUtils;

@SuppressWarnings("all")
public class MaterialsBillPrintPage extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MaterialsBillPrintPage dialog = new MaterialsBillPrintPage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MaterialsBillPrintPage() {
		
		MaterialsService materialsService = SpringContextUtils.getBean(MaterialsService.class);
		MaterialsSellService materialsSellService = SpringContextUtils.getBean(MaterialsSellService.class);
		
		setBounds(100, 100, 286, 243);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("打印物料仓库账单");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(44, 10, 187, 24);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("物料");
		label_1.setBounds(21, 64, 54, 15);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("年份");
		label_2.setBounds(21, 108, 54, 15);
		contentPanel.add(label_2);
		
		JComboBox materialsComBox = new JComboBox();
		materialsComBox.setBounds(66, 61, 165, 21);
		contentPanel.add(materialsComBox);
		
		JComboBox yearComBox = new JComboBox();
		yearComBox.setBounds(66, 105, 165, 21);
		contentPanel.add(yearComBox);
		
		List<Materials> materialList = materialsService.getAllMaterialList();
		if(materialList != null) {
			for (Materials materials : materialList) {
				materialsComBox.addItem(new ComboxVo(materials.getId(), materials.getMaterialsName()));
			}
		}
		
		List<String> years = materialsSellService.getAllYear();
		if(years != null) {
			for (String year : years) {
				yearComBox.addItem(year);
			}
		}
		
		JButton printBtn = new JButton("打印报表");
		printBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(yearComBox.getSelectedItem() == null || materialsComBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "选择错误", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				ComboxVo materialsComboxVo = (ComboxVo) materialsComBox.getSelectedItem();
				String year = (String) yearComBox.getSelectedItem();
				
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				jFileChooser.showSaveDialog(null);
				//获取保存目录
				File selectedFile = jFileChooser.getSelectedFile();
				if(selectedFile != null) {
					File file = new File(selectedFile.getAbsoluteFile() + "/" + materialsComboxVo.getValue() + "-" + year + "-仓库账单报表.xlsx");
					Object[][] data = materialsSellService.getBillDataByYearAndMaterial(materialsComboxVo.getKey(), year);
					String[] columnNames1 = {"物料代码", "物料名", "物料规格", "计量单位", "库存"};
					String[] columnNames2 = {"订单号", "物料名", "操作总数", "进出仓类型", "操作日期", "操作人员"};
					Object[][] data1 = new Object[1][5];
					Object[][] data2 = new Object[data.length - 1][6];
					
					//封装数据
					for (int i = 0; i < data[0].length; i++) {
						if(data[0][i] == null) break;
						data1[0][i] = data[0][i];
					}
					
					for (int j = 1; j < data.length; j++) {
						data2[j-1] = data[j];
					}
					
					if(POIUtil.createXlsx(file, columnNames1 , data1, columnNames2, data2)) {
						JOptionPane.showMessageDialog(null, "导出成功", "提示", JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "导出失败", "提示", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		printBtn.setBounds(93, 162, 93, 23);
		contentPanel.add(printBtn);

	}

}
