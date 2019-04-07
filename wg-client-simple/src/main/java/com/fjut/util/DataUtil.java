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
	 * 是否为空
	 */
	public static boolean isNull(String data) {
		return data == null || "".equals(data.trim());
	}
	
	/**
	 * 查找数组中当前值的下标位置
	 * @param array 查找数组
	 * @param target 目标查找值
	 * @return 返回下标
	 */
	public static int getTargetIndex(String[] array, String target) {
		if(array != null) {
			for (int i = 0; i < array.length; i++) {
				if(array[i].equals(target)) {
					return i;
				}
			}
		}
		return 0;
	}
	
	/**
	 * 当前字符串是否是数字字符串
	 * @param str
	 * @return
	 */
	public static boolean isIntStr(String str) {
		try {
			Integer.parseInt(str);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * 日期合理性检查
	 */
	public static boolean dateIsOk(Date birthday) {
		return !(birthday.getTime() > new Date().getTime());
	}

	/**
	 * User数据检查
	 */
	public static boolean dataCheck(String id, String userId, String userName, String password, Date birthday, 
			String identityNum, String birthPlace, String address, String phone) {
		return !DataUtil.isNull(id) && !DataUtil.isNull(userId) && !DataUtil.isNull(userName)  && !DataUtil.isNull(password) && DataUtil.dateIsOk(birthday)
				&& !DataUtil.isNull(identityNum)  && !DataUtil.isNull(birthPlace)  && !DataUtil.isNull(address)  && !DataUtil.isNull(phone); 
	}
	
	/**
	 * 物料数据检查
	 */
	public static boolean dataCheck(String materialId, String materialName, String materialUnit, String materialModel) {
		return !DataUtil.isNull(materialId) && !DataUtil.isNull(materialName) && !DataUtil.isNull(materialUnit) && !DataUtil.isNull(materialUnit);
	}
	
	public static void main(String[] args) throws Exception {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sim.parse("2011-1-1");
		boolean isOk = dateIsOk(date);
		System.out.println(isOk);
	}
}
