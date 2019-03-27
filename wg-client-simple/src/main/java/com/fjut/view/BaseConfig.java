package com.fjut.view;

import javax.swing.UIManager;

public class BaseConfig {
	public static void run() {
	    try
	    {
	    	UIManager.put("RootPane.setupButtonVisible", false);
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	    }
	    catch(Exception e)
	    {
	        //TODO exception
	    }
	}

}
