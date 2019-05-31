package com.fjut;


import java.awt.EventQueue;

import com.fjut.util.SpringContextUtils;
import com.fjut.view.page.LoginPage;

/**
 * 主页启动
 * @author LGX
 *
 */
public class ViewStart {
	
	public static void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//获取LoginPage界面实例并显示
					SpringContextUtils.getBean(LoginPage.class).setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
