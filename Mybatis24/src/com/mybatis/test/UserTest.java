package com.mybatis.test;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.mybatis.bean.UserCustomer;
import com.mybatis.bean.UserQueryVo;
import com.mybatis.mapper.UserMapper;

public class UserTest {

	private SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void setUp() throws Exception {
		
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testFindUserById() throws Exception{
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustomer userCustomer = new UserCustomer();
		userCustomer.setId(1);
		userQueryVo.setUserCustomer(userCustomer);
		UserCustomer uc = userMapper.findUserListById(userQueryVo);
		Integer count = userMapper.findUserCountById(userQueryVo);
		System.out.println(uc.toString());
		System.out.println(count);
		sqlSession.close();
	}
}
