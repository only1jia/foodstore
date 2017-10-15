package me.only1jia.foodstore.impl;

import me.only1jia.foodstore.dao.UserDAO;
import me.only1jia.foodstore.domain.Trade;
import me.only1jia.foodstore.domain.User;


public class UserDAOImpl extends BaseDAO<User> implements UserDAO {

	@Override
	public User getUser(String username) {
		String sql = "SELECT userId, username, password, telephone, accountId " +
				"FROM userinfo WHERE username = ?";
		return query(sql, username); 
	}

	@Override
	public void setUser(User user) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO userinfo (username, password, telephone, accountid) VALUES " +
				"(?, ?, ?, ?)";
		insert(sql, user.getUsername(), user.getPassword(), user.getTelephone(), user.getAccountId());
	}
}
