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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.eltima.components.ui.DatePicker;
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
@Component("UserAddPage")
@Scope("prototype")
@SuppressWarnings("all")
public class UserAddPage extends JDialog {
	
	@Autowired
	private UserService userService;

	private JPanel contentPane;
	private JTextField userIdField;
	private JTextField userNameField;
	private JPasswordField passwordField;
    private DatePicker datepick;
    private JTextField identityNumField;
    private JTextField birthPlaceField;
    private JTextField addressField;
    private JTextField phoneField;

	/**
	 * Create the dialog.
	 */
	public UserAddPage() {
		setTitle("用户添加");
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
        Border titleBorder = BorderFactory.createTitledBorder("用户添加");
        contentPane.setBorder(titleBorder);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("用户添加");
		label.setIcon(new ImageIcon("E:\\DevelopProjects\\Java_Project\\eclipse_project\\software-class\\wg-client-simple\\static\\images\\useradd.png"));
		label.setBounds(210, 10, 142, 32);
		label.setFont(new Font("宋体", Font.PLAIN, 24));
		
		JLabel label_1 = new JLabel("人员代码");
		label_1.setBounds(71, 64, 80, 15);
		
		JLabel label_2 = new JLabel("用 户 名");
		label_2.setBounds(281, 64, 80, 15);
		
		JLabel label_3 = new JLabel("性    别");
		label_3.setBounds(71, 147, 80, 15);
		
		JRadioButton genderBoyRadio = new JRadioButton("男");
		genderBoyRadio.setBounds(132, 143, 49, 23);
		genderBoyRadio.setSelected(true);
		
		JRadioButton genderGirlRadio = new JRadioButton("女");
		genderGirlRadio.setBounds(183, 143, 49, 23);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(genderBoyRadio);
		buttonGroup.add(genderGirlRadio);
		
		userIdField = new JTextField();
		userIdField.setBounds(132, 61, 131, 21);
		userIdField.setColumns(10);
		
		JLabel label_4 = new JLabel("出生日期");
		label_4.setBounds(281, 104, 71, 15);

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
		label_5.setBounds(71, 187, 80, 15);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("籍   贯");
		label_6.setBounds(281, 187, 80, 15);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("家庭住址");
		label_7.setBounds(71, 228, 80, 15);
		contentPane.add(label_7);
		
		JLabel label_9 = new JLabel("联系电话");
		label_9.setBounds(71, 263, 80, 15);
		contentPane.add(label_9);
		
		JLabel label_8 = new JLabel("密    码");
		label_8.setBounds(71, 104, 80, 15);
		contentPane.add(label_8);
		
		JLabel label_10 = new JLabel("权   限");
		label_10.setBounds(281, 147, 80, 15);
		contentPane.add(label_10);
		
		JRadioButton radioButtonAdmin = new JRadioButton("管理员");
		radioButtonAdmin.setBounds(340, 143, 71, 23);
		contentPane.add(radioButtonAdmin);
		
		JRadioButton radioButtonOrdinary = new JRadioButton("普通员工");
		radioButtonOrdinary.setSelected(true);
		radioButtonOrdinary.setBounds(413, 143, 89, 23);
		contentPane.add(radioButtonOrdinary);
		
		ButtonGroup buttonGroup2 = new ButtonGroup();
		buttonGroup2.add(radioButtonAdmin);
		buttonGroup2.add(radioButtonOrdinary);
		
		userNameField = new JTextField();
		userNameField.setColumns(10);
		userNameField.setBounds(340, 61, 150, 21);
		contentPane.add(userNameField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(132, 101, 131, 21);
		contentPane.add(passwordField);
		
	    datepick = new DateComponent(340, 101, 150, 21).getDatePicker();
		contentPane.add(datepick);
		
		identityNumField = new JTextField();
		identityNumField.setColumns(10);
		identityNumField.setBounds(132, 184, 131, 21);
		contentPane.add(identityNumField);
		
		birthPlaceField = new JTextField();
		birthPlaceField.setColumns(10);
		birthPlaceField.setBounds(340, 184, 150, 21);
		contentPane.add(birthPlaceField);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(132, 225, 358, 21);
		contentPane.add(addressField);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(132, 260, 131, 21);
		contentPane.add(phoneField);
		
		JButton btnSubmit = new JButton("提交");
		//添加用户
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				//获取userId
				String userId = userIdField.getText();
				String userName = userNameField.getText();
				String password = passwordField.getText();
				Date birthday = (Date) datepick.getValue();
				boolean genderBoyRadioSelected = genderBoyRadio.isSelected();
				boolean radioButtonAdminSelected = radioButtonAdmin.isSelected();
				String identityNum = identityNumField.getText();
				String birthPlace = birthPlaceField.getText();
				String address = addressField.getText();
				String phone = phoneField.getText();
				
				//数据检查
				boolean dataCheckStatus = dataCheck(userId, userName, password, birthday, identityNum, birthPlace, address, phone);
				if(!dataCheckStatus) {
					JOptionPane.showMessageDialog(null, "填写错误，请重新填写", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//封装数据
				user.setId(MD5Util.getMD5());
				user.setUserId(userId);
				user.setUserName(userName);
				user.setPassword(password);
				user.setBirthday(birthday);
				if(genderBoyRadioSelected) {
					user.setUserGender(1);
				}else {
					user.setUserGender(0);
				}
				if(radioButtonAdminSelected) {
					user.setPermission(1);
				}else {
					user.setPermission(0);
				}
				user.setAddress(address);
				user.setPhone(phone);
				user.setIdentityNum(identityNum);
				user.setBirthPlace(birthPlace);
				user.setAge(DateUtil.getAgeByBirth(birthday));
				user.setRegisterDay(new Date());
				
				//注册
				boolean register = userService.register(user);
				if(register) {
					setVisible(false);
					dispose();
				}
			}
		});
		btnSubmit.setBounds(71, 311, 93, 23);
		contentPane.add(btnSubmit);
		
		JButton btnReset = new JButton("取消");
		btnReset.setBounds(397, 311, 93, 23);
		contentPane.add(btnReset);
	}
	
	/**
	 * 数据检查
	 * @return
	 */
	private boolean dataCheck(String userId, String userName, String password, Date birthday, 
			String identityNum, String birthPlace, String address, String phone) {
		
		return !DataUtil.isNull(userId) && !DataUtil.isNull(userName)  && !DataUtil.isNull(password) && DataUtil.dateIsOk(birthday)
				&& !DataUtil.isNull(identityNum)  && !DataUtil.isNull(birthPlace)  && !DataUtil.isNull(address)  && !DataUtil.isNull(phone); 
	}
}
