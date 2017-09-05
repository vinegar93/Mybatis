package com.mybatis.dao;

import java.util.List;

import com.mybatis.bean.User;

/*
 * UserDao.java�ӿ��еķ������ƺ�UserMapper.xml�е�statement��idһ��
 * UserDao.java�ӿ��еķ�������������ͺ�UserMapper.xml�е�statement��parameterTypeָ��������һ��
 * UserDao.java�ӿ��еķ�������ֵ���ͺ�UserMapper.xml�е�statement��resultTypeָ��������һ��
 */
public interface UserDao {

	public User findUserById(int id) throws Exception;
	
	public List<User> findUserByName(String name) throws Exception;
	
	public void insertUser(User user) throws Exception;
	
	public void updateUserById(User user) throws Exception;
	
	public void deleteUserById(int id) throws Exception;
}
