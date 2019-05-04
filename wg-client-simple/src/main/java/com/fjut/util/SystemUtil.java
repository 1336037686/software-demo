package com.fjut.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 系统工具类
 * @author LGX
 *
 */
public class SystemUtil {
	
	/**
	 * 获取主机地址
	 * @return
	 */
	public static String getLocalHost() {
		InetAddress localHost = null;
		try {
			localHost = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return localHost != null ? localHost.toString() : "Unknow-Computer/127.0.0.1";
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(getLocalHost());
	}
}
