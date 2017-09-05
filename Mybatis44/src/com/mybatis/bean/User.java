package com.mybatis.bean;

import java.io.Serializable;

/**
 * ʵ�����л��ӿڣ�Ŀ����ʵ�ַ����л���������Ϊ�����������ݴ洢���ʶ��ֶ�������һ���������ڴ���
 * @author vineg
 *
 */
public class User implements Serializable {

	private Integer id;
	private String name;
	private String password;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Integer id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
}
