package com.fjut.view.page;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXDatePicker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fjut.pojo.Session;
import com.fjut.pojo.User;
import com.fjut.service.UserService;
import com.fjut.util.DataUtil;
import com.fjut.util.DateUtil;
import com.fjut.util.MD5Util;
import com.fjut.view.component.DateComponent;

/**
 * 用户添加页面
 * @author LGX
 *
 */
@Lazy
@Component("UserUpdatePage")
@Scope("prototype")
@SuppressWarnings("all")
public class UserUpdatePage extends JDialog {
	
	@Autowired
	private UserService userService;
	
	private JPanel contentPane;
	private JTextField userIdField;
	private JTextField userNameField;
	private JPasswordField passwordField;
    private JXDatePicker datepick;
    private JTextField identityNumField;
    private JTextField birthPlaceField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField idField;
    
    private JRadioButton genderBoyRadio;
    private JRadioButton genderGirlRadio;
    
    

	/**
	 * Create the frame.
	 */
	public UserUpdatePage() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//设置窗口大小
		setBounds(100, 100, 573, 404);
		//设置居中显示
		setLocationRelativeTo(null);
		//设置不可编辑
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        Border titleBorder = BorderFactory.createTitledBorder("用户修改");
        contentPane.setBorder(titleBorder);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("用户修改");
		label.setIcon(new ImageIcon("E:\\DevelopProjects\\Java_Project\\eclipse_project\\software-class\\wg-client-simple\\static\\images\\useradd.png"));
		label.setBounds(210, 10, 142, 32);
		label.setFont(new Font("宋体", Font.PLAIN, 24));
		
		JLabel label_1 = new JLabel("人员代码");
		label_1.setBounds(71, 98, 80, 15);
		
		JLabel label_2 = new JLabel("用 户 名");
		label_2.setBounds(281, 98, 80, 15);
		
		JLabel label_3 = new JLabel("性    别");
		label_3.setBounds(71, 181, 80, 15);
		
		genderBoyRadio = new JRadioButton("男");
		genderBoyRadio.setBounds(132, 177, 49, 23);
		genderBoyRadio.setSelected(true);
		
		genderGirlRadio = new JRadioButton("女");
		genderGirlRadio.setBounds(183, 177, 49, 23);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(genderBoyRadio);
		buttonGroup.add(genderGirlRadio);
		
		userIdField = new JTextField();
		userIdField.setEnabled(false);
		userIdField.setBounds(132, 95, 131, 21);
		userIdField.setColumns(10);
		
		JLabel label_4 = new JLabel("出生日期");
		label_4.setBounds(281, 138, 71, 15);

		contentPane.setLayout(null);
		contentPane.add(label);
		contentPane.add(label_1);
		contentPane.add(label_2);
		contentPane.add(label_3);
		contentPane.add(genderBoyRadio);
		contentPane.add(genderGirlRadio);
		contentPane.add(userIdField);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("身 份 证");
		label_5.setBounds(71, 221, 80, 15);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("籍   贯");
		label_6.setBounds(281, 221, 80, 15);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("家庭住址");
		label_7.setBounds(71, 262, 80, 15);
		contentPane.add(label_7);
		
		JLabel label_9 = new JLabel("联系电话");
		label_9.setBounds(71, 297, 80, 15);
		contentPane.add(label_9);
		
		JLabel label_8 = new JLabel("密    码");
		label_8.setBounds(71, 138, 80, 15);
		contentPane.add(label_8);

		userNameField = new JTextField();
		userNameField.setColumns(10);
		userNameField.setBounds(340, 95, 150, 21);
		contentPane.add(userNameField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(132, 135, 131, 21);
		contentPane.add(passwordField);
		
	    datepick = new DateComponent(340, 135, 150, 21).getJXDatePicker();
		contentPane.add(datepick);
		
		identityNumField = new JTextField();
		identityNumField.setColumns(10);
		identityNumField.setBounds(132, 218, 131, 21);
		contentPane.add(identityNumField);
		
		birthPlaceField = new JTextField();
		birthPlaceField.setColumns(10);
		birthPlaceField.setBounds(340, 218, 150, 21);
		contentPane.add(birthPlaceField);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(132, 259, 358, 21);
		contentPane.add(addressField);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(132, 294, 131, 21);
		contentPane.add(phoneField);
		
		JButton btnSubmit = new JButton("修改");
		//修改用户信息
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "确定修改？") == 0) {
					User user = new User();
					//获取userId
					String id = idField.getText();
					String userId = userIdField.getText();
					String userName = userNameField.getText();
					String password = passwordField.getText();
					Date birthday = datepick.getDate();
					String identityNum = identityNumField.getText();
					String birthPlace = birthPlaceField.getText();
					String address = addressField.getText();
					String phone = phoneField.getText();
					
					//数据检查
					boolean dataCheckStatus = DataUtil.dataCheck(id, userId, userName, password, birthday, identityNum, birthPlace, address, phone);
					if(!dataCheckStatus) {
						JOptionPane.showMessageDialog(null, "填写错误，请重新填写", "提示", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					//封装数据
					user.setId(id);
					user.setUserId(userId);
					user.setUserName(userName);
					user.setPassword(password);
					user.setBirthday(birthday);
					if(genderBoyRadio.isSelected()) {
						user.setUserGender(1);
					}else {
						user.setUserGender(0);
					}
					user.setAddress(address);
					user.setPhone(phone);
					user.setIdentityNum(identityNum);
					user.setBirthPlace(birthPlace);
					user.setAge(DateUtil.getAgeByBirth(birthday));
					User loginUser = (User) Session.getSession().get("user");
					user.setPermission(loginUser.getPermission());
					
					//注册
					boolean result = userService.updateUser(user);
					if (result) {
						JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
						Session.getSession().put("user", userService.getUserByUserId(userId));
						setVisible(false);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "修改失败", "提示", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnSubmit.setBounds(71, 322, 93, 23);
		contentPane.add(btnSubmit);
		
		JButton btnReset = new JButton("取消");
		btnReset.setBounds(397, 322, 93, 23);
		contentPane.add(btnReset);
		
		JLabel label_11 = new JLabel("人员代码");
		label_11.setBounds(71, 61, 80, 15);
		contentPane.add(label_11);
		
		idField = new JTextField();
		idField.setEnabled(false);
		idField.setBounds(132, 58, 358, 21);
		contentPane.add(idField);
		idField.setColumns(10);
		initUpdatePage();
	}
	
	/**
	 * 初始化
	 */
	private void initUpdatePage() {
		User user = (User) Session.getSession().get("user");
		if(user == null) {
			JOptionPane.showMessageDialog(null, "查找出错", "提示", JOptionPane.ERROR_MESSAGE);
			return;
		}
		idField.setText(user.getId());
		userIdField.setText(user.getUserId());
		userNameField.setText(user.getUserName());
		passwordField.setText(user.getPassword());
		datepick.setDate(user.getBirthday());
		birthPlaceField.setText(user.getBirthPlace());
		addressField.setText(user.getAddress());
		phoneField.setText(user.getPhone());
		identityNumField.setText(user.getIdentityNum());
		if(user.getUserGender() == 1) {
			genderBoyRadio.setSelected(true);
		}else {
			genderGirlRadio.setSelected(true);
		}
	}	
}
