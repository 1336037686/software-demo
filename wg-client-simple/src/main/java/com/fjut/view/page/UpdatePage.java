package com.fjut.view.page;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.fjut.pojo.Version;

@SuppressWarnings("all")
public class UpdatePage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField versionField;


	/**
	 * Create the dialog.
	 */
	public UpdatePage(Version version) {
		setBounds(100, 100, 271, 189);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("最新版本");
		label.setBounds(32, 43, 54, 15);
		contentPanel.add(label);
		
		versionField = new JTextField();
		versionField.setEnabled(false);
		versionField.setBounds(96, 40, 122, 21);
		contentPanel.add(versionField);
		versionField.setColumns(10);
		versionField.setText(version.getVersion() + "");
		
		JButton button = new JButton("更新");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().getParentFile().getParentFile().getPath(); //获取应用文件夹路径
				System.out.println(path);
				path = path.substring(path.indexOf("\\") + 1);
				File file = new File(path + "\\wg-update-simple.jar");
				
//		        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
//		        String pid = runtimeMXBean.getName().split("@")[0]; // 获取当前进程号
				
		        if(JOptionPane.showConfirmDialog(null, "确定更新？") == 0) {		        	
		        	try {
		        		Process process = Runtime.getRuntime().exec("java -jar " + path + "\\wg-update-simple.jar"); //执行更新程序
		        		process.waitFor();
		        		System.exit(0); //更新结束后退出系统
		        	} catch (Exception e1) {
		        		e1.printStackTrace();
		        	}
		        }
			}
		});
		button.setBounds(76, 102, 93, 23);
		contentPanel.add(button);
	}
}
