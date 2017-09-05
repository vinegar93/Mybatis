package cn.hwd.mybatis.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hwd.mybatis.bean.User;
import cn.hwd.mybatis.mapper.UserMapper;

public class UserDaoTest {

	@Test
	public void testFindUserById() throws Exception {
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("classpath:applicationContext.xml");  
        UserMapper userMapper = 
        		(UserMapper) applicationContext.getBean("userMapper");
        User user = userMapper.findUserById(1);
        System.out.println(user);
	}

}
