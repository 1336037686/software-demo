package com.fjut.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.fjut.pojo.User;

@Mapper
public interface UserMapper {
	
	@Insert("insert into user values(#{id}, #{userId}, #{password}, #{userName}, #{age}, #{userGender}, #{birthday}, #{registerDay}, #{identityNum}, #{birthPlace}, #{address}, #{phone}, #{permission})")
	public int addUser(User user);
	
	@Select("select * from user")
	public List<User> getAllUser();
	
	@Select("select * from user where userId = #{userId}")
	public User getUserByUserId(String userId);
	
	@Select("select * from user where userId = #{userId} and permission = #{permission}")
	public User getUserByIdAndPermission( @Param("userId") String userId, @Param("permission") Integer permission);
	
	@Select("select * from user where userid like  binary concat(concat('%', #{input}), '%')  or userName like binary concat(concat('%', #{input}), '%') or age like  binary concat(concat('%', #{input}), '%') "
			+ "or birthday like binary concat(concat('%', #{input}), '%') or identityNum like binary concat(concat('%', #{input}), '%') or birthPlace like binary concat(concat('%', #{input}), '%') "
			+ "or address like binary concat(concat('%', #{input}), '%') or phone like binary concat(concat('%', #{input}), '%')")
	public List<User> getSearchUser(@Param("input") String input);
	
	@Update("update user set password = #{password}, userName = #{userName}, age = #{age}, userGender = #{userGender}, birthday = #{birthday}, identityNum = #{identityNum}, "
			+ "birthPlace = #{birthPlace}, address = #{address}, phone = #{phone}, permission = #{permission} where id = #{id} or userId = #{userId}")
	public int updateUser(User user);
	
	@Delete("delete from user where id = #{id} or userid = #{userId}")
	public int deleteUserById(@Param("id") String id, @Param("userId") String userId);

}
