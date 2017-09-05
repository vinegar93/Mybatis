package com.mybatis.mapper;

import java.util.List;

import com.mybatis.bean.User;
import com.mybatis.bean.UserQueryVo;

public interface UserMapper {

	public List<User> findUserListById(UserQueryVo userQueryVo) throws Exception;
	
	public Integer findUserCountByIds(UserQueryVo userQueryVo) throws Exception;
}
