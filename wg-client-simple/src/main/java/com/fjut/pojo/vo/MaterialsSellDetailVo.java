package com.fjut.pojo.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 订当详情视图Vo实体
 * @author LGX
 *
 */
@Getter@Setter
public class MaterialsSellDetailVo {
	private int id;
	private String materialsName;
	private int total;
	
	public MaterialsSellDetailVo() {
		
	}

	public MaterialsSellDetailVo(int id, String materialsName, int total) {
		this.id = id;
		this.materialsName = materialsName;
		this.total = total;
	}
}
