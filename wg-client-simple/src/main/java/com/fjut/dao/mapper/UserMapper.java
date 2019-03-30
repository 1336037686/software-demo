package com.fjut.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.fjut.pojo.User;

/**
 * 用户操作Mapper
 * @author LGX
 *
 */
@Mapper
public interface UserMapper {
	
	/**
	 * 添加用户
	 */
	@Insert("insert into user values(#{id}, #{userId}, #{password}, #{userName}, #{age}, #{userGender}, #{birthday}, #{registerDay}, #{identityNum}, #{birthPlace}, #{address}, #{phone}, #{permission})")
	public int addUser(User user);
	
	/**
	 * 获取所有用户
	 */
	@Select("select * from user")
	public List<User> getAllUser();
	
	/**
	 * 根据用户代码获取用户
	 */
	@Select("select * from user where userId = #{userId}")
	public User getUserByUserId(String userId);
	
	/**
	 * 根据用户ID和密码获取用户
	 */
	@Select("select * from user where userId = #{userId} and permission = #{permission}")
	public User getUserByIdAndPermission( @Param("userId") String userId, @Param("permission") Integer permission);
	
	/**
	 * 根据关键字查询用户
	 */
	@Select("select * from user where userid like  binary concat(concat('%', #{input}), '%')  or userName like binary concat(concat('%', #{input}), '%') or age like  binary concat(concat('%', #{input}), '%') "
			+ "or birthday like binary concat(concat('%', #{input}), '%') or identityNum like binary concat(concat('%', #{input}), '%') or birthPlace like binary concat(concat('%', #{input}), '%') "
			+ "or address like binary concat(concat('%', #{input}), '%') or phone like binary concat(concat('%', #{input}), '%')")
	public List<User> getSearchUser(@Param("input") String input);
	
	/**
	 * 更新用户
	 */
	@Update("update user set password = #{password}, userName = #{userName}, age = #{age}, userGender = #{userGender}, birthday = #{birthday}, identityNum = #{identityNum}, "
			+ "birthPlace = #{birthPlace}, address = #{address}, phone = #{phone}, permission = #{permission} where id = #{id} or userId = #{userId}")
	public int updateUser(User user);
	
	/**
	 * 删除用户
	 */
	@Delete("delete from user where id = #{id} or userid = #{userId}")
	public int deleteUserById(@Param("id") String id, @Param("userId") String userId);

}
