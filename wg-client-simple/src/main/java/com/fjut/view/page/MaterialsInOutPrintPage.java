package com.fjut.view.page;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fjut.service.MaterialsSellService;
import com.fjut.util.POIUtil;
import com.fjut.util.SpringContextUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.awt.event.ActionEvent;

/**
 * 进出仓报表打印页面
 * @author LGX
 *
 */
@SuppressWarnings("all")
public class MaterialsInOutPrintPage extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MaterialsInOutPrintPage dialog = new MaterialsInOutPrintPage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MaterialsInOutPrintPage() {
		MaterialsSellService materialsSellService = SpringContextUtils.getBean(MaterialsSellService.class);
		
		List<String> years = materialsSellService.getAllYear();
		
		setBounds(100, 100, 337, 247);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("打印进出仓报表");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(86, 10, 151, 29);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("日期选择：");
		label_1.setBounds(36, 69, 71, 15);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("年份");
		label_2.setBounds(117, 68, 54, 15);
		contentPanel.add(label_2);
		
		JComboBox yearComboBox = new JComboBox();
		yearComboBox.setBounds(154, 66, 128, 21);
		contentPanel.add(yearComboBox);
		//初始化年份选择
		if(years != null) {
			for (String year : years) {
				yearComboBox.addItem(year);
			}
		}
		
		JLabel label_3 = new JLabel("月份");
		label_3.setBounds(117, 107, 33, 15);
		contentPanel.add(label_3);
		
		JComboBox monthComboBox = new JComboBox();
		monthComboBox.setBounds(154, 104, 128, 21);
		contentPanel.add(monthComboBox);
		//初始化月份选择
		if(years != null && years.size() > 0) {			
			List<String> months = materialsSellService.getAllMonth(years.get(0));
			if(months != null) {
				for (String month : months) {
					monthComboBox.addItem(month);
				}
			}
		}
		
		//监听年份变化，更改月份
		yearComboBox.addItemListener((e) -> {
			String year = (String) e.getItem();
			monthComboBox.removeAllItems();
			List<String> months = materialsSellService.getAllMonth(year);
			if(months != null) {
				for (String month : months) {
					monthComboBox.addItem(month);
				}
			}
		});
		
		JButton button = new JButton("打印报表");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(yearComboBox.getSelectedItem() == null || monthComboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "请选择正确的日期", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				jFileChooser.showSaveDialog(null);
				//获取保存目录
				File selectedFile = jFileChooser.getSelectedFile();
				if(selectedFile != null) {
					File file = new File(selectedFile.getAbsoluteFile() + "/" + yearComboBox.getSelectedItem() + "-" + monthComboBox.getSelectedItem() + "-进出仓报表.xlsx");
					Object[][] data = materialsSellService.getInOutDataByDate(yearComboBox.getSelectedItem().toString(), monthComboBox.getSelectedItem().toString());
					String[] columnNames = {"订单号", "物料名", "操作总数", "进出仓类型", "操作日期", "操作人员"};
					if(POIUtil.createXlsx(file, columnNames , data)) {
						JOptionPane.showMessageDialog(null, "导出成功", "提示", JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "导出失败", "提示", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		button.setBounds(114, 159, 93, 23);
		contentPanel.add(button);

	}

}
