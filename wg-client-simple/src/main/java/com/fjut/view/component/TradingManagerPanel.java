package com.fjut.view.component;

import javax.swing.JPanel;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fjut.util.SpringContextUtils;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
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
	
	// 表格数据
	private Object[] columnNames1 = { "序号", "单号", "操作人员", "时间", "类型"};
	private Object[][] rowData1;
	private Object[] columnNames2 = { "序号", "物料", "总数"};
	private Object[][] rowData2;
	
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
		deleteBtn.setBounds(212, 584, 93, 23);
		materialShellInfoPanel.add(deleteBtn);
		
		JButton dropBtn = new JButton("删除记录");
		dropBtn.setBounds(390, 584, 93, 23);
		materialShellInfoPanel.add(dropBtn);
		
		searchInputField = new JTextField();
		searchInputField.setBounds(247, 11, 302, 21);
		add(searchInputField);
		searchInputField.setColumns(10);
		
		JButton searchBtn = new JButton("搜索");
		searchBtn.setBounds(559, 10, 93, 23);
		add(searchBtn);
		
		//表格1
		table1 = new TableComponent(rowData1, columnNames1);
		
		//表格2
		table2 = new TableComponent(rowData2, columnNames2);
		
		
		
	}
}
