package cn.bank.ssm.bean;

import java.util.Set;

/**
 * �û���
 * @author Administrator
 * @version 7.0 2017/9/28
 */
public class User {
	
	private int id;//�û�������ʶ
	private String username;//�û���
	private String password;//����
	private double money;//�����
	private String tag;//Ȩ�޵ı��
	private Set log;
	

	public User(){}

	public Set getLog() {
		return log;
	}
	
	public void setLog(Set log) {
		this.log = log;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
	
}
