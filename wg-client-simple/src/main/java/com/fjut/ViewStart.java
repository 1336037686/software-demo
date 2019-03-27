package com.fjut;


import java.awt.EventQueue;

import com.fjut.util.SpringContextUtils;
import com.fjut.view.page.LoginPage;

public class ViewStart {
	
	public static void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpringContextUtils.getBean(LoginPage.class).setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
