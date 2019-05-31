package com.fjut.pojo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 进出仓实体类
 * @author LGX
 *
 */
@Getter@Setter
public class MaterialsSell {
	/**
	 * 进出仓单号
	 */
	private String id;
	
	/**
	 * 操作日期
	 */
	private Date date;
	
	/**
	 * 操作人员代码
	 */
	private String userId;
	
	/**
	 * 备注
	 */
	private String remarks;
	
	/**
	 * 进出仓类型. 进:1,出:0
	 */
	private int type;

	public MaterialsSell() {

	}

	public MaterialsSell(String id, Date date, String userId, String remarks, int type) {
		this.id = id;
		this.date = date;
		this.userId = userId;
		this.remarks = remarks;
		this.type = type;
	}

	@Override
	public String toString() {
		return "订单 [订单号=" + id + ", 订单日期=" + date + ", 用户ID=" + userId + ", 进出仓类型="
				+ (type == 1 ? "进仓" : "出仓") + "]" + "\n + [备注=" + remarks + "]\n";
	}
}
