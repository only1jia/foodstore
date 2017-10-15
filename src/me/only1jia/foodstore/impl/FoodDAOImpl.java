package me.only1jia.foodstore.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import me.only1jia.foodstore.dao.FoodDAO;
import me.only1jia.foodstore.domain.Food;
import me.only1jia.foodstore.domain.ShoppingCartItem;
import me.only1jia.foodstore.web.CriteriaFood;
import me.only1jia.foodstore.web.Page;


public class FoodDAOImpl extends BaseDAO<Food> implements FoodDAO {

	@Override
	public Food getFood(int id) {
		String sql = "SELECT id, title, price, " +
				"salesAmount, storeNumber, remark FROM myfoods WHERE id = ?";
		return query(sql, id);
	}

	//3. 
	@Override
	public Page<Food> getPage(CriteriaFood cb) {
		Page<Food> page = new Page<>(cb.getPageNo());
		
		page.setTotalItemNumber(getTotalFoodNumber(cb));
		//校验 pageNo 的合法性
		cb.setPageNo(page.getPageNo());
		page.setList(getPageList(cb, 3));
		
		return page;
	}

	//1. 
	public long getTotalFoodNumber(CriteriaFood cb) {
		String sql = "SELECT count(id) FROM myfoods WHERE price >= ? AND price <= ?";
		return getSingleVal(sql, cb.getMinPrice(), cb.getMaxPrice()); 
	}

	//2. 
	/**
	 * MySQL 分页使用 LIMIT, 其中 fromIndex 从 0 开始。 
	 */
	@Override
	public List<Food> getPageList(CriteriaFood cb, int pageSize) {
		String sql = "SELECT id, title, price, " +
				"salesAmount, storeNumber, remark FROM myfoods " +
				"WHERE price >= ? AND price <= ? " +
				"LIMIT ?, ?";
		
		return queryForList(sql, cb.getMinPrice(), cb.getMaxPrice(), 
				(cb.getPageNo() - 1) * pageSize, pageSize);
	}

	@Override
	public int getStoreNumber(Integer id) {
		String sql = "SELECT storeNumber FROM myfoods WHERE id = ?";
		return getSingleVal(sql, id);
	}

	@Override
	public void batchUpdateStoreNumberAndSalesAmount(
			Collection<ShoppingCartItem> items) {
		String sql = "UPDATE myfoods SET salesAmount = salesAmount + ?, " +
				"storeNumber = storeNumber - ? " +
				"WHERE id = ?";
		Object [][] params = null;
		params = new Object[items.size()][3];
		List<ShoppingCartItem> scis = new ArrayList<>(items);
		for(int i = 0; i < items.size(); i++){
			params[i][0] = scis.get(i).getQuantity();
			params[i][1] = scis.get(i).getQuantity();
			params[i][2] = scis.get(i).getFood().getId();
		}
		batch(sql, params);
	}



}
