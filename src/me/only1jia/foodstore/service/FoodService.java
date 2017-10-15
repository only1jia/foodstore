package me.only1jia.foodstore.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import me.only1jia.foodstore.dao.AccountDAO;
import me.only1jia.foodstore.dao.FoodDAO;
import me.only1jia.foodstore.dao.TradeDAO;
import me.only1jia.foodstore.dao.TradeItemDAO;
import me.only1jia.foodstore.dao.UserDAO;
import me.only1jia.foodstore.db.JDBCUtils;
import me.only1jia.foodstore.domain.Food;
import me.only1jia.foodstore.domain.ShoppingCart;
import me.only1jia.foodstore.domain.ShoppingCartItem;
import me.only1jia.foodstore.domain.Trade;
import me.only1jia.foodstore.domain.TradeItem;
import me.only1jia.foodstore.impl.AccountDAOIml;
import me.only1jia.foodstore.impl.FoodDAOImpl;
import me.only1jia.foodstore.impl.TradeDAOImpl;
import me.only1jia.foodstore.impl.TradeItemDAOImpl;
import me.only1jia.foodstore.impl.UserDAOImpl;
import me.only1jia.foodstore.web.CriteriaFood;
import me.only1jia.foodstore.web.Page;


public class FoodService {
	
	private FoodDAO foodDAO = new FoodDAOImpl();
	
	public Page<Food> getPage(CriteriaFood criteriaFood){
		return foodDAO.getPage(criteriaFood);
	}

	public Food getFood(int id) {
		return foodDAO.getFood(id);
	}

	public boolean addToCart(int id, ShoppingCart sc) {
		Food food = foodDAO.getFood(id);
		
		if(food != null){
			sc.addFood(food);
			return true;
		}
		
		return false;
	}

	public void removeItemFromShoppingCart(ShoppingCart sc, int id) {
		sc.removeItem(id);
	}

	public void clearShoppingCart(ShoppingCart sc) {
		sc.clear();
	}

	public void updateItemQuantity(ShoppingCart sc, int id, int quantity) {
		sc.updateItemQuantity(id, quantity);
	}
	
	private AccountDAO accountDAO = new AccountDAOIml();
	private TradeDAO tradeDAO = new TradeDAOImpl();
	private UserDAO userDAO = new UserDAOImpl();
	private TradeItemDAO tradeItemDAO = new TradeItemDAOImpl();

	//ҵ�񷽷�. 
	public void cash(ShoppingCart shoppingCart, String username,
			String accountId) {
		
		//1. ���� myFoods ���ݱ���ؼ�¼�� salesamount �� storenumber
		foodDAO.batchUpdateStoreNumberAndSalesAmount(shoppingCart.getItems());
		
		
		//2. ���� account ���ݱ�� balance
		accountDAO.updateBalance(Integer.parseInt(accountId), shoppingCart.getTotalMoney());
		
		//3. �� trade ���ݱ����һ����¼
		Trade trade = new Trade();
		trade.setTradeTime(new Date(new java.util.Date().getTime()));
		trade.setUserId(userDAO.getUser(username).getUserId());
		tradeDAO.insert(trade);
		
		//4. �� tradeitem ���ݱ���� n ����¼
		Collection<TradeItem> items = new ArrayList<>();
		for(ShoppingCartItem sci: shoppingCart.getItems()){
			TradeItem tradeItem = new TradeItem();
			
			tradeItem.setFoodId(sci.getFood().getId());
			tradeItem.setQuantity(sci.getQuantity());
			tradeItem.setTradeId(trade.getTradeId());
			
			items.add(tradeItem);
		}
		tradeItemDAO.batchSave(items);
		
		//5. ��չ��ﳵ
		shoppingCart.clear();
	}
	
}
