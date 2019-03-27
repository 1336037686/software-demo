package com.fjut.view.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fjut.pojo.User;
import com.fjut.service.UserService;
import com.fjut.util.SpringContextUtils;
import com.fjut.view.page.UserAddPage;

/**
 * 用户管理面板
 * @author LGX
 *
 */
@Lazy
@Scope("prototype")
@Component("UserManagerPanel")
@SuppressWarnings("all")
public class UserManagerPanel extends JPanel {
	
	private JTextField userSelectField;
	private JTextField idFiled;
	private JTextField userIdField;
	private JTextField userNameField;
	private JPasswordField passwordField;
	private JTextField phoneField;
	private JTextField idnumField;
	private JTextField birthPlaceField;
	private JTextField addressField;
	private JTextField textField_7;
	private JXDatePicker datepick;
	private JTextField textField_8;

	/**
	 * Create the panel.
	 */
	public UserManagerPanel() {
        /*
         * 疑问：
         * 1. 为什么不能使用依赖注入
         * 2. 为什么需要手动获取才可以
         */
        UserService userService = SpringContextUtils.getBean(UserService.class);
		
		setBounds(0, 0, 1165, 730);
		setLayout(null);
		JButton userAddBtn = new JButton("添加员工");
		//添加员工
		userAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserAddPage frame = SpringContextUtils.getBean("UserAddPage", UserAddPage.class);
				frame.setVisible(true);
			}
		});
		userAddBtn.setBounds(10, 10, 93, 23);
		add(userAddBtn);
		
		JButton flushBtn = new JButton("刷新");
		flushBtn.setBounds(127, 10, 80, 23);
		add(flushBtn);
		
		userSelectField = new JTextField();
		userSelectField.setBounds(236, 11, 389, 22);
		add(userSelectField);
		userSelectField.setColumns(10);
		
		JButton btnNewButton = new JButton("搜索");
		btnNewButton.setBounds(635, 10, 121, 23);
		add(btnNewButton);
		
		JPanel userListPanel = new JPanel();
		
        Border titleBorder = BorderFactory.createTitledBorder("用户列表");
        userListPanel.setBorder(titleBorder);
		userListPanel.setBounds(10, 54, 746, 661);
		add(userListPanel);
		
        //使用滚动表格添加用户数据
		//设置标题头
        Object[] columnNames = {"序号","用户代码", "用户姓名", "人员性别", "联系电话", "权限类别"};
        
        //设置表格数据
        Object[][] rowData = userService.getAllUser();
        
        // 表格所有行数据
