package com.fjut.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 数据操作工具类
 * @author LGX
 *
 */
public class DataUtil {
	
	/**
	 * 日期合理
	 * @param birthday
	 * @return
	 */
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

	/**
	 * 数据检查
	 * @return
	 */
	public static boolean dataCheck(String id, String userId, String userName, String password, Date birthday, 
			String identityNum, String birthPlace, String address, String phone) {
		return !DataUtil.isNull(id) && !DataUtil.isNull(userId) && !DataUtil.isNull(userName)  && !DataUtil.isNull(password) && DataUtil.dateIsOk(birthday)
				&& !DataUtil.isNull(identityNum)  && !DataUtil.isNull(birthPlace)  && !DataUtil.isNull(address)  && !DataUtil.isNull(phone); 
	}
	
	
	public static void main(String[] args) throws Exception {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sim.parse("2011-1-1");
		boolean isOk = dateIsOk(date);
		System.out.println(isOk);
	}
}
