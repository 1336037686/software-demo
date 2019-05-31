package com.fjut.view.component;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fjut.service.MaterialsSellDetailService;
import com.fjut.service.MaterialsSellService;
import com.fjut.util.DataUtil;
import com.fjut.util.SpringContextUtils;
import com.fjut.view.page.RemarksPage;

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
	private Object[] selectChildTarget;
	
	/**
	 * Create the panel.
	 */
	public TradingManagerPanel() {
		setBounds(0, 0, 1165, 730);
		setLayout(null);
		
		JButton materialInBtn = new JButton("进仓");
		materialInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//实例化MaterialInPage面板
				JDialog materialInPage = (JDialog) SpringContextUtils.getBean("MaterialInPage");
				//设置该面板显示
				materialInPage.setVisible(true);
			}
		});
		materialInBtn.setBounds(10, 10, 93, 23);
		add(materialInBtn);
		
		JButton materialOutBtn = new JButton("出仓");
		materialOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//点击出仓按钮事，实例化MaterialOutPage面板
				JDialog materialOutPage = (JDialog) SpringContextUtils.getBean("MaterialOutPage");
				//设置该面板显示
				materialOutPage.setVisible(true);
			}
		});
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
		materialShellInfoPanel.setLayout(null);
		
		JPanel materialShellDetailPanel = new JPanel();
		materialShellDetailPanel.setBounds(10, 21, 473, 553);
		materialShellInfoPanel.add(materialShellDetailPanel);
		
//		JButton deleteBtn = new JButton("删除");
//		deleteBtn.setBounds(40, 584, 93, 23);
//		materialShellInfoPanel.add(deleteBtn);
//		deleteBtn.addActionListener((e) -> {
//			if(selectChildTarget == null) {
//				JOptionPane.showMessageDialog(null, "请选择需要查看的记录", "提示", JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//			//删除
//			if(JOptionPane.showConfirmDialog(null, "确定删除【" + selectChildTarget[1] + "】？") == 0) {
//				try {
//					if(materialsSellDetailService.deleteMaterialsSellDetailById((int)selectChildTarget[3])) {
//						JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
//						updateTable();
//					}
//				} catch (Exception e1) {
//					JOptionPane.showMessageDialog(null, "删除失败", "提示", JOptionPane.ERROR_MESSAGE);
//				}
//			}
//		});
		
//		JButton dropBtn = new JButton("删除记录");
//		dropBtn.setBounds(376, 584, 93, 23);
//		materialShellInfoPanel.add(dropBtn);
//		dropBtn.addActionListener((e) -> {
//			if(selectTarget == null) {
//				JOptionPane.showMessageDialog(null, "请选择需要删除的记录", "提示", JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//			//删除
//			if(JOptionPane.showConfirmDialog(null, "确定删除当前【" + selectTarget[1] + "】订单？") == 0) {
//				try {
//					if(materialsSellService.deleteMaterialSell(selectTarget[1].toString())) {
//						JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
//						updateTable();
//					}
//				} catch (Exception e1) {
//					JOptionPane.showMessageDialog(null, "删除失败", "提示", JOptionPane.ERROR_MESSAGE);
//				}
//			}
//		});
		
		JButton remarksBtn = new JButton("查看备注");
		remarksBtn.setBounds(214, 584, 93, 23);
		materialShellInfoPanel.add(remarksBtn);
		remarksBtn.addActionListener((e) -> {
			//如果没有选择一条记录
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
		searchBtn.addActionListener((e) -> {
			String input = searchInputField.getText();
			if(DataUtil.isNull(input)) {
				JOptionPane.showMessageDialog(null, "输入不能为空", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			rowData1 = materialsSellService.searchMaterialSell(input);
			updateList(table1, rowData1, columnNames1);
			updateList(table2, new Object[0][0], columnNames2);
		});
		
		JButton flushBtn = new JButton("刷新");
		flushBtn.setBounds(218, 10, 93, 23);
		add(flushBtn);
		
		//表格1
		rowData1 = materialsSellService.getAllMaterialSell();
//		rowData1 = new Object[0][0];
		table1 = new TableComponent(rowData1, columnNames1);
		materialShellListPanel.add(table1.getTableHeader());						// 添加表头
		materialShellListPanel.setLayout(new BorderLayout(5, 5));
		materialShellListPanel.add(new JScrollPane(table1));
		
		//表格2
		table2 = new TableComponent(new Object[0][0], columnNames2);
		materialShellInfoPanel.add(table2.getTableHeader());
		JScrollPane scrollPane = new JScrollPane(table2);
		scrollPane.setBounds(6, 17, 481, 620);
		materialShellInfoPanel.add(scrollPane);
		
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
						updateList(table2, rowData2, columnNames2);
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
						selectChildTarget = rowData2[index];
						int id = (int) rowData2[index][3];
						System.out.println(id);
						
					}
				}
			}
		});
		
		// 刷新
		flushBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});
	}
	
	/**
	 * 更新用户列表
	 * @param type
	 * @param input
	 */
	private void updateList(TableComponent table, Object[][] rowData, Object[] columnNames) {
		table.updateModel(rowData, columnNames);
	}
	
	public void updateTable() {
		rowData1 = materialsSellService.getAllMaterialSell();
		updateList(table1, rowData1, columnNames1);
		
		//清空详情
		updateList(table2, new Object[0][0], columnNames2);
		
		//清空选中
		selectChildTarget = null;
		selectTarget = null;
	}

}
