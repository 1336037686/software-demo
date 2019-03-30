package com.fjut.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * @author LGX
 *
 */
public class DateUtil {

	public static int getAgeByBirth(Date birthday) {

		//Calendar：日历
		/*从Calendar对象中或得一个Date对象*/
		Calendar cal = Calendar.getInstance();
		/*把出生日期放入Calendar类型的bir对象中，进行Calendar和Date类型进行转换*/
		Calendar bir = Calendar.getInstance();
		bir.setTime(birthday);
		/*如果生日大于当前日期，则抛出异常：出生日期不能大于当前日期*/
		if(cal.before(bir)){
			throw new IllegalArgumentException("The birthday is before Now,It's unbelievable");
		}
		/*取出当前年月日*/
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayNow = cal.get(Calendar.DAY_OF_MONTH);
		/*取出出生年月日*/
		int yearBirth = bir.get(Calendar.YEAR);
		int monthBirth = bir.get(Calendar.MONTH);
		int dayBirth = bir.get(Calendar.DAY_OF_MONTH);
		/*大概年龄是当前年减去出生年*/
		int age = yearNow - yearBirth;
		/*如果出当前月小与出生月，或者当前月等于出生月但是当前日小于出生日，那么年龄age就减一岁*/
		if(monthNow < monthBirth || (monthNow == monthBirth && dayNow < dayBirth)){
			age--;
		}
		return age;
	}
	
	
	public static String dateFormate(Date date) {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		return sim.format(date);
	}
	
	public static void main(String[] args) throws Exception {
//		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = sim.parse("1997-11-16");
//		int year = getAgeByBirth(date);
//		System.out.println(year);
		System.out.println(dateFormate(new Date()));
	}
}
