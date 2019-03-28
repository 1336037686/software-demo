package com.fjut.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

/**
 * md5加密工具类
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("all")
public class MD5Util {
	
	private static Random random = new Random();
	
	/**
	 * 创建一个随机MD5值
	 * @return
	 */
	public static String getMD5() {
		String msg = System.currentTimeMillis() + "" + random.nextInt(Integer.MAX_VALUE) + "" + random.nextInt(Integer.MAX_VALUE);
		return md5(msg);
	}
	
	/**
	 * 使用md5的算法进行加密
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}
	
	public static void main(String[] args) {
		System.out.println(md5("666666"));
		//f379eaf3c831b04de153469d1bec345e
		
	}
}
