package com.fjut.pojo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 物料类
 * @author LGX
 *
 */
@Getter@Setter
public class Materials {
	
	/**
	 * id
	 */
	private String id;
	
	/**
	 * 物料代码
	 */
	private String materialsId;
	
	/**
	 * 物料名称
	 */
	private String materialsName;
	
	/**
	 * 型号规模
	 */
	private String model;
	
	/**
	 * 计量单位
	 */
	private String unit;
	
	/**
	 * 库存数量
	 */
	private int stockQuantity;
	
	/**
	 * 物料备注
	 */
	private String remarks;
	
	/**
	 * 档案创建日期
	 */
	private Date createDate;
	
	/**
	 * 是否删除
	 */
	private int isDelete;

	
	public Materials() {
		
	}


	public Materials(String id, String materialsId, String materialsName, String model, String unit, int stockQuantity,
			String remarks, Date createDate, int isDelete) {
		this.id = id;
		this.materialsId = materialsId;
		this.materialsName = materialsName;
		this.model = model;
		this.unit = unit;
		this.stockQuantity = stockQuantity;
		this.remarks = remarks;
		this.createDate = createDate;
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		return "Materials [id=" + id + ", materialsId=" + materialsId + ", materialsName=" + materialsName + ", model="
				+ model + ", unit=" + unit + ", stockQuantity=" + stockQuantity + ", remarks=" + remarks
				+ ", createDate=" + createDate + ", isDelete=" + isDelete + "]";
	}
}
