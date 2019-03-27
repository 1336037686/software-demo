package com.fjut.pojo;

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

	public Materials() {
		
	}

	public Materials(String materialsId, String materialsName, String model, String unit, int stockQuantity,
			String remarks) {
		this.materialsId = materialsId;
		this.materialsName = materialsName;
		this.model = model;
		this.unit = unit;
		this.stockQuantity = stockQuantity;
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Materials [materialsId=" + materialsId + ", materialsName=" + materialsName + ", model=" + model
				+ ", unit=" + unit + ", stockQuantity=" + stockQuantity + ", remarks=" + remarks + "]";
	}
}