//        Object[][] rowData = {
//                {1, "张三", 80, 80, 80, 240},
//                {2, "John", 70, 80, 90, 240},
//                {3, "Sue", 70, 70, 70, 210},
//                {4, "Jane", 80, 70, 60, 210},
//                {5, "Joe", 80, 70, 60, 210}
//        };

        //创建一个表格，指定 所有行数据 和 表头
        JTable table = new JTable(rowData, columnNames);
        table.setSelectionBackground(Color.CYAN); 							//设置选中背景
        table.setSelectionForeground(Color.BLACK);							//设置选中字体样式
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);		//设置表格自适应
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		//设置每次只能选择一行
        //表头设置
        table.getTableHeader().setResizingAllowed(false);               	// 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             	// 设置不允许拖动重新排序各列
        //添加表头
        userListPanel.add(table.getTableHeader());
        userListPanel.setLayout(new BorderLayout(5, 5));
        //将表格添加到滚动面板中
        JScrollPane scrollPane = new JScrollPane(table);
        userListPanel.add(scrollPane);
        
        //用户信息管理界面
		JPanel userMsgPanel = new JPanel();
        Border titleBorder2 = BorderFactory.createTitledBorder("用户信息管理");
        userMsgPanel.setBorder(titleBorder2);
		userMsgPanel.setBounds(766, 54, 389, 661);
		add(userMsgPanel);
		userMsgPanel.setLayout(null);
		
		JLabel lblid = new JLabel("用户ID");
		lblid.setBounds(37, 31, 54, 15);
		userMsgPanel.add(lblid);
		
		idFiled = new JTextField();
		idFiled.setEnabled(false);
		idFiled.setBounds(101, 28, 263, 21);
		userMsgPanel.add(idFiled);
		idFiled.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("用户代码");
		lblNewLabel_1.setBounds(37, 74, 54, 15);
		userMsgPanel.add(lblNewLabel_1);
		
		userIdField = new JTextField();
		userIdField.setEnabled(false);
		userIdField.setBounds(101, 71, 263, 21);
		userMsgPanel.add(userIdField);
		userIdField.setColumns(10);
		
		JLabel label = new JLabel("用户姓名");
		label.setBounds(37, 158, 54, 15);
		userMsgPanel.add(label);
		
		userNameField = new JTextField();
		userNameField.setBounds(101, 155, 263, 21);
		userMsgPanel.add(userNameField);
		userNameField.setColumns(10);
		
		JLabel label_1 = new JLabel("用户性别");
		label_1.setBounds(37, 199, 54, 15);
		userMsgPanel.add(label_1);
		
		JRadioButton radioButtonBoy = new JRadioButton("男");
		radioButtonBoy.setBounds(101, 195, 40, 23);
		userMsgPanel.add(radioButtonBoy);
		
		JRadioButton radioButtonGirl = new JRadioButton("女");
		radioButtonGirl.setBounds(169, 195, 40, 23);
		userMsgPanel.add(radioButtonGirl);
		
		ButtonGroup genderButtonGroup = new ButtonGroup();
		genderButtonGroup.add(radioButtonBoy);
		genderButtonGroup.add(radioButtonGirl);
		
		JLabel label_2 = new JLabel("联系电话");
		label_2.setBounds(37, 239, 54, 15);
		userMsgPanel.add(label_2);
		
		JLabel label_3 = new JLabel("用户密码");
		label_3.setBounds(37, 117, 54, 15);
		userMsgPanel.add(label_3);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(101, 114, 263, 21);
		userMsgPanel.add(passwordField);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(101, 236, 263, 21);
		userMsgPanel.add(phoneField);
		
		JLabel label_4 = new JLabel("用户权限");
		label_4.setBounds(37, 278, 54, 15);
		userMsgPanel.add(label_4);
		
		JRadioButton radioButtonAdmin = new JRadioButton("管理员");
		radioButtonAdmin.setBounds(97, 274, 70, 23);
		userMsgPanel.add(radioButtonAdmin);
		
		JRadioButton radioButtonOrdinary = new JRadioButton("普通员工");
		radioButtonOrdinary.setBounds(169, 274, 96, 23);
		userMsgPanel.add(radioButtonOrdinary);
		
		ButtonGroup permissionButtonGroup = new ButtonGroup();
		permissionButtonGroup.add(radioButtonAdmin);
		permissionButtonGroup.add(radioButtonOrdinary);
		
		JLabel lblNewLabel = new JLabel("身份证");
		lblNewLabel.setBounds(37, 314, 54, 15);
		userMsgPanel.add(lblNewLabel);
		
		idnumField = new JTextField();
		idnumField.setColumns(10);
		idnumField.setBounds(101, 311, 263, 21);
		userMsgPanel.add(idnumField);
		
		JLabel label_5 = new JLabel("籍贯");
		label_5.setBounds(37, 353, 54, 15);
		userMsgPanel.add(label_5);
		
		birthPlaceField = new JTextField();
		birthPlaceField.setColumns(10);
		birthPlaceField.setBounds(101, 350, 263, 21);
		userMsgPanel.add(birthPlaceField);
		
		JLabel label_6 = new JLabel("地址");
		label_6.setBounds(37, 388, 54, 15);
		userMsgPanel.add(label_6);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(101, 385, 263, 21);
		userMsgPanel.add(addressField);
		
		JLabel label_7 = new JLabel("出生日期");
		label_7.setBounds(37, 424, 54, 15);
		userMsgPanel.add(label_7);
	
	    datepick = new DateComponent(101, 421, 164, 21).getJXDatePicker();
	    userMsgPanel.add(datepick);
	    
	    JButton button = new JButton("修改");
	    button.setBounds(37, 607, 93, 23);
	    userMsgPanel.add(button);
	    
	    JButton button_1 = new JButton("删除");
	    button_1.setBounds(271, 607, 93, 23);
	    userMsgPanel.add(button_1);
        
        //设置表格监听事件
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener(){
			//会响应两次，使用e.getValueIsAdjusting()判断，鼠标点击，getValueIsAdjusting() 返回True. 鼠标释放,getValueIsAdjusting() 返回False
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()) {
					String str = e.getSource().toString();
					//获取当前行序号
					int index = Integer.parseInt(str.substring(str.lastIndexOf("{") + 1, str.lastIndexOf("}")));
					String userId = (String) rowData[index][1];
					//获取选中的用户信息
					User selectedUser = userService.getUserByUserId(userId);
					if(selectedUser == null) {
						JOptionPane.showMessageDialog(null, "查找出错", "提示", JOptionPane.ERROR_MESSAGE);
						return;
					}
					idFiled.setText(selectedUser.getId());
					userIdField.setText(selectedUser.getUserId());
					passwordField.setText(selectedUser.getPassword());
					userNameField.setText(selectedUser.getUserName());
					phoneField.setText(selectedUser.getPhone());
					idnumField.setText(selectedUser.getIdentityNum());
					birthPlaceField.setText(selectedUser.getBirthPlace());
					addressField.setText(selectedUser.getAddress());
					System.out.println(selectedUser.getBirthday());
					//selectedUser.getBirthday() 设置生日，还未完成
					datepick.setDate(selectedUser.getBirthday());
					if(selectedUser.getUserGender() == 1) {
						radioButtonBoy.setSelected(true);
					}else {
						radioButtonGirl.setSelected(true);
					}
					if(selectedUser.getPermission() == 1){
						radioButtonAdmin.setSelected(true);
					}else {
						radioButtonOrdinary.setSelected(true);
					}
				}
			}
        });
        
        //刷新
		flushBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				Object[][] rowData = userService.getAllUser();
				table.setModel(new AbstractTableModel() {
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
				table.repaint();
			}
		});
        
	}
}
