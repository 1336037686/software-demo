package com.fjut.pojo.vo;

/**
 * 进出仓数据Vo
 * @author LGX
 *
 */
public class InOutDataVo {
	private String id;
	private String materialsName;
	private int total;
	private int type;
	private String date;
	private String username;
	
	public InOutDataVo() {
		
	}
	public InOutDataVo(String id, String materialsName, int total, int type, String date, String username) {
		this.id = id;
		this.materialsName = materialsName;
		this.total = total;
		this.type = type;
		this.date = date;
		this.username = username;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMaterialsName() {
		return materialsName;
	}
	public void setMaterialsName(String materialsName) {
		this.materialsName = materialsName;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getType() {
		return type == 1 ? "进仓" : "出仓";
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
