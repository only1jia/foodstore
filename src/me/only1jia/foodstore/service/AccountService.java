package me.only1jia.foodstore.service;

import me.only1jia.foodstore.dao.AccountDAO;
import me.only1jia.foodstore.domain.Account;
import me.only1jia.foodstore.impl.AccountDAOIml;


public class AccountService {
	
	private AccountDAO accountDAO = new AccountDAOIml();
	
	public Account getAccount(int accountId){
		return accountDAO.get(accountId);
	}
	
}
