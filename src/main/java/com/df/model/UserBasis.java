package com.df.model;

import com.df.common.MD5Util;
import java.io.Serializable;

@SuppressWarnings("serial")
public class UserBasis implements Serializable{


	private long id;

	private String name;

	private String password;

	private String phone;

	private int status;

	private int permission;

	private java.util.Date createtime;

	private java.util.Date updatetime;

	public UserBasis(){

	}

	public UserBasis(long id,String name,String password,String phone,int status){
		this.id = id;
		this.name = name;
		this.password = MD5Util.GetMD5Code32(password);
		this.phone = phone;
		this.status = status;
		this.permission = 0;
		this.createtime = new java.util.Date();
		this.updatetime = new java.util.Date();
	}


	public void setId(long value) {
		this.id = value;
	}
	public long getId() {
		return this.id;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	public String getName() {
		return this.name;
	}
	
	public void setPassword(String value) {
		this.password = value;
	}
	public String getPassword() {
		return this.password;
	}
	
	public void setPhone(String value) {
		this.phone = value;
	}
	public String getPhone() {
		return this.phone;
	}
	
	public void setStatus(int value) {
		this.status = value;
	}
	public int getStatus() {
		return this.status;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setUpdatetime(java.util.Date value) {
		this.updatetime = value;
	}
	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}


	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}
}

