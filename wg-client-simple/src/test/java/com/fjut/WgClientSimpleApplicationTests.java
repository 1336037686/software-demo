package com.fjut;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fjut.dao.mapper.UserMapper;
import com.fjut.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WgClientSimpleApplicationTests {

	@Autowired
	UserService userService;
	
	@Autowired
	UserMapper userMapper;
	
	@Test
	public void contextLoads() {
//		System.out.println(userMapper.getUserByIdAndPermission("123456", 0));
	}

}
