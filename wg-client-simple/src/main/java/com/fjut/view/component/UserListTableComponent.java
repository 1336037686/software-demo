package com.fjut.view.component;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fjut.service.UserService;
import com.fjut.util.SpringContextUtils;

@Lazy
@Scope("prototype")
@Component("UserListTableComponent")
public class UserListTableComponent extends JTable {

	public UserListTableComponent() {
        setSelectionBackground(Color.CYAN); 							//设置选中背景
        setSelectionForeground(Color.BLACK);							//设置选中字体样式
        setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);		//设置表格自适应
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		//设置每次只能选择一行
        //表头设置
        getTableHeader().setResizingAllowed(false);               	// 设置不允许手动改变列宽
        getTableHeader().setReorderingAllowed(false);             	// 设置不允许拖动重新排序各列
	}

	@Override
	public void updateUI() {
		UserService userService = SpringContextUtils.getBean(UserService.class);
		Object[][] rowData = userService.getAllUser();
		Object[] columnNames = {"序号","用户代码", "用户姓名", "人员性别", "联系电话", "权限类别"};
		setModel(new AbstractTableModel() {
            public String getColumnName(int column) { return columnNames[column].toString(); }
            public int getRowCount() { return rowData.length; }
            public int getColumnCount() { return columnNames.length; }
            public Object getValueAt(int row, int col) { return rowData[row][col]; }
            public boolean isCellEditable(int row, int column) { return true; }
            public void setValueAt(Object value, int row, int col) {
                rowData[row][col] = value;
                fireTableCellUpdated(row, col);
            }
        });
		super.updateUI();
	}


	
	
	
	
	
}
