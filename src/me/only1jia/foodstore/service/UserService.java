package me.only1jia.foodstore.service;

import java.util.Iterator;
import java.util.Set;

import me.only1jia.foodstore.dao.FoodDAO;
import me.only1jia.foodstore.dao.TradeDAO;
import me.only1jia.foodstore.dao.TradeItemDAO;
import me.only1jia.foodstore.dao.UserDAO;
import me.only1jia.foodstore.domain.Trade;
import me.only1jia.foodstore.domain.TradeItem;
import me.only1jia.foodstore.domain.User;
import me.only1jia.foodstore.impl.FoodDAOImpl;
import me.only1jia.foodstore.impl.TradeDAOImpl;
import me.only1jia.foodstore.impl.TradeItemDAOImpl;
import me.only1jia.foodstore.impl.UserDAOImpl;


public class UserService {

	private UserDAO userDAO = new UserDAOImpl();
	
	public User getUserByUserName(String username){
		return userDAO.getUser(username);
	}
	
	private TradeDAO tradeDAO = new TradeDAOImpl();
	private TradeItemDAO tradeItemDAO = new TradeItemDAOImpl();
	private FoodDAO foodDAO = new FoodDAOImpl();
	
	public User getUserWithTrades(String username){
	
//		调用 UserDAO 的方法获取 User 对象
		User user = userDAO.getUser(username);
		if(user == null){
			return null;
		}
		
//		调用 TradeDAO 的方法获取 Trade 的集合，把其装配为 User 的属性
		int userId = user.getUserId();
		
//		调用 TradeItemDAO 的方法获取每一个 Trade 中的 TradeItem 的集合，并把其装配为 Trade 的属性
		Set<Trade> trades = tradeDAO.getTradesWithUserId(userId);
		
		if(trades != null){
			Iterator<Trade> tradeIt = trades.iterator();
			
			while(tradeIt.hasNext()){
				Trade trade = tradeIt.next();
				
				int tradeId = trade.getTradeId();
				Set<TradeItem> items = tradeItemDAO.getTradeItemsWithTradeId(tradeId);
				
				if(items != null){
					for(TradeItem item: items){
						item.setFood(foodDAO.getFood(item.getFoodId())); 
					}
					
					if(items != null && items.size() != 0){
						trade.setItems(items);						
					}
				}
				
				if(items == null || items.size() == 0){
					tradeIt.remove();	
				}
				
			}
		}
		
		if(trades != null && trades.size() != 0){
			user.setTrades(trades);			
		}
		
		return user;
	}
	
	public void addUser(User user) {
		userDAO.setUser(user);
	}
	
}
