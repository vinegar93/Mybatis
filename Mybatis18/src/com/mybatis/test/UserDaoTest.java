package com.mybatis.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.mybatis.bean.User;
import com.mybatis.dao.UserDao;

public class UserDaoTest {

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
		//创建UserDao对象
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		//调用UserDao方法
		User user = userDao.findUserById(1);
		System.out.println(user.toString());
		sqlSession.close();
	}

	@Test
	public void testFindUserByName() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		List<User> users = userDao.findUserByName("小明");
		System.out.println(users.toString());
		sqlSession.close();
	}

	@Test
	public void testInsertUser() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		User user = new User();
		user.setId(3);
		user.setName("黄小红");
		user.setPassword("789");
		userDao.insertUser(user);
		sqlSession.commit();
		sqlSession.close();
	}

	@Test
	public void testUpdateUserById() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		User user = new User();
		user.setId(3);
		user.setName("小红");
		user.setPassword("777");
		userDao.updateUserById(user);
		sqlSession.commit();
		sqlSession.close();
	}

	@Test
	public void testDeleteUserById() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		userDao.deleteUserById(3);
		sqlSession.commit();
		sqlSession.close();
	}
}
