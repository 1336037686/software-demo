package com.fjut.pojo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 企业人员类
 * @author LGX
 *
 */
@Getter@Setter
public class User {
	/**
	 * 人员id
	 */
	private String id;
	
	/**
	 * 人员代码 唯一值
	 */
	private String userId;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 人员姓名
	 */
	private String userName;
	
	/**
	 * 性别 男1 女0
	 */
	private Integer userGender;

	/**
	 * 出生日期
	 */
	private Date birthday;
	
	/**
	 * 身份证
	 */
	private String identityNum;
	
	/**
	 * 籍贯
	 */
	private String birthPlace;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 电话号码
	 */
	private String phone;
	
	/**
	 * 权限 管理员1，普通员工0，默认0
	 */
	private int permission;
	
	/**
	 * 年龄
	 */
	private Integer age;
	
	/**
	 * 注册日期
	 */
	private Date registerDay;
	
	/**
	 * 具体权限 1：物料管理，2：进出仓管理，3：报表管理，4：日志管理
	 */
	private String authority;
	
	/**
	 * 是否删除: 0未删除,1删除
	 */
	private int isDelete;

	public User() {}

	public User(String id, String userId, String password, String userName, Integer userGender, Date birthday,
			String identityNum, String birthPlace, String address, String phone, int permission, Integer age,
			Date registerDay, String authority, int isDelete) {
		super();
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.userGender = userGender;
		this.birthday = birthday;
		this.identityNum = identityNum;
		this.birthPlace = birthPlace;
		this.address = address;
		this.phone = phone;
		this.permission = permission;
		this.age = age;
		this.registerDay = registerDay;
		this.authority = authority;
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", password=" + password + ", userName=" + userName
				+ ", userGender=" + userGender + ", birthday=" + birthday + ", identityNum=" + identityNum
				+ ", birthPlace=" + birthPlace + ", address=" + address + ", phone=" + phone + ", permission="
				+ permission + ", age=" + age + ", registerDay=" + registerDay + ", authority=" + authority
				+ ", isDelete=" + isDelete + "]";
	}
	
	
}
