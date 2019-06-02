package com.fjut.view.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
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
import javax.swing.table.TableModel;

import org.jdesktop.swingx.JXDatePicker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fjut.pojo.User;
import com.fjut.service.UserService;
import com.fjut.util.DataUtil;
import com.fjut.util.DateUtil;
import com.fjut.util.SpringContextUtils;
import com.fjut.view.page.UserAddPage;
import javax.swing.JCheckBox;

/**
 * 用户管理面板
 * 
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
	private JXDatePicker datepick;
	private TableComponent table;

	private JRadioButton radioButtonBoy;
	private JRadioButton radioButtonGirl;

	private JRadioButton radioButtonAdmin;
	private JRadioButton radioButtonOrdinary;
	
	private JCheckBox authorityMaterialCheckBox;
	private JCheckBox authorityInOutCheckBox ;
	private JCheckBox authorityChartCheckBox;
	private JCheckBox authorityLogCheckBox;


	// 表格数据
	private Object[] columnNames = { "序号", "用户代码", "用户姓名", "人员性别", "联系电话", "权限类别" };
	private Object[][] rowData;

	/*
	 * 疑问： 1. 为什么不能使用依赖注入 2. 为什么需要手动获取才可以
	 */
	private UserService userService = SpringContextUtils.getBean(UserService.class);
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public UserManagerPanel() {

		setBounds(0, 0, 1165, 730);
		setLayout(null);
		JButton userAddBtn = new JButton("添加员工");
		// 添加员工
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

		JButton searchBtn = new JButton("搜索");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取搜索框信息
				String input = userSelectField.getText();
				//数据校验
				if (input == null || "".equals(input.trim())) {
					JOptionPane.showMessageDialog(null, "输入不能为空", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//更新用户列表
				updateUserList(1, input);
			}
		});
		searchBtn.setBounds(635, 10, 121, 23);
		add(searchBtn);

		JPanel userListPanel = new JPanel();
		userListPanel.setBorder(BorderFactory.createTitledBorder("用户列表"));
		userListPanel.setBounds(10, 54, 746, 661);
		add(userListPanel);

		// 设置表格数据
		 rowData = userService.getAllUser();

		// 表格所有行数据
