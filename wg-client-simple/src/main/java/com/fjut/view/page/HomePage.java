package com.fjut.view.page;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.fjut.pojo.Session;
import com.fjut.pojo.User;
import com.fjut.pojo.Version;
import com.fjut.pojo.vo.ClientMSG;
import com.fjut.service.UserService;
import com.fjut.util.SpringContextUtils;
import com.fjut.util.UpdateUtil;
import com.fjut.view.component.IndexPanel;

/**
 * 主页面
 * @author LGX
 *
 */
@Lazy //启用懒加载策略
@Scope("prototype")
@org.springframework.stereotype.Component("HomePage")
@SuppressWarnings("all")
public class HomePage extends JFrame {

	@Autowired
	@Qualifier("ClientMSG")
	private ClientMSG clientMSG;
	
	@Autowired
	private UpdateUtil updateUtil;
	
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public HomePage() {
		//获取登录用户
		User loginUser = (User) Session.getSession().get("user");
		//获取服务层
		UserService userService = SpringContextUtils.getBean(UserService.class);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		setLocationRelativeTo(null);
		setResizable(false);
		
		//菜单栏信息
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu selectMenu = new JMenu("选择");
		menuBar.add(selectMenu);
		
		JMenu helpMenu = new JMenu("帮助");
		menuBar.add(helpMenu);
		
		JMenuItem checkMenuItem = new JMenuItem("检查更新");
		helpMenu.add(checkMenuItem);
		checkMenuItem.addActionListener((e) -> {
			Version version = updateUtil.getInfoData();
			if(version != null) {				
				String currVersion = clientMSG.getVersion().substring(clientMSG.getVersion().lastIndexOf(" ") + 1);
				double cv = Double.parseDouble(currVersion);
				if(version.getVersion() == cv) {
					JOptionPane.showMessageDialog(null, "当前已经是最新版本", "提示", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(version.getVersion() > cv) {
					new UpdatePage(version).setVisible(true);
				}
			}
		});
		
		JMenuItem versionMenuItem = new JMenuItem("当前版本");
		helpMenu.add(versionMenuItem);
		versionMenuItem.addActionListener((e) -> {
			JOptionPane.showMessageDialog(null, clientMSG.getVersion(), "当前版本", JOptionPane.INFORMATION_MESSAGE);
		});
		
		JMenuItem aboutMeMenuItem = new JMenuItem("关于我们");
		helpMenu.add(aboutMeMenuItem);
		aboutMeMenuItem.addActionListener((e) -> {
			JOptionPane.showMessageDialog(null, clientMSG.getAboutMe(), "关于我们", JOptionPane.INFORMATION_MESSAGE);
		});
		
		Component horizontalGlue = Box.createHorizontalGlue();
		menuBar.add(horizontalGlue);
		
		
		JMenu adminMenu = new JMenu("欢迎，" + (loginUser.getPermission() == 1 ? "管理员" : "普通员工")  + "：" + loginUser.getUserName());
		menuBar.add(adminMenu);
		
		JMenuItem updateAdminMenuItem = new JMenuItem("修改信息");
		adminMenu.add(updateAdminMenuItem);
		//修改用户信息
		updateAdminMenuItem.addActionListener((e) -> {
            ((UserUpdatePage)SpringContextUtils.getBean("UserUpdatePage")).setVisible(true);
		});
		
		JMenuItem logOutMenuItem = new JMenuItem("退出登陆");
		adminMenu.add(logOutMenuItem);
		//退出登陆
		logOutMenuItem.addActionListener((e) -> {
			if(userService.logOut()) {
                setVisible(false);
                ((LoginPage)SpringContextUtils.getBean("LoginPage")).setVisible(true);
				dispose();
			}
		});
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane contentTablePane = new JTabbedPane(JTabbedPane.TOP);
		contentTablePane.setBounds(10, 10, 1170, 730);
		contentPane.add(contentTablePane);
		
		//添加默认页面
		JPanel indexPanel = new IndexPanel();
		contentTablePane.addTab("Home 主页", new ImageIcon("E:\\DevelopProjects\\Java_Project\\eclipse_project\\software-class\\wg-client-simple\\static\\images\\home.png"), indexPanel, null);
		
		//根据登陆用户权限加载菜单
		if(loginUser != null) {
			//管理员账号
			if(loginUser.getPermission() == 1) {
				//用户管理主页
				JPanel userManagerPanel = (JPanel) SpringContextUtils.getBean("UserManagerPanel");
				contentTablePane.addTab("用户管理", new ImageIcon("E:\\DevelopProjects\\Java_Project\\eclipse_project\\software-class\\wg-client-simple\\static\\images\\usermanager.png"), userManagerPanel, null);
			}
		}
		
		//物料管理主页
		JPanel materialManagerPanel = (JPanel) SpringContextUtils.getBean("MaterialManagerPanel");
		contentTablePane.addTab("物料管理", new ImageIcon("E:\\DevelopProjects\\Java_Project\\eclipse_project\\software-class\\wg-client-simple\\static\\images\\material.png"), materialManagerPanel, null);
		
		//进出仓管理主页 TradingManagerPanel
		JPanel tradingManagerPanel = (JPanel) SpringContextUtils.getBean("TradingManagerPanel");
		contentTablePane.addTab("进出仓管理", new ImageIcon("E:\\DevelopProjects\\Java_Project\\eclipse_project\\software-class\\wg-client-simple\\static\\images\\materialsSell.png"), tradingManagerPanel, null);
		
		//报表管理主页 TradingManagerPanel
		JPanel formManagerPanel = (JPanel) SpringContextUtils.getBean("FormManagerPanel");
		contentTablePane.addTab("报表管理", new ImageIcon("E:\\DevelopProjects\\Java_Project\\eclipse_project\\software-class\\wg-client-simple\\static\\images\\materialsSell.png"), formManagerPanel, null);
		
		
	}
}
