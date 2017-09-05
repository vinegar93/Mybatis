package com.mybatis.mapper;

import com.mybatis.bean.User;

public interface UserMapper {

	public User findUserById(Integer id) throws Exception;
	
	public void updateUserById(User user) throws Exception;
}
