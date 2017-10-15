package me.only1jia.foodstore.domain;

import java.util.LinkedHashSet;
import java.util.Set;

public class User {

	private Integer userId;
	private String username;
	private String password;
	private String telephone;
	private int accountId;
	 
	private Set<Trade> trades = new LinkedHashSet<Trade>();

	public void setTrades(Set<Trade> trades) {
		this.trades = trades;
	}
	
	public Set<Trade> getTrades() {
		return trades;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username
				+ "telephone=" + telephone + "password=" + password
				+ ", accountId=" + accountId + "]";
	}

	public User(Integer userId, String username, int accountId) {
		super();
		this.userId = userId;
		this.username = username;
		this.accountId = accountId;
	}
	
	public User(String username, String password, String telephone, int accountId) {
		this.username = username;
		this.password = password;
		this.telephone = telephone;
		this.accountId = accountId;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
