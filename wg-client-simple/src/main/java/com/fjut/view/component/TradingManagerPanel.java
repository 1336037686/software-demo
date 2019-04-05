package com.fjut.view.component;

import javax.swing.JPanel;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fjut.pojo.User;
import com.fjut.service.MaterialsSellDetailService;
import com.fjut.service.MaterialsSellService;
import com.fjut.util.SpringContextUtils;
import com.fjut.view.page.RemarksPage;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

/**
 * 进出仓管理面板
 * @author LGX
 *
 */
@Lazy
@Scope("prototype")
@Component("TradingManagerPanel")
@SuppressWarnings("all")
public class TradingManagerPanel extends JPanel {
	private JTextField searchInputField;
	
	private TableComponent table1;
	private TableComponent table2;
	
	private MaterialsSellService materialsSellService = SpringContextUtils.getBean(MaterialsSellService.class);
	private MaterialsSellDetailService materialsSellDetailService = SpringContextUtils.getBean(MaterialsSellDetailService.class);
	
	// 表格数据
	private Object[] columnNames1 = { "序号", "单号", "操作人员", "时间", "类型"};
	private Object[][] rowData1;
	private Object[] columnNames2 = { "序号", "物料", "总数"};
	private Object[][] rowData2;
	
	//选中的删除对象
	private Object[] selectTarget;
	
	/**
	 * Create the panel.
	 */
	public TradingManagerPanel() {
		setBounds(0, 0, 1165, 730);
		setLayout(null);
		
		JButton materialInBtn = new JButton("进仓");
		materialInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog materialInPage = (JDialog) SpringContextUtils.getBean("MaterialInPage");
				materialInPage.setVisible(true);
			}
		});
		materialInBtn.setBounds(10, 10, 93, 23);
		add(materialInBtn);
		
		JButton materialOutBtn = new JButton("出仓");
		materialOutBtn.setBounds(113, 10, 93, 23);
		add(materialOutBtn);
		
		JPanel materialShellListPanel = new JPanel();
		materialShellListPanel.setBounds(10, 43, 642, 644);
		materialShellListPanel.setBorder(BorderFactory.createTitledBorder("进出仓列表"));
		add(materialShellListPanel);
		
		JPanel materialShellInfoPanel = new JPanel();
		materialShellInfoPanel.setBounds(662, 43, 493, 644);
		materialShellInfoPanel.setBorder(BorderFactory.createTitledBorder("进出仓详情"));
		add(materialShellInfoPanel);
		materialShellInfoPanel.setLayout(null);
		
		JPanel materialShellDetailPanel = new JPanel();
		materialShellDetailPanel.setBounds(10, 21, 473, 553);
		materialShellInfoPanel.add(materialShellDetailPanel);
		
		JButton updateBtn = new JButton("修改");
		updateBtn.setBounds(10, 584, 93, 23);
		materialShellInfoPanel.add(updateBtn);
		
		JButton deleteBtn = new JButton("删除");
		deleteBtn.setBounds(144, 584, 93, 23);
		materialShellInfoPanel.add(deleteBtn);
		
		JButton dropBtn = new JButton("删除记录");
		dropBtn.setBounds(390, 584, 93, 23);
		materialShellInfoPanel.add(dropBtn);
		dropBtn.addActionListener((e) -> {
			if(selectTarget == null) {
				JOptionPane.showMessageDialog(null, "请选择需要删除的记录", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			//删除
		});
		
		JButton remarksBtn = new JButton("查看备注");
		remarksBtn.setBounds(274, 584, 93, 23);
		materialShellInfoPanel.add(remarksBtn);
		remarksBtn.addActionListener((e) -> {
			if(selectTarget == null) {
				JOptionPane.showMessageDialog(null, "请选择需要查看的记录", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			new RemarksPage(selectTarget[5].toString()).setVisible(true);;
		});
		
		searchInputField = new JTextField();
		searchInputField.setBounds(321, 11, 331, 21);
		add(searchInputField);
		searchInputField.setColumns(10);
		
		JButton searchBtn = new JButton("搜索");
		searchBtn.setBounds(665, 10, 93, 23);
		add(searchBtn);
		
		JButton flushBtn = new JButton("刷新");
		flushBtn.setBounds(218, 10, 93, 23);
		add(flushBtn);
		
		//表格1
		rowData1 = materialsSellService.getAllMaterialSell();
		table1 = new TableComponent(rowData1, columnNames1);
		materialShellListPanel.add(table1.getTableHeader());						// 添加表头
		materialShellListPanel.setLayout(new BorderLayout(5, 5));
		materialShellListPanel.add(new JScrollPane(table1));
		
		//表格2
		table2 = new TableComponent(new Object[0][0], columnNames2);
		materialShellInfoPanel.add(table2.getTableHeader());						// 添加表头
		materialShellInfoPanel.setLayout(new BorderLayout(5, 5));
		materialShellInfoPanel.add(new JScrollPane(table2));
		
		// 设置表格1监听事件
		table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					String str = e.getSource().toString();
					String indexStr = str.substring(str.lastIndexOf("{") + 1, str.lastIndexOf("}"));
					if (str != null && !"".equals(str.trim()) && indexStr != null && !"".equals(indexStr.trim())) {
						int index = Integer.parseInt(indexStr);
						String id = (String) rowData1[index][1];
						selectTarget = rowData1[index];
						rowData2 = materialsSellDetailService.MaterialsSellDetailByMaterialsSellId(id);
						updateUserList(table2, rowData2, columnNames2);
					}
				}
			}
		});
		
		// 设置表格2监听事件
		table2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					String str = e.getSource().toString();
					String indexStr = str.substring(str.lastIndexOf("{") + 1, str.lastIndexOf("}"));
					if (str != null && !"".equals(str.trim()) && indexStr != null && !"".equals(indexStr.trim())) {
						int index = Integer.parseInt(indexStr);
						int id = (int) rowData2[index][3];
						System.out.println(id);
						
					}
				}
			}
		});
		
		// 刷新
		flushBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rowData1 = materialsSellService.getAllMaterialSell();
				updateUserList(table1, rowData1, columnNames1);
				//清空详情
				updateUserList(table2, new Object[0][0], columnNames2);
			}
		});
	}
	
	/**
	 * 更新用户列表
	 * @param type
	 * @param input
	 */
	private void updateUserList(TableComponent table, Object[][] rowData, Object[] columnNames) {
		table.updateModel(rowData, columnNames);
	}
	
	
}
