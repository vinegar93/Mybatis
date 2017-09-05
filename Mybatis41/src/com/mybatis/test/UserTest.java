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
		
		//��һ�η������󣬲�ѯidΪ1���û�
		//�ȴӻ����в����Ƿ���idΪ1���û���Ϣ�����û�У�������ݿ��в����û���Ϣ
		//�õ��û���Ϣ��������һ��������
		User user1 = userMapper.findUserById(1);
		System.out.println(user1);

		//�ڶ��η������󣬲�ѯidΪ1���û�
		//�ȴӻ����в����Ƿ���idΪ1���û���Ϣ������У���ֱ�Ӵӻ����в����û���Ϣ
		User user2 = userMapper.findUserById(1);
		System.out.println(user2);
		
		sqlSession.close();
	}
	
	@Test
	public void testFindUserById2() throws Exception{
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		//��һ�η������󣬲�ѯidΪ1���û�
		User user1 = userMapper.findUserById(1);
		System.out.println(user1);
		
		//����user1
		user1.setPassword("123456");
		userMapper.updateUserById(user1);
		//���sqlSessionִ��commit������������sqlSession�е�һ�����棬Ŀ���Ǳ������
		sqlSession.commit();
		
		//�ڶ��η������󣬲�ѯidΪ1���û�
		User user2 = userMapper.findUserById(1);
		System.out.println(user2);
		
		sqlSession.close();
	}
}
