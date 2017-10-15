package me.only1jia.foodstore.dao;

import java.util.Collection;
import java.util.List;

import me.only1jia.foodstore.domain.Food;
import me.only1jia.foodstore.domain.ShoppingCartItem;
import me.only1jia.foodstore.web.CriteriaFood;
import me.only1jia.foodstore.web.Page;


public interface FoodDAO {

	/**
	 * 根据 id 获取指定 Food 对象
	 * @param id
	 * @return
	 */
	public abstract Food getFood(int id);

	/**
	 * 根据传入的 CriteriaFood 对象返回对应的 Page 对象
	 * @param cb
	 * @return
	 */
	public abstract Page<Food> getPage(CriteriaFood cb);

	/**
	 * 根据传入的 CriteriaFood 对象返回其对应的记录数
	 * @param cb
	 * @return
	 */
	public abstract long getTotalFoodNumber(CriteriaFood cb);

	/**
	 * 根据传入的 CriteriaFood 和 pageSize 返回当前页对应的 List 
	 * @param cb
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract List<Food> getPageList(CriteriaFood cb,int pageSize);

	/**
	 * 返回指定 id 的 Food 的 storeNumber 字段的值
	 * @param id
	 * @return
	 */
	public abstract int getStoreNumber(Integer id);

	/**
	 * 根据传入的 ShoppingCartItem 的集合, 
	 * 批量更新 Foods 数据表的 storenumber 和 salesnumber 字段的值
	 * @param items
	 */
	public abstract void batchUpdateStoreNumberAndSalesAmount(
			Collection<ShoppingCartItem> items);

}