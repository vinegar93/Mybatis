package com.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;

import com.mybatis.bean.User;

public class Test {

	@Before
	public void setUp() throws Exception {
	}

	@org.junit.Test
	public void findUserById() throws IOException {
		
		//mybatis配置文件
		String resource = "config/mybatis-config.xml";
		//得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建会话工厂
		SqlSessionFactory sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(inputStream);
		//通过会话工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		String statement = "test.findUserById";
		//通过SqlSession操作数据库
		//第一个参数：=命名空间+statement的id
		//第二个参数：指定和映射文件中所匹配的parameterType类型的
		User user = sqlSession.selectOne(statement, 1);
		System.out.println(user.toString());
		//关闭会话
		sqlSession.close();
	}
	
	@org.junit.Test
	public void findUserByName() throws IOException {
		
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		String statement = "test.findUserByName";
		List<User> users = sqlSession.selectList(statement, "小明");
		System.out.println(users.toString());
		sqlSession.close();
	}
	
	@org.junit.Test
	public void insertUser() throws IOException {
		
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		String statement = "test.insertUser";
		User user = new User();
		user.setId(3);
		user.setName("黄小红");
		user.setPassword("789");
		sqlSession.insert(statement, user);
		//提交事务
		sqlSession.commit();
		sqlSession.close();
	}
	
	@org.junit.Test
	public void updateUserById() throws IOException {
		
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		String statement = "test.updateUserById";
		User user = new User();
		user.setId(3);
		user.setName("小红");
		user.setPassword("777");
		sqlSession.update(statement, user);
		sqlSession.commit();
		sqlSession.close();
	}
	
	@org.junit.Test
	public void deleteUserById() throws IOException {
		
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		String statement = "test.deleteUserById";
		sqlSession.delete(statement, 3);
		sqlSession.commit();
		sqlSession.close();
	}
}