//		Object[][] rowData = { 
//				{ 1, "张三", 80, 80, 80, 240 }, 
//				{ 2, "John", 70, 80, 90, 240 },
//				{ 3, "Sue", 70, 70, 70, 210 }, 
//				{ 4, "Jane", 80, 70, 60, 210 }, 
//				{ 5, "Joe", 80, 70, 60, 210 } 
//			};

		// 创建一个表格，指定 所有行数据 和 表头
		table = new TableComponent(rowData, columnNames);
		userListPanel.add(table.getTableHeader());						// 添加表头
		userListPanel.setLayout(new BorderLayout(5, 5));
		JScrollPane scrollPane = new JScrollPane(table); 				// 将表格添加到滚动面板中
		userListPanel.add(scrollPane);

		// 用户信息管理界面
		JPanel userMsgPanel = new JPanel();
		userMsgPanel.setBorder(BorderFactory.createTitledBorder("用户信息管理"));
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

		radioButtonBoy = new JRadioButton("男");
		radioButtonBoy.setBounds(101, 195, 40, 23);
		userMsgPanel.add(radioButtonBoy);

		radioButtonGirl = new JRadioButton("女");
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

		radioButtonAdmin = new JRadioButton("管理员");
		radioButtonAdmin.setBounds(97, 274, 70, 23);
		userMsgPanel.add(radioButtonAdmin);

		radioButtonOrdinary = new JRadioButton("普通员工");
		radioButtonOrdinary.setBounds(169, 274, 96, 23);
		userMsgPanel.add(radioButtonOrdinary);

		ButtonGroup permissionButtonGroup = new ButtonGroup();
		permissionButtonGroup.add(radioButtonAdmin);
		permissionButtonGroup.add(radioButtonOrdinary);
		
		JLabel label_8 = new JLabel("权限管理");
		label_8.setBounds(37, 314, 54, 15);
		userMsgPanel.add(label_8);
		
		authorityMaterialCheckBox = new JCheckBox("物料管理");
		authorityMaterialCheckBox.setBounds(101, 310, 103, 23);
		userMsgPanel.add(authorityMaterialCheckBox);
		
		authorityInOutCheckBox = new JCheckBox("进出仓管理");
		authorityInOutCheckBox.setBounds(215, 310, 103, 23);
		userMsgPanel.add(authorityInOutCheckBox);
		
		authorityChartCheckBox = new JCheckBox("报表管理");
		authorityChartCheckBox.setBounds(101, 347, 103, 23);
		userMsgPanel.add(authorityChartCheckBox);
		
		authorityLogCheckBox = new JCheckBox("日志管理");
		authorityLogCheckBox.setBounds(215, 347, 103, 23);
		userMsgPanel.add(authorityLogCheckBox);

		JLabel lblNewLabel = new JLabel("身份证");
		lblNewLabel.setBounds(37, 392, 54, 15);
		userMsgPanel.add(lblNewLabel);

		idnumField = new JTextField();
		idnumField.setColumns(10);
		idnumField.setBounds(101, 389, 263, 21);
		userMsgPanel.add(idnumField);

		JLabel label_5 = new JLabel("籍贯");
		label_5.setBounds(37, 431, 54, 15);
		userMsgPanel.add(label_5);

		birthPlaceField = new JTextField();
		birthPlaceField.setColumns(10);
		birthPlaceField.setBounds(101, 428, 263, 21);
		userMsgPanel.add(birthPlaceField);

		JLabel label_6 = new JLabel("地址");
		label_6.setBounds(37, 466, 54, 15);
		userMsgPanel.add(label_6);

		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(101, 463, 263, 21);
		userMsgPanel.add(addressField);

		JLabel label_7 = new JLabel("出生日期");
		label_7.setBounds(37, 502, 54, 15);
		userMsgPanel.add(label_7);

		datepick = new DateComponent(101, 500, 164, 21).getJXDatePicker();
		userMsgPanel.add(datepick);

		JButton button = new JButton("修改");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "确定修改？") == 0) {
					//获取数据
					String id = idFiled.getText();
					String userId = userIdField.getText();
					String userName = userNameField.getText();
					String password = passwordField.getText();
					Date birthday = datepick.getDate();
					String identityNum = idnumField.getText();
					String birthPlace = birthPlaceField.getText();
					String address = addressField.getText();
					String phone = phoneField.getText();
					String authority = "";
					
					//权限
					int gender = 0;
					if (radioButtonBoy.isSelected()) {
						gender = 1;
					}
					int permission = 0;
					if (radioButtonAdmin.isSelected()) {
						permission = 1;
					}
					
					//拼接具体权限
					if(authorityMaterialCheckBox.isSelected()) {
						authority += "1-";
					}
					
					if(authorityInOutCheckBox.isSelected()) {
						authority += "2-";
					}
					
					if(authorityChartCheckBox.isSelected()) {
						authority += "3-";
					}
					
					if(authorityLogCheckBox.isSelected()) {
						authority += "4-";
					}
					
					if(authority.endsWith("-")) {
						authority = authority.trim().substring(0, authority.lastIndexOf("-"));
					}
					
					//id为空
					if(id == null || "".equals(id.trim())) {
						JOptionPane.showMessageDialog(null, "请选择一条要修改的信息", "提示", JOptionPane.ERROR_MESSAGE);
						return;
					}
					//密码小于6位或者密码超过12位
					if((password.trim().length() < 6 || password.trim().length() > 12) && password.trim().length() < 32) {
						JOptionPane.showMessageDialog(null, "密码长度应在6-12位之间", "提示", JOptionPane.ERROR_MESSAGE);
						return;
					}
					//数据检查
					boolean check = DataUtil.dataCheck(id, userId, userName, password, birthday, identityNum,birthPlace, address, phone);
					if (!check) {
						JOptionPane.showMessageDialog(null, "填写错误，请重新填写", "提示", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					//封装数据，更新数据
					User user = new User();
					user.setId(id);
					user.setUserId(userId);
					user.setUserName(userName);
					user.setPassword(password);
					user.setAddress(address);
					user.setBirthday(birthday);
					user.setBirthPlace(birthPlace);
					user.setUserGender(gender);
					user.setIdentityNum(identityNum);
					user.setPermission(permission);
					user.setPhone(phone);
					user.setAge(DateUtil.getAgeByBirth(birthday));
					user.setAuthority(authority);
					boolean result = userService.updateUser(user);
					if (result) {
						JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
						clearField();
					} else {
						JOptionPane.showMessageDialog(null, "修改失败", "提示", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		button.setBounds(37, 607, 93, 23);
		userMsgPanel.add(button);

		JButton button_1 = new JButton("删除");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "确定删除该用户？") == 0) {
					String id = idFiled.getText();
					String userId = userIdField.getText();
					if(id == null || "".equals(id.trim()) || userId == null || "".equals(userId.trim())) {
						JOptionPane.showMessageDialog(null, "请选择一个删除对象", "提示", JOptionPane.ERROR_MESSAGE);
						return;
					}
					boolean result = userService.deleteUser(id, userId);
					if(result) {
						JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
						clearField();
					}else {
						JOptionPane.showMessageDialog(null, "删除失败", "提示", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		button_1.setBounds(271, 607, 93, 23);
		userMsgPanel.add(button_1);

		// 设置表格监听事件
		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {
			// 会响应两次，使用e.getValueIsAdjusting()判断，鼠标点击，getValueIsAdjusting() 返回True.
			// 鼠标释放,getValueIsAdjusting() 返回 False
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					String str = e.getSource().toString();
					// 取出当前行
					String indexStr = str.substring(str.lastIndexOf("{") + 1, str.lastIndexOf("}"));
					if (str != null && !"".equals(str.trim()) && indexStr != null && !"".equals(indexStr.trim())) {
						// 获取当前行序号
						int index = Integer.parseInt(indexStr);
						String userId = (String) rowData[index][1];
						// 获取选中的用户信息
						User selectedUser = userService.getUserByUserId(userId);
						if (selectedUser == null) {
							JOptionPane.showMessageDialog(null, "查找出错", "提示", JOptionPane.ERROR_MESSAGE);
							return;
						}
						// 封装数据
						idFiled.setText(selectedUser.getId());
						userIdField.setText(selectedUser.getUserId());
						passwordField.setText(selectedUser.getPassword());
						userNameField.setText(selectedUser.getUserName());
						phoneField.setText(selectedUser.getPhone());
						idnumField.setText(selectedUser.getIdentityNum());
						birthPlaceField.setText(selectedUser.getBirthPlace());
						addressField.setText(selectedUser.getAddress());
						datepick.setDate(selectedUser.getBirthday());
						//封装身份
						if (selectedUser.getUserGender() == 1) {
							radioButtonBoy.setSelected(true);
						} else {
							radioButtonGirl.setSelected(true);
						}
						if (selectedUser.getPermission() == 1) {
							radioButtonAdmin.setSelected(true);
						} else {
							radioButtonOrdinary.setSelected(true);
						}
						//封装具体权限
						String[] authoritys = selectedUser.getAuthority().trim().split("-");
						for (String authority : authoritys) {
							if("1".equals(authority)) {
								authorityMaterialCheckBox.setSelected(true);
							}
							if("2".equals(authority)) {
								authorityInOutCheckBox.setSelected(true);
							}
							if("3".equals(authority)) {
								authorityChartCheckBox.setSelected(true);
							}
							if("4".equals(authority)) {
								authorityLogCheckBox.setSelected(true);
							}
						}
					}
				}
			}
		});
		
		//添加权限按钮事件监听
		//点击管理员自动添加所有权限，并设置不可选择
		radioButtonAdmin.addItemListener((e) -> {
			authorityMaterialCheckBox.setSelected(true);
			authorityInOutCheckBox.setSelected(true);
			authorityChartCheckBox.setSelected(true);
			authorityLogCheckBox.setSelected(true);
			authorityMaterialCheckBox.setEnabled(false);
			authorityInOutCheckBox.setEnabled(false);
			authorityChartCheckBox.setEnabled(false);
			authorityLogCheckBox.setEnabled(false);
		});
		//点击普通用户自动取消所有权限，并设置可选择
		radioButtonOrdinary.addItemListener((e) -> {
			authorityMaterialCheckBox.setSelected(false);
			authorityInOutCheckBox.setSelected(false);
			authorityChartCheckBox.setSelected(false);
			authorityLogCheckBox.setSelected(false);
			authorityMaterialCheckBox.setEnabled(true);
			authorityInOutCheckBox.setEnabled(true);
			authorityChartCheckBox.setEnabled(true);
			authorityLogCheckBox.setEnabled(true);
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
		if (type == 0) rowData = userService.getAllUser();
		if (type == 1) rowData = userService.getSearchUser(input);
		table.updateModel(rowData, columnNames);
		clearField();
	}
	
	/**
	 * 清空输入框数据
	 */
	private void clearField() {
		idFiled.setText("");
		userIdField.setText("");
		passwordField.setText("");
		userNameField.setText("");
		phoneField.setText("");
		idnumField.setText("");
		birthPlaceField.setText("");
		addressField.setText("");
		datepick.setDate(new Date());
		radioButtonOrdinary.setSelected(true);
		radioButtonBoy.setSelected(true);
		authorityMaterialCheckBox.setSelected(false);
		authorityInOutCheckBox.setSelected(false);
		authorityChartCheckBox.setSelected(false);
		authorityLogCheckBox.setSelected(false);
	}
}
