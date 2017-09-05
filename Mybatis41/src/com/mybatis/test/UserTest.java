package com.mybatis.test;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.mybatis.bean.User;
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
	public void testFindUserById1() throws Exception{
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		//第一次发起请求，查询id为1的用户
		//先从缓存中查找是否有id为1的用户信息，如果没有，则从数据库中查找用户信息
		//得到用户信息并保存在一级缓存中
		User user1 = userMapper.findUserById(1);
		System.out.println(user1);

		//第二次发起请求，查询id为1的用户
		//先从缓存中查找是否有id为1的用户信息，如果有，则直接从缓存中查找用户信息
		User user2 = userMapper.findUserById(1);
		System.out.println(user2);
		
		sqlSession.close();
	}
	
	@Test
	public void testFindUserById2() throws Exception{
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		//第一次发起请求，查询id为1的用户
		User user1 = userMapper.findUserById(1);
		System.out.println(user1);
		
		//更新user1
		user1.setPassword("123456");
		userMapper.updateUserById(user1);
		//如果sqlSession执行commit操作，则会清空sqlSession中的一级缓存，目的是避免脏读
		sqlSession.commit();
		
		//第二次发起请求，查询id为1的用户
		User user2 = userMapper.findUserById(1);
		System.out.println(user2);
		
		sqlSession.close();
	}
}
