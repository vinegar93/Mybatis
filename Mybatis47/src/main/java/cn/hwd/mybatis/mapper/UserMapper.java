package cn.hwd.mybatis.mapper;

import cn.hwd.mybatis.bean.User;

public interface UserMapper {

	public User findUserById(Integer id) throws Exception;

}
