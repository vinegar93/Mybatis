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
		//��һ�η������󣬲�ѯidΪ1���û�
		//�ȴӻ����в����Ƿ���idΪ1���û���Ϣ�����û�У�������ݿ��в����û���Ϣ
		User user1 = userMapper1.findUserById1(1);
		System.out.println(user1);
		//ִ�йرղ�����Ŀ���ǽ�sqlSession1�е�����д����������
		sqlSession1.close();

		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
		//�ڶ��η������󣬲�ѯidΪ1���û�
		//�ȴӻ����в����Ƿ���idΪ1���û���Ϣ������У���ֱ�Ӵӻ����в����û���Ϣ
		User user2 = userMapper2.findUserById1(1);
		System.out.println(user2);
		sqlSession1.close();
	}
	
	@Test
	public void testFindUserById2() throws Exception{
		
		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
		//��һ�η������󣬲�ѯidΪ1���û�
		User user1 = userMapper1.findUserById1(1);
		System.out.println(user1);
		//ִ�йرղ�����Ŀ���ǽ�sqlSession1�е�����д����������
		sqlSession1.close();

		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
		User user2 = new User(1, "tom", "123");
		userMapper2.updateUserById(user2);
		//ִ��commit�����userMapper�µĶ�������
		sqlSession2.commit();
		sqlSession2.close();
		
		SqlSession sqlSession3 = sqlSessionFactory.openSession();
		UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
		//�ڶ��η������󣬲�ѯidΪ1���û�
		User user3 = userMapper3.findUserById1(1);
		System.out.println(user3);
		sqlSession1.close();
	}
	
	@Test
	public void testFindUserById3() throws Exception{
		
		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
		//��һ�η������󣬲�ѯidΪ1���û�
		User user1 = userMapper1.findUserById2(1);
		System.out.println(user1);
		sqlSession1.close();

		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
		//�ڶ��η������󣬲�ѯidΪ1���û�
		//���ڽ�����statement��id��findUserById2�Ķ������棬�����û���Ϣ���Ǵ����ݿ��в���
		User user2 = userMapper2.findUserById2(1);
		System.out.println(user2);
		sqlSession1.close();
	}
}
