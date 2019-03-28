package com.fjut.dao.mapper;

import java.util.List;

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
	
	@Select("select * from user where userid like concat(concat('%', #{input}), '%')  or userName like concat(concat('%', #{input}), '%') or age like concat(concat('%', #{input}), '%') "
			+ "or birthday like concat(concat('%', #{input}), '%') or identityNum like concat(concat('%', #{input}), '%') or birthPlace like concat(concat('%', #{input}), '%') "
			+ "or address like concat(concat('%', #{input}), '%') or phone like concat(concat('%', #{input}), '%')")
	public List<User> getSearchUser(@Param("input") String input);
	
	@Update("update table user set password = #{password}, userName = #{userName}, age = #{age}, userGender = #{userGender}, birthday = #{birthday}, identityNum = #{identityNum}, "
			+ "birthPlace = #{birthPlace}, address = #{address}, phone = #{phone}, permission = #{permission} where id = #{id} or userId = #{userId}")
	public int updateUser(User user);

}
