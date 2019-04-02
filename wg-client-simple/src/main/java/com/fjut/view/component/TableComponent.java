package com.fjut.view.component;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 * 表格组件
 * @author LGX
 *
 */
public class TableComponent extends JTable{

	public TableComponent() {
		super();
	}

	public TableComponent(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
		setSelectionBackground(Color.CYAN); 							// 设置选中背景
		setSelectionForeground(Color.BLACK); 							// 设置选中字体样式
		setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS); 		// 设置表格自适应
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 			// 设置每次只能选择一行
																		// 表头设置
		getTableHeader().setResizingAllowed(false); 					// 设置不允许手动改变列宽
		getTableHeader().setReorderingAllowed(false); 					// 设置不允许拖动重新排序各列
	}
	
	/**
	 * 更新表格数据
	 * @param rowData 	     数据
	 * @param columnNames 表名
	 */
	public void updateModel(Object[][] rowData, Object[] columnNames) {
		DefaultTableModel dataModel = new DefaultTableModel(rowData, columnNames);
		setModel(dataModel);
		revalidate();
	}
}
