package com.mybatis.bean;

import java.util.List;

public class UserQueryVo {

	private List<Integer> ids;
	
	private UserCustomer userCustomer;

	public UserCustomer getUserCustomer() {
		return userCustomer;
	}

	public void setUserCustomer(UserCustomer userCustomer) {
		this.userCustomer = userCustomer;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	
	//�����԰�װ�����ĵĲ�ѯ���������綩����Ϣ����Ʒ��Ϣ�ȵ�
}
