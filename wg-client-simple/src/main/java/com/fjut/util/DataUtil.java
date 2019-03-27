package com.fjut.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 数据操作工具类
 * @author LGX
 *
 */
public class DataUtil {
	
	public static boolean dateIsOk(Date birthday) {
		return !(birthday.getTime() > new Date().getTime());
	}
	
	/**
	 * 是否为空
	 * @param data
	 * @return
	 */
	public static boolean isNull(String data) {
		return data == null || "".equals(data.trim());
	}

	public static void main(String[] args) throws Exception {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sim.parse("2011-1-1");
		boolean isOk = dateIsOk(date);
		System.out.println(isOk);
	}
}
