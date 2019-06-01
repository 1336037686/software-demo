package com.fjut.view.page;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fjut.pojo.User;
import com.fjut.service.UserService;
import com.fjut.util.DataUtil;
import com.fjut.util.SpringContextUtils;

/**
 * 用户登录页面
 * @author LGX
 *
 */
@Component("LoginPage")
@Scope("prototype") //创建多例
@SuppressWarnings("all")
public class LoginPage extends JFrame {
	
	@Autowired
	private UserService userService;

	private JPanel contentPane;
	private JTextField usernameFiled;
	private JPasswordField passwordField;

	public LoginPage() {
		setTitle("登陆界面");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel totalLbl = new JLabel("用户登录");
		totalLbl.setIcon(new ImageIcon("E:\\DevelopProjects\\Java_Project\\eclipse_project\\software-class\\wg-client-simple\\static\\images\\mysql.png"));
		totalLbl.setFont(new Font("黑体", Font.BOLD, 20));
		totalLbl.setBounds(151, 10, 121, 28);
		contentPane.add(totalLbl);
		
		JLabel usernameLbl = new JLabel("用户名");
		usernameLbl.setIcon(new ImageIcon("E:\\DevelopProjects\\Java_Project\\eclipse_project\\software-class\\wg-client-simple\\static\\images\\username.png"));
		usernameLbl.setBounds(81, 71, 60, 15);
		contentPane.add(usernameLbl);
		
		JLabel passwordLbl = new JLabel("密 码");
		passwordLbl.setIcon(new ImageIcon("E:\\DevelopProjects\\Java_Project\\eclipse_project\\software-class\\wg-client-simple\\static\\images\\password.png"));
		passwordLbl.setBounds(81, 118, 54, 15);
		contentPane.add(passwordLbl);
		
		usernameFiled = new JTextField();
		usernameFiled.setBounds(151, 68, 210, 21);
		contentPane.add(usernameFiled);
		usernameFiled.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(151, 115, 210, 21);
		contentPane.add(passwordField);
		
		JRadioButton radioButtonAdmin = new JRadioButton("管理员");
		radioButtonAdmin.setBounds(151, 162, 96, 23);
		JRadioButton radioButtonOrdinary = new JRadioButton("普通员工");
		radioButtonOrdinary.setSelected(true);
		radioButtonOrdinary.setBounds(249, 162, 112, 23);
		
		//添加组
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(radioButtonAdmin);
		radioGroup.add(radioButtonOrdinary);
		
		contentPane.add(radioButtonAdmin);
		contentPane.add(radioButtonOrdinary);
		
		JLabel lblJurisdiction = new JLabel("权 限");
		lblJurisdiction.setIcon(new ImageIcon("E:\\DevelopProjects\\Java_Project\\eclipse_project\\software-class\\wg-client-simple\\static\\images\\quanxian.png"));
		lblJurisdiction.setBounds(81, 166, 54, 15);
		contentPane.add(lblJurisdiction);
		
		JButton loginBtn = new JButton("登陆");
		//登陆操作
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取用户名和密码
				String uname = usernameFiled.getText();
				String pwd = passwordField.getText();
				//数据校验
				boolean dataCheck = dataCheck(uname, pwd);
				if(!dataCheck) {
					JOptionPane.showMessageDialog(null, "用户名密码输入错误", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//封装数据
				User user = new User();
				user.setUserId(uname);
				user.setPassword(pwd);
				if(radioButtonAdmin.isSelected()) {
					user.setPermission(1);
				}else {
					user.setPermission(0);
				}
				
				//用户登录
				if(userService.login(user)) {
	                setVisible(false);// 登陆成功，本窗口隐藏
	                //打开home窗口
	                ((HomePage)SpringContextUtils.getBean("HomePage")).setVisible(true);
	                //销毁窗口
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "登录失败", "message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		loginBtn.setIcon(new ImageIcon("E:\\DevelopProjects\\Java_Project\\eclipse_project\\software-class\\wg-client-simple\\static\\images\\login.png"));
		loginBtn.setBounds(81, 214, 93, 23);
		contentPane.add(loginBtn);
		
		JButton resetBtn = new JButton("重置");
		//重置操作
		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				usernameFiled.setText("");
				passwordField.setText("");
			}
		});
		resetBtn.setIcon(new ImageIcon("E:\\DevelopProjects\\Java_Project\\eclipse_project\\software-class\\wg-client-simple\\static\\images\\reset.png"));
		resetBtn.setBounds(268, 214, 93, 23);
		contentPane.add(resetBtn);
	}
	
	//数据校验
	private boolean dataCheck(String userName, String password) {
		return !DataUtil.isNull(userName) && !DataUtil.isNull(password);
	}
}
