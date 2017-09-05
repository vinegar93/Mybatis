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
		
		//mybatis�����ļ�
		String resource = "config/mybatis-config.xml";
		//�õ������ļ���
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//�����Ự����
		SqlSessionFactory sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(inputStream);
		//ͨ���Ự�����õ�SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		String statement = "test.findUserById";
		//ͨ��SqlSession�������ݿ�
		//��һ��������=�����ռ�+statement��id
		//�ڶ���������ָ����ӳ���ļ�����ƥ���parameterType���͵�
		User user = sqlSession.selectOne(statement, 1);
		System.out.println(user.toString());
		//�رջỰ
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
		List<User> users = sqlSession.selectList(statement, "С��");
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
		user.setName("��С��");
		user.setPassword("789");
		sqlSession.insert(statement, user);
		//�ύ����
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
		user.setName("С��");
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
