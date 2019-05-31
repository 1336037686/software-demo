package com.fjut.view.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fjut.pojo.Materials;
import com.fjut.pojo.vo.ComboxVo;
import com.fjut.service.MaterialsSellDetailService;
import com.fjut.service.MaterialsSellService;
import com.fjut.service.MaterialsService;
import com.fjut.util.MD5Util;
import com.fjut.util.POIUtil;
import com.fjut.util.SpringContextUtils;
import com.fjut.view.page.MaterialsBillPrintPage;
import com.fjut.view.page.MaterialsInOutPrintPage;

/**
 * 报表管理面板
 * @Scope("prototype") 设置为动态创建，每调用一次当前类就创建一次
 * @Component("FormManagerPanel") 设置当前为组件
 * @Lazy 设置为懒加载
 */
@Lazy
@Scope("prototype")
@Component("FormManagerPanel")
@SuppressWarnings("all")
public class FormManagerPanel extends JPanel {
	
	private JPanel chartPanel;
	
	private MaterialsService materialsService = SpringContextUtils.getBean(MaterialsService.class);
	private MaterialsSellService materialsSellService = SpringContextUtils.getBean(MaterialsSellService.class);
	private MaterialsSellDetailService materialsSellDetailService = SpringContextUtils.getBean(MaterialsSellDetailService.class);

	/**
	 * Create the panel.
	 */
	public FormManagerPanel() {
		setBounds(0, 0, 1165, 730);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(10, 76, 1145, 624);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(BorderFactory.createTitledBorder("报表查看"));
		panel_1.setBounds(10, 49, 1125, 525);
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel label_3 = new JLabel("物料");
		label_3.setBounds(10, 14, 54, 15);
		panel.add(label_3);
		
		JComboBox materialsComboBox = new JComboBox();
		materialsComboBox.setBounds(40, 11, 97, 21);
		panel.add(materialsComboBox);
		
		JLabel label = new JLabel("年份");
		label.setBounds(147, 15, 54, 15);
		panel.add(label);
		
		JComboBox yearComboBox = new JComboBox();
		yearComboBox.setBounds(182, 12, 97, 21);
		panel.add(yearComboBox);
		
		JLabel label_1 = new JLabel("月份区间");
		label_1.setBounds(289, 15, 54, 15);
		panel.add(label_1);
		
		
		JComboBox monthComBox1 = new JComboBox();
		monthComBox1.setBounds(345, 12, 62, 21);
		panel.add(monthComBox1);
		
		JLabel label_2 = new JLabel("-");
		label_2.setBounds(412, 15, 24, 15);
		panel.add(label_2);
		
		JComboBox monthComBox2 = new JComboBox();
		monthComBox2.setBounds(422, 12, 62, 21);
		panel.add(monthComBox2);
		
		JButton lookBtn = new JButton("查看");
		//查看图表
		lookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComboxVo material =  (ComboxVo) materialsComboBox.getSelectedItem();
				//获取时间区间
				String year = (String) yearComboBox.getSelectedItem();
				int month1 = (int) monthComBox1.getSelectedItem();
				int month2 = (int) monthComBox2.getSelectedItem();
				if(month1 > month2) {
					JOptionPane.showMessageDialog(null, "月份区间选择错误", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//删除原有图标
				if(chartPanel != null) panel_1.remove(chartPanel);
				//创建新的报表
				chartPanel = ChartComponent.getChartPanel(material.getKey(), year, month1, month2);
				panel_1.add(chartPanel);
				//刷新
				panel_1.validate();
			}
		});
		lookBtn.setBounds(597, 10, 93, 23);
		panel.add(lookBtn);
		
		JButton saveBtn = new JButton("保存图片");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComboxVo material =  (ComboxVo) materialsComboBox.getSelectedItem();
				String year = (String) yearComboBox.getSelectedItem();
				int month1 = (int) monthComBox1.getSelectedItem();
				int month2 = (int) monthComBox2.getSelectedItem();
				if(month1 > month2) {
					JOptionPane.showMessageDialog(null, "月份区间选择错误", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				jFileChooser.showSaveDialog(null);
				//获取保存目录
				File selectedFile = jFileChooser.getSelectedFile();
				if(selectedFile != null) {
					File file = new File(selectedFile.getAbsoluteFile() + "/" + MD5Util.getMD5() +  ".png");
					if(ChartComponent.saveChartPNG(file, material.getKey(), year, month1, month2)) {
						JOptionPane.showMessageDialog(null, "保存成功", "提示", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "保存失败", "提示", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		saveBtn.setBounds(803, 10, 93, 23);
		panel.add(saveBtn);
		
		JButton lookBigBtn = new JButton("大图查看");
		lookBigBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComboxVo material =  (ComboxVo) materialsComboBox.getSelectedItem();
				String year = (String) yearComboBox.getSelectedItem();
				int month1 = (int) monthComBox1.getSelectedItem();
				int month2 = (int) monthComBox2.getSelectedItem();
				if(month1 > month2) {
					JOptionPane.showMessageDialog(null, "月份区间选择错误", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//创建一个Frame窗口用于显示创建的Chart图表
				ChartComponent.createChartFrame(material.getKey(), year, month1, month2);
			}
		});
		lookBigBtn.setBounds(700, 10, 93, 23);
		panel.add(lookBigBtn);
		
		JButton btnNewButton = new JButton("打印进出仓订单");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//显示打印进出仓订单界面
				new MaterialsInOutPrintPage().setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 20, 143, 43);
		add(btnNewButton);
		
		JButton button_1 = new JButton("打印仓库账本");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//显示打印仓库账本
				new MaterialsBillPrintPage().setVisible(true);
			}
		});
		button_1.setBounds(173, 20, 143, 43);
		add(button_1);
		
		//初始化
		init(materialsComboBox, yearComboBox, monthComBox1, monthComBox2);
		
		JButton flushBtn = new JButton("刷新");
		flushBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//重新加载数据
				init(materialsComboBox, yearComboBox, monthComBox1, monthComBox2);
			}
		});
		flushBtn.setBounds(494, 10, 93, 23);
		panel.add(flushBtn);
	}
	
	/**
	 * 初始化
	 * @param materialsComboBox
	 * @param yearComboBox
	 * @param monthComBox1 
	 * @param monthComBox2
	 */
	public void init(JComboBox materialsComboBox, JComboBox yearComboBox, JComboBox monthComBox1, JComboBox monthComBox2) {
		//清空
		materialsComboBox.removeAllItems();
		yearComboBox.removeAllItems();
		monthComBox1.removeAllItems();
		monthComBox2.removeAllItems();
		
		List<Materials> materialList = materialsService.getAllMaterialList();
		if(materialList != null) {
			for (Materials materials : materialList) {
				materialsComboBox.addItem(new ComboxVo(materials.getId(), materials.getMaterialsName()));
			}
		}
		
		List<String> years = materialsSellService.getAllYear();
		if(years != null) {
			for (String year : years) {
				yearComboBox.addItem(year);
			}
		}
		
		for (int i = 1; i <= 12; i++) {
			monthComBox1.addItem(i);
			monthComBox2.addItem(i);
		}
	}
}
