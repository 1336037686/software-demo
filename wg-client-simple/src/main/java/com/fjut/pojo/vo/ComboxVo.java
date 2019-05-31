package com.fjut.pojo.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 下拉菜单Vo
 * @author LGX
 *
 */
@Getter@Setter
public class ComboxVo {
	
	/**
	 * 下拉菜单Key
	 */
	private String key;
	
	/**
	 * 下拉菜单Value
	 */
	private String value;
	
	@Override
	public String toString() {
		return value;
	}

	public ComboxVo() {
	
	}

	public ComboxVo(String key, String value) {
		this.key = key;
		this.value = value;
	}
}
