package com.fjut.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * 进出仓详情表
 * @author LGX
 *
 */
@Getter@Setter
public class MaterialsSellDetail {
	
	/**
	 * ID
	 */
	private int id;
	
	/**
	 * 进出仓单号
	 */
	private String materialsSellId;
	
	/**
	 * 物料ID
	 */
	private String materialsId;
	
	/**
	 * 进出仓数量
	 */
	private int total;

	public MaterialsSellDetail() {
		
	}

	public MaterialsSellDetail(int id, String materialsSellId, String materialsId, int total) {
		this.id = id;
		this.materialsSellId = materialsSellId;
		this.materialsId = materialsId;
		this.total = total;
	}

	@Override
	public String toString() {
		return "MaterialsSellDetail [id=" + id + ", materialsSellId=" + materialsSellId + ", materialsId=" + materialsId
				+ ", total=" + total + "]";
	}
}
