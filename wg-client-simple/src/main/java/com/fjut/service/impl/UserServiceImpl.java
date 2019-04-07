package com.fjut.service.impl;

import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fjut.dao.mapper.UserMapper;
import com.fjut.pojo.Session;
import com.fjut.pojo.User;
import com.fjut.service.UserService;
import com.fjut.util.MD5Util;

/**
 * User服务实现类
 * @author LGX
 *
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 用户登录
	 */
	@Override
	public boolean login(User user) {
		User existUser = userMapper.getUserByIdAndPermission(user.getUserId(), user.getPermission());
		if(existUser == null) return false;
		if(MD5Util.md5(user.getPassword()).equals(existUser.getPassword())) {
			Session.getSession().put("user", existUser);
			return true;
		}
		return false;
	}

	/**
	 * 用户注册
	 */
	@Override
	public boolean register(User user) {
		String userId = user.getUserId();
		User findUser = userMapper.getUserByUserId(userId);
		if(findUser != null) {
			JOptionPane.showMessageDialog(null, "这个用户代码已经存在", "Message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		user.setPassword(MD5Util.md5(user.getPassword()));
		int result = userMapper.addUser(user);
		if(result == 1) {
			JOptionPane.showMessageDialog(null, "添加成功", "Message", JOptionPane.INFORMATION_MESSAGE);
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "添加失败", "Message", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
	}

	
	/**
	 * 查找所有用户并返回相应信息
	 */
	@Override
	public Object[][] getAllUser() {
		List<User> userList = userMapper.getAllUser();
		if(userList != null) {			
			Object[][] userMSG = new Object[userList.size()][6];
			for (int i = 0; i < userList.size(); i++) {
				userMSG[i][0] = i + 1;
				userMSG[i][1] = userList.get(i).getUserId();
				userMSG[i][2] = userList.get(i).getUserName();
				userMSG[i][3] = userList.get(i).getUserGender() == 1 ? "男" : "女";
				userMSG[i][4] = userList.get(i).getPhone();
				userMSG[i][5] = userList.get(i).getPermission() == 1 ? "管理员" : "普通员工";
			}
			return userMSG;
		}
		return new Object[0][0];
	}

	/**
	 * 根据userId获取用户
	 */
	@Override
	public User getUserByUserId(String userId) {
		return userMapper.getUserByUserId(userId);
	}

	/**
	 * 退出登陆
	 * 删除session跳转页面
	 */
	@Override
	public boolean logOut() {
		Object remove = Session.getSession().remove("user");
		if(remove != null) return true;
		return false;
	}

	/**
	 * 查找用户
	 */
	@Override
	public Object[][] getSearchUser(String input) {
		List<User> userList = userMapper.getSearchUser(input);
		if(userList != null && userList.size() >= 0) {			
			Object[][] userMSG = new Object[userList.size()][6];
			for (int i = 0; i < userList.size(); i++) {
				userMSG[i][0] = i + 1;
				userMSG[i][1] = userList.get(i).getUserId();
				userMSG[i][2] = userList.get(i).getUserName();
				userMSG[i][3] = userList.get(i).getUserGender() == 1 ? "男" : "女";
				userMSG[i][4] = userList.get(i).getPhone();
				userMSG[i][5] = userList.get(i).getPermission() == 1 ? "管理员" : "普通员工";
			}
			return userMSG;
		}
		return new Object[0][0];
	}

	/**
	 * 更新用户
	 */
	@Override
	public boolean updateUser(User user) {
		user.setPassword(MD5Util.md5(user.getPassword()));
		int result = userMapper.updateUser(user);
		if(result > 0) return true;
		return false;
	}

	/**
	 * 根据id删除用户
	 */
	@Override
	public boolean deleteUser(String id, String userId) {
		int result = userMapper.deleteUserById(id, userId);
		if(result == 1) return true;
		return false;
	}

	/**
	 * 获取所有用户
	 */
	@Override
	public List<User> getAllUserList() {
		return userMapper.getAllUser();
	}
}
