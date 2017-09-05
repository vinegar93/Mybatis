package com.mybatis.test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.mybatis.bean.User;
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
	public void findUserListById() throws Exception{
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		UserCustomer userCustomer = new UserCustomer();
		//由于这里使用动态sql，如果不设置指定值，条件不会拼接在sql中
		//userCustomer.setId(1);
		UserQueryVo userQueryVo = new UserQueryVo();
		userQueryVo.setUserCustomer(userCustomer);
		List<User> users = userMapper.findUserListById(userQueryVo);
		System.out.println("size:" + users.size());
		for (User user : users) {
			System.out.println(user.toString());
		}
		sqlSession.close();
	}
	
	@Test
	public void findUserCountByIds() throws Exception{
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		UserQueryVo userQueryVo = new UserQueryVo();
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(3);
		userQueryVo.setIds(ids);
		Integer count = userMapper.findUserCountByIds(userQueryVo);
		System.out.println("count:" + count);
		sqlSession.close();
	}
}
