package com.fjut.pojo.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 订单视图实体
 * @author LGX
 *
 */
@Getter@Setter
public class MaterialsSellVo {
	private String id;
	private String userName;
	private Date date;
	private int type;
	private String remarks;
	
	public MaterialsSellVo() {
		
	}
	
	public MaterialsSellVo(String id, String userName, Date date, int type, String remarks) {
		super();
		this.id = id;
		this.userName = userName;
		this.date = date;
		this.type = type;
		this.remarks = remarks;
	}
}
