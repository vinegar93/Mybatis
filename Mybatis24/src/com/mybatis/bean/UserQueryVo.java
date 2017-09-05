package com.mybatis.bean;

public class UserQueryVo {

	private UserCustomer userCustomer;

	public UserCustomer getUserCustomer() {
		return userCustomer;
	}

	public void setUserCustomer(UserCustomer userCustomer) {
		this.userCustomer = userCustomer;
	}
	
	//还可以包装其他的的查询条件，例如订单信息，商品信息等等
}
