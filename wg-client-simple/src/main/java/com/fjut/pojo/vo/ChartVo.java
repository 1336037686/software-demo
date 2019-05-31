package com.fjut.pojo.vo;

/**
 * 报表视图实体
 * @author LGX
 *
 */
public class ChartVo {

	/**
	 * 月份
	 */
	private int month; 
	
	/**
	 * 年份
	 */
	private int year;
	
	/**
	 * 总数
	 */
	private int sum;
	
	//private Date date;
	
	public ChartVo(int month, int year, int sum) {
		super();
		this.month = month;
		this.year = year;
		this.sum = sum;
	}
	
	public ChartVo() {
		
	}
	
//	public ChartVo(Date date, int sum) {
//		this.date = date;
//		this.sum = sum;
//	}
//	public Date getDate() {
//		return date;
//	}
//	public void setDate(Date date) {
//		this.date = date;
//	}

	public int getSum() {
		if(this.sum < 0) return -sum;
		return sum;
	}
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "ChartVo [month=" + month + ", year=" + year + ", sum=" + sum + "]";
	}
	
	
}
