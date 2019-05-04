package com.fjut.pojo.vo;

import java.util.Date;

/**
 * 报表视图实体
 * @author LGX
 *
 */
public class ChartVo {

	private Date date;
	private int sum;
	
	public ChartVo() {
		
	}
	public ChartVo(Date date, int sum) {
		this.date = date;
		this.sum = sum;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getSum() {
		if(this.sum < 0) return -sum;
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
}
