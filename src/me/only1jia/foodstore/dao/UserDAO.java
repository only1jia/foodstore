package me.only1jia.foodstore.dao;

import me.only1jia.foodstore.domain.User;

public interface UserDAO {

	/**
	 * �����û�����ȡ User ����
	 * @param username
	 * @return
	 */
	public abstract User getUser(String username);
	public abstract void setUser(User user);

}

