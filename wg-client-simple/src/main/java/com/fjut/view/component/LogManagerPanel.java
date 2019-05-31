package com.fjut.view.component;

import javax.swing.JPanel;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fjut.pojo.SystemLog;
import com.fjut.service.SystemLogService;
import com.fjut.service.impl.SystemLogServiceImpl;
import com.fjut.util.SpringContextUtils;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;

/**
 * 日志管理面板
 * @author LGX
 *
 */
@Lazy
@Scope("prototype")
@Component("LogManagerPanel")
@SuppressWarnings("all")
public class LogManagerPanel extends JPanel {

	private JTextArea textArea;
	
	SystemLogService systemLogService = SpringContextUtils.getBean(SystemLogServiceImpl.class);
	
	/**
	 * Create the panel.
	 */
	public LogManagerPanel() {
		setBounds(0, 0, 1165, 725);
		setLayout(null);
		
		JButton btnNewButton = new JButton("日志刷新");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//重新加载日志
				initData();
			}
		});
		btnNewButton.setBounds(29, 29, 93, 23);
		add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 81, 1145, 611);
		panel.setBorder(BorderFactory.createTitledBorder("日志列表："));
		add(panel);
		panel.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setForeground(Color.GREEN);
		textArea.setBackground(Color.BLACK);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
		JScrollPane js = new JScrollPane(textArea);
		js.setBounds(25, 25, 1100, 570);
		panel.add(js);
		initData();
	}
	
	
	void initData() {
		//查询所有日志
		List<SystemLog> list = systemLogService.selectLog();
		StringBuffer sb = new StringBuffer();
		if(list != null) {			
			for (SystemLog systemLog : list) {
				//拼接日志
				sb.append(systemLog + "\n");
			}
		}
		textArea.setText("");
		textArea.setText(sb.toString());
	}
	
	
	
}
