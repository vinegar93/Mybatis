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
		
		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
		//第一次发起请求，查询id为1的用户
		//先从缓存中查找是否有id为1的用户信息，如果没有，则从数据库中查找用户信息
		User user1 = userMapper1.findUserById1(1);
		System.out.println(user1);
		//执行关闭操作，目的是将sqlSession1中的数据写到二级缓存
		sqlSession1.close();

		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
		//第二次发起请求，查询id为1的用户
		//先从缓存中查找是否有id为1的用户信息，如果有，则直接从缓存中查找用户信息
		User user2 = userMapper2.findUserById1(1);
		System.out.println(user2);
		sqlSession1.close();
	}
	
	@Test
	public void testFindUserById2() throws Exception{
		
		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
		//第一次发起请求，查询id为1的用户
		User user1 = userMapper1.findUserById1(1);
		System.out.println(user1);
		//执行关闭操作，目的是将sqlSession1中的数据写到二级缓存
		sqlSession1.close();

		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
		User user2 = new User(1, "tom", "123");
		userMapper2.updateUserById(user2);
		//执行commit，清空userMapper下的二级缓存
		sqlSession2.commit();
		sqlSession2.close();
		
		SqlSession sqlSession3 = sqlSessionFactory.openSession();
		UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
		//第二次发起请求，查询id为1的用户
		User user3 = userMapper3.findUserById1(1);
		System.out.println(user3);
		sqlSession1.close();
	}
	
	@Test
	public void testFindUserById3() throws Exception{
		
		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
		//第一次发起请求，查询id为1的用户
		User user1 = userMapper1.findUserById2(1);
		System.out.println(user1);
		sqlSession1.close();

		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
		//第二次发起请求，查询id为1的用户
		//由于禁用了statement的id是findUserById2的二级缓存，所以用户信息还是从数据库中查找
		User user2 = userMapper2.findUserById2(1);
		System.out.println(user2);
		sqlSession1.close();
	}
}
