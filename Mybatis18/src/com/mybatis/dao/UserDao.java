package com.mybatis.dao;

import java.util.List;

import com.mybatis.bean.User;

/*
 * UserDao.java接口中的方法名称和UserMapper.xml中的statement的id一致
 * UserDao.java接口中的方法输入参数类型和UserMapper.xml中的statement的parameterType指定的类型一致
 * UserDao.java接口中的方法返回值类型和UserMapper.xml中的statement的resultType指定的类型一致
 */
public interface UserDao {

	public User findUserById(int id) throws Exception;
	
	public List<User> findUserByName(String name) throws Exception;
	
	public void insertUser(User user) throws Exception;
	
	public void updateUserById(User user) throws Exception;
	
	public void deleteUserById(int id) throws Exception;
}
