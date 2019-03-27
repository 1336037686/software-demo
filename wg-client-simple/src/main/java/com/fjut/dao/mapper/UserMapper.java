package com.fjut.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
	

}
