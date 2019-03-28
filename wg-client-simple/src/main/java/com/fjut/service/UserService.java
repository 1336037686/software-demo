package com.fjut.service;

import com.fjut.pojo.User;

/**
 * User服务类
 * @author LGX
 *
 */
public interface UserService {
	
	/**
	 * 用户登陆
	 * @param user 需要登录的用户
	 */
	public boolean login(User user);
	
	/**
	 * 退出登陆
	 * @return
	 */
	public boolean logOut();
	
	/**
	 * 用户注册
	 * @param user 需要注册的用户
	 */
	public boolean register(User user);
	
	/**
	 * 查找所有用户并返回信息
	 * @return
	 */
	public Object[][] getAllUser();
	
	/**
	 * 根据UserID获取用户
	 * @param userId
	 * @return
	 */
	public User getUserByUserId(String userId);
	
	/**
	 * 根据关键字查询，用户名，姓名，性别，年龄
	 * @param input
	 * @return
	 */
	public Object[][] getSearchUser(String input);
	
	/**
	 * 用户更新
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user);
	
	/**
	 * 根据id删除用户
	 * @param id
	 * @param userId
	 * @return
	 */
	public boolean deleteUser(String id, String userId);
}
