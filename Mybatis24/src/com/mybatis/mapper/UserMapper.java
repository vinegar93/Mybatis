package com.mybatis.mapper;

import com.mybatis.bean.UserCustomer;
import com.mybatis.bean.UserQueryVo;

public interface UserMapper {

	public UserCustomer findUserListById(UserQueryVo userQueryVo) throws Exception;
	
	public Integer findUserCountById(UserQueryVo userQueryVo) throws Exception;
}
