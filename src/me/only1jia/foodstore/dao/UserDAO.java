package me.only1jia.foodstore.dao;

import me.only1jia.foodstore.domain.User;

public interface UserDAO {

	/**
	 * 根据用户名获取 User 对象
	 * @param username
	 * @return
	 */
	public abstract User getUser(String username);
	public abstract void setUser(User user);

}

