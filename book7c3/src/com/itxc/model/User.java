package com.itxc.model;
import java.util.Date;
//数据载体-->对应数据表【自定义的结构】模型【领域建模】UML
public class User {
	 private int id;
	 private String name;
	 private String password;
	 private String email;
	 private Date birthday;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	 
}